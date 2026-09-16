// Archivo: Programa.java
// Arma las tres clases y muestra la ventana.

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
    }
}
