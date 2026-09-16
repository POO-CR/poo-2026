// Archivo: Caja.java
// El modelo. Cada metodo que cambia el estado termina con notificar(): la
// Caja no sabe quienes son los observadores ni que hacen, solo que tienen
// cajaCambio(). No importa nada de javax.swing.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caja {

    private final List<Pago> pagos;
    private final Notificador notificador;
    private final List<ObservadorCaja> observadores;

    public Caja(Notificador notificador) {
        if (notificador == null) {
            throw new IllegalArgumentException("El notificador es obligatorio");
        }
        this.pagos = new ArrayList<>();
        this.notificador = notificador;
        this.observadores = new ArrayList<>();
    }

    public boolean registrar(Pago pago) {
        if (pago == null) {
            return false;
        }
        this.pagos.add(pago);
        this.notificar();
        return true;
    }

    public boolean anularUltimo() {
        if (this.pagos.isEmpty()) {
            return false;
        }
        this.pagos.remove(this.pagos.size() - 1);
        this.notificar();
        return true;
    }

    public List<Pago> getPagos() {
        return Collections.unmodifiableList(this.pagos);
    }

    public double total() {
        double total = 0;
        for (Pago pago : this.pagos) {
            total += pago.montoAcreditado();
        }
        return total;
    }

    public double cerrar() {
        double total = this.total();
        this.notificador.enviar("Cierre del dia: $" + total);
        return total;
    }

    public void agregarObservador(ObservadorCaja observador) {
        if (observador != null) {
            this.observadores.add(observador);
        }
    }

    private void notificar() {
        for (ObservadorCaja observador : this.observadores) {
            observador.cajaCambio(this);
        }
    }
}
