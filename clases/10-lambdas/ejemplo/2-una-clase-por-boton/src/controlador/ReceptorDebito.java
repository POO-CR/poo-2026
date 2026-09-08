// Archivo: ReceptorDebito.java
// La copia de ReceptorEfectivo para el otro boton. Cambia una linea.

package controlador;

import modelo.Caja;
import modelo.PagoDebito;
import vista.VistaCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptorDebito implements ActionListener {

    private final VistaCaja vista;
    private final Caja caja;

    public ReceptorDebito(VistaCaja vista, Caja caja) {
        this.vista = vista;
        this.caja = caja;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            double monto = Double.parseDouble(this.vista.getTextoMonto());
            this.caja.registrar(new PagoDebito(monto));
            this.vista.mostrarTotal(this.caja.total());
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
