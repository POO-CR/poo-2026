// Archivo: Enemigo.java
// Un enemigo es una unidad con una tactica. La tactica la elige quien arma el
// nivel, segun la dificultad, y hoy es un texto con un switch.

package modelo;

public class Enemigo {

    private final Unidad unidad;
    private String tactica;

    public Enemigo(Unidad unidad, String tactica) {
        if (unidad == null || tactica == null) {
            throw new IllegalArgumentException("Unidad y tactica son obligatorias");
        }
        this.unidad = unidad;
        this.tactica = tactica;
    }

    public boolean cambiarTactica(String tactica) {
        if (tactica == null) {
            return false;
        }
        this.tactica = tactica;
        return true;
    }

    // Problema B
    public Accion decidir(Unidad objetivo) {
        switch (this.tactica) {
            case "agresiva":
                return Accion.ATACAR;
            case "defensiva":
                if (this.unidad.getVida() > this.unidad.getVidaMaxima() / 2) {
                    return Accion.ATACAR;
                }
                return Accion.DEFENDER;
            default:
                return Accion.ATACAR;
        }
    }

    public Unidad getUnidad() {
        return this.unidad;
    }
}
