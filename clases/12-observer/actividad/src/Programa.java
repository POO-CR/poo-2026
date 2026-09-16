// Archivo: Programa.java
// Programa de prueba. No se modifica, salvo comentar y descomentar bloques.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;
import vista.VistaHistorial;
// TODO 5: descomentar junto con el bloque de la parte 3.
// import vista.VistaContador;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {

        // ----- Parte 1: el controlador actualiza las vistas a mano -----
        // Sirve hasta el TODO 3 inclusive. Con el TODO 4 cambia el constructor
        // del controlador: comentar este bloque y descomentar el de la parte 2.
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            VistaHistorial historial = new VistaHistorial(conversor);
            ControladorConversor controlador = new ControladorConversor(vista, historial, conversor);
            vista.mostrar();
            historial.mostrar();
        });

        // ----- Parte 2: las vistas observan al Conversor -----
        /*
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            VistaHistorial historial = new VistaHistorial(conversor);
            conversor.agregarObservador(vista);
            conversor.agregarObservador(historial);
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
            historial.mostrar();
        });
        */

        // ----- Parte 3: una tercera vista, sin tocar el controlador -----
        // Descomentar este bloque y el import de arriba, con el de la parte 2 comentado.
        /*
        SwingUtilities.invokeLater(() -> {
            Conversor conversor = new Conversor(1450.0, 1620.0, 265.0);
            VistaConversor vista = new VistaConversor();
            VistaHistorial historial = new VistaHistorial(conversor);
            VistaContador contador = new VistaContador();
            conversor.agregarObservador(vista);
            conversor.agregarObservador(historial);
            conversor.agregarObservador(contador);
            ControladorConversor controlador = new ControladorConversor(vista, conversor);
            vista.mostrar();
            historial.mostrar();
            contador.mostrar();
        });
        */
    }
}
