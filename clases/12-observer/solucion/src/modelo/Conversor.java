// Archivo: Conversor.java (solucion)
// El modelo. Cada conversion se guarda en el historial y termina con
// notificar(): el Conversor no sabe quienes observan ni que hacen.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Conversor {

    private final double pesosPorDolar;
    private final double pesosPorEuro;
    private final double pesosPorReal;
    private final List<Conversion> historial;
    private final List<ObservadorConversor> observadores;

    public Conversor(double pesosPorDolar, double pesosPorEuro, double pesosPorReal) {
        if (pesosPorDolar <= 0 || pesosPorEuro <= 0 || pesosPorReal <= 0) {
            throw new IllegalArgumentException("La cotizacion debe ser mayor que cero");
        }
        this.pesosPorDolar = pesosPorDolar;
        this.pesosPorEuro = pesosPorEuro;
        this.pesosPorReal = pesosPorReal;
        this.historial = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public double aDolares(double pesos) {
        return this.convertir(pesos, "USD", this.pesosPorDolar);
    }

    public double aEuros(double pesos) {
        return this.convertir(pesos, "EUR", this.pesosPorEuro);
    }

    public double aReales(double pesos) {
        return this.convertir(pesos, "BRL", this.pesosPorReal);
    }

    private double convertir(double pesos, String moneda, double cotizacion) {
        if (pesos < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        double resultado = pesos / cotizacion;
        this.historial.add(new Conversion(pesos, moneda, resultado));
        this.notificar();
        return resultado;
    }

    public List<Conversion> getHistorial() {
        return Collections.unmodifiableList(this.historial);
    }

    // La ultima conversion hecha, o null si no hubo ninguna.
    public Conversion getUltima() {
        if (this.historial.isEmpty()) {
            return null;
        }
        return this.historial.get(this.historial.size() - 1);
    }

    // TODO 1
    public void agregarObservador(ObservadorConversor observador) {
        if (observador != null) {
            this.observadores.add(observador);
        }
    }

    private void notificar() {
        for (ObservadorConversor observador : this.observadores) {
            observador.conversorCambio(this);
        }
    }
}
