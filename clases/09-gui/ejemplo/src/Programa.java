// Archivo: Programa.java
// Arranca la version repartida. Es el unico lugar donde aparecen los new de
// las tres clases. La Caja es la misma de la clase 8, con su notificador.

import controlador.ControladorCaja;
import modelo.Caja;
import modelo.ServicioCorreo;
import vista.VistaCaja;

public class Programa {

    public static void main(String[] args) {
        Caja caja = new Caja(new ServicioCorreo("smtp.lapampa.com", 587));
        VistaCaja vista = new VistaCaja();
        ControladorCaja controlador = new ControladorCaja(vista, caja);
        vista.mostrar();

        // main termina aca. La ventana sigue abierta porque Swing dejo un hilo
        // esperando eventos; el programa termina al cerrarla.
    }
}
