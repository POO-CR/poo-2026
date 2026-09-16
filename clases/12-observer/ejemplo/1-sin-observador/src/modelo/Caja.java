// Archivo: Caja.java
// El modelo. Registra pagos, anula el ultimo y calcula el total. No sabe que
// existen las vistas: quien lo modifica tiene que acordarse de actualizarlas.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
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

    public boolean anularUltimo() {
        if (this.pagos.isEmpty()) {
            return false;
        }
        this.pagos.remove(this.pagos.size() - 1);
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
}
