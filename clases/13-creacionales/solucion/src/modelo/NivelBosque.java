// Archivo: NivelBosque.java (solucion)
// La subclase es la decision: un nivel nuevo es una clase con un solo metodo.

package modelo;

public class NivelBosque extends Nivel {

    public NivelBosque(GestorAudio audio) {
        super("Bosque", audio);
    }

    @Override
    protected Enemigo crearEnemigo() {
        return new Orco();
    }
}
