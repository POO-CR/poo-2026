// Archivo: NivelPantano.java (solucion)
// La subclase es la decision: un nivel nuevo es una clase con un solo metodo.

package modelo;

public class NivelPantano extends Nivel {

    public NivelPantano(GestorAudio audio) {
        super("Pantano", audio);
    }

    @Override
    protected Enemigo crearEnemigo() {
        return new Arquero();
    }
}
