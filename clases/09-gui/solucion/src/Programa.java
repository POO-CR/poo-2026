// Archivo: Programa.java (solucion)
// El bloque de la parte 3b. Es el unico lugar donde aparecen los new de las
// tres clases y donde se decide la cotizacion.

import controlador.ControladorConversor;
import modelo.Conversor;
import vista.VistaConversor;

public class Programa {

    public static void main(String[] args) {

        Conversor conversor = new Conversor(1450.0, 1620.0);
        VistaConversor vista = new VistaConversor();
        ControladorConversor controlador = new ControladorConversor(vista, conversor);
        vista.mostrar();

        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
