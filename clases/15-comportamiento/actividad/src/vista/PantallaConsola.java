// Archivo: PantallaConsola.java
// La vista: muestra la vida de una unidad en la consola.

package vista;

import modelo.Unidad;

public class PantallaConsola {

    public void mostrarVida(Unidad unidad) {
        System.out.println("  " + unidad.getNombre() + ": " + unidad.getVida() + "/" + unidad.getVidaMaxima());
    }
}
