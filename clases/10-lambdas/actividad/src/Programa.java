// Archivo: Programa.java
// Programa de prueba. No se modifica, salvo comentar y descomentar bloques.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;

// TODO 7: descomentar este import junto con el bloque de la parte 3b.
// import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {

        // ----- Partes 1 y 2: el conversor tal como quedo en la clase 9 -----
        // Sirve hasta el TODO 5 inclusive. Con el TODO 6 cambia el constructor
        // de Conversor: comentar este bloque y descomentar el de la parte 3a.

        Conversor conversor = new Conversor(1450.0, 1620.0);
        VistaConversor vista = new VistaConversor();
        ControladorConversor controlador = new ControladorConversor(vista, conversor);
        vista.mostrar();

        // ----- Parte 3a: dolares, euros y reales -----
        // Descomentar el bloque (sacar el /* y el */).
        /*
        Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
        VistaConversor vista = new VistaConversor();
        ControladorConversor controlador = new ControladorConversor(vista, conversor);
        vista.mostrar();
        */

        // ----- Parte 3b: lo mismo, arrancado desde el hilo de Swing -----
        // Descomentar este bloque y el import de arriba, con el de la parte 3a
        // comentado. Se ve igual.
        /*
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
        });
        */

        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
