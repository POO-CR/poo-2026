// Archivo: Programa.java (solucion)
// El bloque de la parte 3: tres vistas observando al mismo Conversor.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaContador;
import vista.VistaConversor;
import vista.VistaHistorial;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
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
    }
}
