// Archivo: NivelFortaleza.java (solucion)
// La subclase es la decision: un nivel nuevo es una clase con un solo metodo.

package modelo;

public class NivelFortaleza extends Nivel {

    public NivelFortaleza(GestorAudio audio) {
        super("Fortaleza", audio);
    }

    @Override
    protected Enemigo crearEnemigo() {
        return new Troll();
    }
}
