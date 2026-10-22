package controlador;

import java.util.Set;
import modelo.Comandos;
import modelo.IFuenteComandos;

/**
 * Las teclas de un jugador. Lee las teclas nuevas que mantiene el controlador:
 * las que se apretaron en este tick, asi mantener la tecla repite el super una
 * sola vez.
 *
 * Es una pieza auxiliar que el controlador (ControladorPelea) usa para leer el
 * teclado.
 */
public class TecladoJugador implements IFuenteComandos {

    private final Set<Integer> teclasNuevas;
    private final int[] teclasSuper;

    /**
     * @param teclasNuevas conjunto compartido de teclas apretadas en este tick
     * @param teclasSuper  teclas del super (puede haber mas de una)
     */
    public TecladoJugador(Set<Integer> teclasNuevas, int[] teclasSuper) {
        this.teclasNuevas = teclasNuevas;
        this.teclasSuper = teclasSuper;
    }

    @Override
    public Comandos leer() {
        return new Comandos(algunaNueva(teclasSuper));
    }

    /**
     * @param teclas codigos de tecla
     * @return true si alguna se apreto en este tick
     */
    private boolean algunaNueva(int[] teclas) {
        for (int tecla : teclas) {
            if (teclasNuevas.contains(tecla)) {
                return true;
            }
        }
        return false;
    }
}
