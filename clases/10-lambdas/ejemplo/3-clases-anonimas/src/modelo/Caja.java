// Archivo: Caja.java
// El modelo. La Caja de la clase 8, con registrar() y total() para poder
// cargar los pagos de a uno. No importa nada de javax.swing, ni de vista, ni
// de controlador: el paquete modelo no sabe que existen.

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Caja {

    private final List<Pago> pagos;
    private final Notificador notificador;

    public Caja(Notificador notificador) {
        if (notificador == null) {
            throw new IllegalArgumentException("El notificador es obligatorio");
        }
        this.pagos = new ArrayList<>();
        this.notificador = notificador;
    }

    public boolean registrar(Pago pago) {
        if (pago == null) {
            return false;
        }
        this.pagos.add(pago);
        return true;
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
}
