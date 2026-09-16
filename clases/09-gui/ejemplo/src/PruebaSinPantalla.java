// Archivo: PruebaSinPantalla.java
// El modelo, usado desde main sin abrir ninguna ventana. No compila si Caja
// dependiera de algo de javax.swing. Termina solo.

import modelo.Caja;
import modelo.PagoEfectivo;
import modelo.ServicioCorreo;

public class PruebaSinPantalla {

    public static void main(String[] args) {
        Caja caja = new Caja(new ServicioCorreo("smtp.lapampa.com", 587));
        caja.registrar(new PagoEfectivo(1500));
        caja.registrar(new PagoEfectivo(800));
        System.out.println("Total: $" + caja.total());
        caja.cerrar();
    }
}
