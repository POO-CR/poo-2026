// Archivo: ControladorCaja.java
// Un receptor por boton, como clase anonima escrita donde se registra. Cada
// una llama a un metodo privado del controlador. Ya no hay archivos aparte.

package controlador;

import modelo.Caja;
import modelo.Pago;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import vista.VistaCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCaja {

    private final VistaCaja vista;
    private final Caja caja;

    public ControladorCaja(VistaCaja vista, Caja caja) {
        if (vista == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        this.vista = vista;
        this.caja = caja;

        this.vista.getBotonEfectivo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                registrarEfectivo();   // sin this: aca this es el objeto anonimo
            }
        });

        this.vista.getBotonDebito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                registrarDebito();
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
