// Archivo: Programa.java (solucion)
// El bloque de la parte 3b. La ventana se arma y se muestra desde el hilo de
// Swing: invokeLater() recibe un Runnable, que tiene un solo metodo, asi que
// una lambda sin parametros.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
        });
        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
