// Archivo: ControladorCaja.java
// El controlador solo le pide cosas al modelo. No actualiza ninguna vista:
// la Caja avisa a las que esten observando.

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import modelo.PagoQR;
import vista.VistaCaja;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControladorCaja {

    private final VistaCaja vista;
    private final Caja caja;

    public ControladorCaja(VistaCaja vista, Caja caja) {
        if (vista == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        this.vista = vista;
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
        this.vista.limpiarMonto();
    }
}
