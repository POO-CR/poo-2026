// Archivo: ControladorListado.java
// El boton de anular. Le pide a la Caja que anule el ultimo pago, y nada mas:
// las dos ventanas se enteran por la Caja.

package controlador;

import modelo.Caja;
import vista.VistaListado;

public class ControladorListado {

    private final VistaListado listado;
    private final Caja caja;

    public ControladorListado(VistaListado listado, Caja caja) {
        if (listado == null || caja == null) {
            throw new IllegalArgumentException("El listado y la caja son obligatorios");
        }
        this.listado = listado;
        this.caja = caja;
        this.listado.getBotonAnular().addActionListener(evento -> this.caja.anularUltimo());
    }
}
