// Archivo: Conversor.java (solucion)
// El modelo. Sabe convertir y nada mas. No importa javax.swing, ni vista, ni
// controlador. Lo unico que cambio hoy es la tercera cotizacion (TODO 6).

package modelo;

public class Conversor {

    private final double pesosPorDolar;
    private final double pesosPorEuro;
    private final double pesosPorReal;   // TODO 6

    public Conversor(double pesosPorDolar, double pesosPorEuro, double pesosPorReal) {
        if (pesosPorDolar <= 0 || pesosPorEuro <= 0 || pesosPorReal <= 0) {
            throw new IllegalArgumentException("La cotizacion debe ser mayor que cero");
        }
        this.pesosPorDolar = pesosPorDolar;
        this.pesosPorEuro = pesosPorEuro;
        this.pesosPorReal = pesosPorReal;
    }

    public double aDolares(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return pesos / this.pesosPorDolar;
    }

    public double aEuros(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return pesos / this.pesosPorEuro;
    }

    // TODO 6
    public double aReales(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return pesos / this.pesosPorReal;
    }
}
