// Archivo: Programa.java (solucion)
// El bloque de la parte 3, con las cuatro cotizaciones.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0, 1850.0);
            VistaConversor vista = new VistaConversor();
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
        });
    }
}
