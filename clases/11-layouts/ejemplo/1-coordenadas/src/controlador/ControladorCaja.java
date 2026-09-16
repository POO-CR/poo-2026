// Archivo: ControladorCaja.java
// El controlador de la clase pasada, con un boton mas: una lambda y un metodo para
// el QR, y nada de lo que ya estaba se modifico. Es el mismo archivo en las
// tres etapas del ejemplo: la vista cambia de forma y el controlador no se
// entera, porque se registra en getBotonEfectivo(), getBotonDebito() y
// getBotonQR(), y esos siguen ahi.

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import modelo.PagoQR;
import vista.VistaCaja;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorCaja {

    private final VistaCaja vista;
    private final Caja caja;

    public ControladorCaja(VistaCaja vista, Caja caja) {
        if (vista == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        this.vista = vista;
        this.caja = caja;

        // Un receptor por boton.
        this.vista.getBotonEfectivo().addActionListener(evento -> this.registrarEfectivo());
        this.vista.getBotonDebito().addActionListener(evento -> this.registrarDebito());
        this.vista.getBotonQR().addActionListener(evento -> this.registrarQR());

        // Enter en el campo registra en efectivo.
        this.vista.getCampoMonto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrarEfectivo();
                }
            }
        });

        // Doble click en el campo lo vacia.
        this.vista.getCampoMonto().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    vista.limpiarMonto();
                }
            }
        });
    }

    private void registrarEfectivo() {
        try {
            this.registrar(new PagoEfectivo(this.leerMonto()));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void registrarDebito() {
        try {
            this.registrar(new PagoDebito(this.leerMonto()));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void registrarQR() {
        try {
            this.registrar(new PagoQR(this.leerMonto()));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private double leerMonto() {
        return Double.parseDouble(this.vista.getTextoMonto());
    }

    private void registrar(Pago pago) {
        this.caja.registrar(pago);
        this.vista.mostrarTotal(this.caja.total());
    }
}
