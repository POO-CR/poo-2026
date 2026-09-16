// Archivo: ControladorCaja.java
// El controlador de la clase 9 con dos botones: un solo
// receptor, registrado en los dos, que adentro pregunta cual fue la fuente.
// Funciona. El problema se ve cuando hay que agregar el tercer boton.

package controlador;

import modelo.Caja;
import modelo.PagoDebito;
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
        this.vista.getBotonEfectivo().addActionListener(this);
        this.vista.getBotonDebito().addActionListener(this);
    }

    // Lo llama Swing con cada click en cualquiera de los dos botones.
    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            double monto = Double.parseDouble(this.vista.getTextoMonto());
            if (evento.getSource() == this.vista.getBotonDebito()) {
                this.caja.registrar(new PagoDebito(monto));
            } else {
                this.caja.registrar(new PagoEfectivo(monto));
            }
            this.vista.mostrarTotal(this.caja.total());
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
