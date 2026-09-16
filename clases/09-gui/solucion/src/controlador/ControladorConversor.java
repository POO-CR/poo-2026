// Archivo: ControladorConversor.java (solucion)
// El controlador. Escucha el boton, traduce lo que hay en la pantalla a una
// llamada al modelo, y el resultado del modelo a algo que la vista muestre.
// Importa java.awt.event, que es de eventos, y nada de javax.swing.

package controlador;

import modelo.Conversor;
import vista.VistaConversor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorConversor implements ActionListener {

    private final VistaConversor vista;
    private final Conversor conversor;

    public ControladorConversor(VistaConversor vista, Conversor conversor) {
        if (vista == null || conversor == null) {
            throw new IllegalArgumentException("La vista y el conversor son obligatorios");
        }
        this.vista = vista;
        this.conversor = conversor;
        // Registro: la fuente es el boton, el receptor es este objeto.
        this.vista.getBotonDolares().addActionListener(this);
        this.vista.getBotonEuros().addActionListener(this);   // TODO 6
    }

    // Lo llama Swing cada vez que se presiona alguno de los dos botones.
    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            if (evento.getSource() == this.vista.getBotonEuros()) {   // TODO 6
                this.vista.mostrarResultado(String.format("%.2f EUR", this.conversor.aEuros(pesos)));
            } else {
                this.vista.mostrarResultado(String.format("%.2f USD", this.conversor.aDolares(pesos)));
            }
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
