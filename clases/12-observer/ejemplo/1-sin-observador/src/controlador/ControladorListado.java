// Archivo: ControladorListado.java
// El boton de anular. Anula el ultimo pago y actualiza la tabla. No conoce
// a VistaCaja, asi que el total de la otra ventana queda viejo.

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
        this.listado.getBotonAnular().addActionListener(evento -> {
            this.caja.anularUltimo();
            this.listado.actualizar();   // y VistaCaja? Este controlador no la conoce.
        });
    }
}
