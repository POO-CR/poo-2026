// Archivo: Enemigo.java (solucion)
// Strategy: el enemigo delega la decision en su Tactica, que elige quien arma
// el nivel y se puede cambiar.

package modelo;

public class Enemigo {

    private final Unidad unidad;
    private Tactica tactica;

    public Enemigo(Unidad unidad, Tactica tactica) {
        if (unidad == null || tactica == null) {
            throw new IllegalArgumentException("Unidad y tactica son obligatorias");
        }
        this.unidad = unidad;
        this.tactica = tactica;
    }

    public boolean cambiarTactica(Tactica tactica) {
        if (tactica == null) {
            return false;
        }
        this.tactica = tactica;
        return true;
    }

    public Accion decidir(Unidad objetivo) {
        return this.tactica.decidir(this.unidad, objetivo);
    }

    public Unidad getUnidad() {
        return this.unidad;
    }
}
