// Archivo: ControladorCaja.java
// Un receptor por boton, como lambda: solo el cuerpo del unico metodo de
// ActionListener. El controlador no implementa ActionListener ni tiene
// actionPerformed(); tiene un metodo privado por cosa que puede pasar.

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import vista.VistaCaja;


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
