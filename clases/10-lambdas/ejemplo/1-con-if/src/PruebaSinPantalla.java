// Archivo: PruebaSinPantalla.java
// El modelo, usado desde main sin abrir ninguna ventana. Sigue igual que en
// la clase 9: nada de lo que cambio hoy toca el paquete modelo.

import modelo.Caja;
import modelo.PagoDebito;
import modelo.PagoEfectivo;
import modelo.ServicioCorreo;

public class PruebaSinPantalla {

    public static void main(String[] args) {
        Caja caja = new Caja(new ServicioCorreo("smtp.lapampa.com", 587));
        caja.registrar(new PagoEfectivo(1500));
        caja.registrar(new PagoDebito(800));
        System.out.println("Total: $" + caja.total());
        caja.cerrar();
    }
}
