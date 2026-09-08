// Archivo: Programa.java
// Arranca la version final. La ventana se arma y se muestra desde el hilo de
// Swing: invokeLater() recibe una tarea (un Runnable, interfaz de un solo
// metodo, asi que una lambda sin parametros) y la ejecuta en ese hilo.

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
        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
