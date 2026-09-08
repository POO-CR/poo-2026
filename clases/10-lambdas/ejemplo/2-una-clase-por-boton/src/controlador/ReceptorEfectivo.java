// Archivo: ReceptorEfectivo.java
// Una clase por boton. Cada receptor necesita la vista y
// la caja, asi que las recibe por constructor. Con ReceptorDebito son dos
// archivos casi iguales; con cinco medios de pago serian cinco.

package controlador;

import modelo.Caja;
import modelo.PagoEfectivo;
import vista.VistaCaja;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptorEfectivo implements ActionListener {

    private final VistaCaja vista;
    private final Caja caja;

    public ReceptorEfectivo(VistaCaja vista, Caja caja) {
        this.vista = vista;
        this.caja = caja;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            double monto = Double.parseDouble(this.vista.getTextoMonto());
            this.caja.registrar(new PagoEfectivo(monto));
            this.vista.mostrarTotal(this.caja.total());
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
