// Archivo: Conversor.java
// El modelo, tal como quedo en la clase 9. Sabe convertir y nada mas. No importa javax.swing, ni vista, ni
// controlador: se puede usar desde una ventana, desde la consola o desde una
// prueba.

package modelo;

public class Conversor {

    private final double pesosPorDolar;
    private final double pesosPorEuro;

    public Conversor(double pesosPorDolar, double pesosPorEuro) {
        if (pesosPorDolar <= 0 || pesosPorEuro <= 0) {
            throw new IllegalArgumentException("La cotizacion debe ser mayor que cero");
        }
        this.pesosPorDolar = pesosPorDolar;
        this.pesosPorEuro = pesosPorEuro;
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
}
