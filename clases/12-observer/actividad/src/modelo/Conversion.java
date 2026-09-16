// Archivo: Conversion.java
// Una conversion hecha: cuantos pesos, a que moneda, cuanto dio.

package modelo;

public class Conversion {

    private final double pesos;
    private final String moneda;
    private final double resultado;

    public Conversion(double pesos, String moneda, double resultado) {
        this.pesos = pesos;
        this.moneda = moneda;
        this.resultado = resultado;
    }

    public double getPesos() {
        return this.pesos;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public double getResultado() {
        return this.resultado;
    }
}
