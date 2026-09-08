// Archivo: ControladorCaja.java
// Las lambdas de los botones, mas dos interfaces de varios metodos: Enter en
// el campo registra en efectivo (KeyAdapter) y doble click en el campo lo
// vacia (MouseAdapter). Clases anonimas que sobrescriben solo un metodo.

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
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

        // Un receptor por boton. Cada lambda es un objeto que implementa
        // ActionListener; Swing la llama con cada click.
        this.vista.getBotonEfectivo().addActionListener(evento -> this.registrarEfectivo());
        this.vista.getBotonDebito().addActionListener(evento -> this.registrarDebito());

        // Enter en el campo registra en efectivo. KeyListener tiene tres
        // metodos, asi que no puede ser una lambda: es una clase anonima que
        // hereda de KeyAdapter y sobrescribe solo el que interesa.
        this.vista.getCampoMonto().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrarEfectivo();   // sin this: aca this es el KeyAdapter
                }
            }
        });

        // Doble click en el campo lo vacia. MouseListener tiene cinco metodos.
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

    private double leerMonto() {
        return Double.parseDouble(this.vista.getTextoMonto());
    }

    private void registrar(Pago pago) {
        this.caja.registrar(pago);
        this.vista.mostrarTotal(this.caja.total());
    }
}
