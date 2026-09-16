// Archivo: Conversor.java (solucion)
// El modelo. Lo unico que cambio hoy es la cuarta cotizacion (TODO 5).

package modelo;

public class Conversor {

    private final double pesosPorDolar;
    private final double pesosPorEuro;
    private final double pesosPorReal;
    private final double pesosPorLibra;   // TODO 5

    public Conversor(double pesosPorDolar, double pesosPorEuro, double pesosPorReal, double pesosPorLibra) {
        if (pesosPorDolar <= 0 || pesosPorEuro <= 0 || pesosPorReal <= 0 || pesosPorLibra <= 0) {
            throw new IllegalArgumentException("La cotizacion debe ser mayor que cero");
        }
        this.pesosPorDolar = pesosPorDolar;
        this.pesosPorEuro = pesosPorEuro;
        this.pesosPorReal = pesosPorReal;
        this.pesosPorLibra = pesosPorLibra;
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

    public double aReales(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return pesos / this.pesosPorReal;
    }

    // TODO 5
    public double aLibras(double pesos) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return pesos / this.pesosPorLibra;
    }
}
