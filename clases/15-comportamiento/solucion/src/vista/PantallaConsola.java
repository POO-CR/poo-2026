// Archivo: PantallaConsola.java (solucion)
// La vista observa a las unidades: muestra la vida cuando la unidad avisa.

package vista;

import modelo.ObservadorUnidad;
import modelo.Unidad;

public class PantallaConsola implements ObservadorUnidad {

    @Override
    public void vidaCambio(Unidad unidad) {
        System.out.println("  " + unidad.getNombre() + ": " + unidad.getVida() + "/" + unidad.getVidaMaxima());
    }
}
