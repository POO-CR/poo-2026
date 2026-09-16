// Archivo: Programa.java
// Las dos ventanas se registran como observadoras de la Caja. Los
// controladores solo le piden cosas al modelo.

import controlador.ControladorCaja;
import controlador.ControladorListado;
import modelo.Caja;
import modelo.ServicioCorreo;
import vista.VistaCaja;
import vista.VistaListado;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Caja caja = new Caja(new ServicioCorreo("smtp.lapampa.com", 587));
            VistaCaja vista = new VistaCaja();
            VistaListado listado = new VistaListado(caja);
            caja.agregarObservador(vista);
            caja.agregarObservador(listado);
            new ControladorCaja(vista, caja);
            new ControladorListado(listado, caja);
            vista.mostrar();
            listado.mostrar();
        });
    }
}
