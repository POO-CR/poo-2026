// Archivo: ControladorCaja.java
// El controlador. Escucha el boton y traduce en las dos direcciones: lo que
// hay en la pantalla a una llamada al modelo, y lo que devuelve el modelo a
// algo que la vista muestre. Importa java.awt.event y nada de javax.swing.

package controlador;

import modelo.Caja;
import modelo.PagoEfectivo;
import vista.VistaCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCaja implements ActionListener {

    private final VistaCaja vista;
    private final Caja caja;

    public ControladorCaja(VistaCaja vista, Caja caja) {
        if (vista == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        this.vista = vista;
        this.caja = caja;
        // Registro: la fuente es el boton, el receptor es este objeto.
        this.vista.getBotonRegistrar().addActionListener(this);
    }

    // Lo llama Swing con cada click. Nadie del programa lo llama.
    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            double monto = Double.parseDouble(this.vista.getTextoMonto());
            this.caja.registrar(new PagoEfectivo(monto));
            this.vista.mostrarTotal(this.caja.total());
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());   // lo que rechazo el modelo
        }
    }
}
