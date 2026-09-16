// Archivo: Programa.java
// Arranca la caja desde el hilo de Swing, como la clase pasada.

import controlador.ControladorCaja;
import modelo.Caja;
import modelo.ServicioCorreo;
import vista.VistaCaja;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Caja caja = new Caja(new ServicioCorreo("smtp.lapampa.com", 587));
            VistaCaja vista = new VistaCaja();
            ControladorCaja controlador = new ControladorCaja(vista, caja);
            vista.mostrar();
        });
    }
}
