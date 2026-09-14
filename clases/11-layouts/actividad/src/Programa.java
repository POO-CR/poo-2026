// Archivo: Programa.java
// Programa de prueba. No se modifica, salvo comentar y descomentar bloques.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {

        // ----- Partes 1 y 2: el conversor tal como quedo la clase pasada -----
        // Sirve hasta el TODO 4 inclusive. Con el TODO 5 cambia el constructor
        // de Conversor: comentar este bloque y descomentar el de la parte 3.
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
        });

        // ----- Parte 3: dolares, euros, reales y libras -----
        // Descomentar el bloque (sacar el /* y el */).
        /*
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0, 1850.0);
            VistaConversor vista = new VistaConversor();
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
        });
        */

        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
