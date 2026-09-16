// Archivo: Programa.java
// Dos ventanas sobre la misma Caja, cada una con su controlador. Registrar
// desde la caja actualiza las dos; anular desde el listado, solo el listado.

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
            new ControladorCaja(vista, listado, caja);
            new ControladorListado(listado, caja);
            vista.mostrar();
            listado.mostrar();
        });
    }
}
