// Archivo: ControladorCaja.java
// El controlador registra el pago y despues actualiza las dos vistas a mano.
// Cada vista nueva es un parametro mas y una linea mas en registrar().

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import modelo.PagoQR;
import vista.VistaCaja;
import vista.VistaListado;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorCaja {

    private final VistaCaja vista;
    private final VistaListado listado;
    private final Caja caja;

    public ControladorCaja(VistaCaja vista, VistaListado listado, Caja caja) {
        if (vista == null || listado == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        this.vista = vista;
        this.listado = listado;
        this.caja = caja;

        this.vista.getBotonEfectivo().addActionListener(evento -> this.registrar(new PagoEfectivo(this.leerMonto())));
        this.vista.getBotonDebito().addActionListener(evento -> this.registrar(new PagoDebito(this.leerMonto())));
        this.vista.getBotonQR().addActionListener(evento -> this.registrar(new PagoQR(this.leerMonto())));

        this.vista.getCampoMonto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrar(new PagoEfectivo(leerMonto()));
                }
            }
        });
    }

    private double leerMonto() {
        try {
            return Double.parseDouble(this.vista.getTextoMonto());
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
            return 0;
        }
    }

    private void registrar(Pago pago) {
        this.caja.registrar(pago);
        this.vista.mostrarTotal(this.caja.total());   // una linea por vista
        this.listado.actualizar();
        this.vista.limpiarMonto();
    }
}
