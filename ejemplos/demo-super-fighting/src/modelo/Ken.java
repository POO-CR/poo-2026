package modelo;

/**
 * Ken. Su super es el Shinku Hadoken (un rayo).
 *
 * Aca estan los tiempos de sus animaciones (cuantos fotogramas y cuanto dura
 * cada uno), el fotograma en que sale el rayo y su altura. Como se ve cada fotograma esta en la vista
 * ({@code vista.AparienciaKen}).
 */
public class Ken extends Luchador {

    /* Fotogramas clave de las animaciones (los numeros son posiciones en la animacion) */
    private static final int LANZAMIENTO_SUPER = 3;

    private static final double ALTURA_RAYO = 105;

    /**
     * Crea a Ken con sus animaciones.
     */
    public Ken() {
        /* cantidad de fotogramas, milisegundos por fotograma, si se repite: guardia, super, golpeado */
        super("KEN", new Animacion(7, 90, true), new Animacion(8, 70, false), new Animacion(2, 120, false));
    }

    @Override
    protected int getFotogramaLanzamientoSuper() {
        return LANZAMIENTO_SUPER;
    }

    @Override
    protected double getAlturaRayo() {
        return ALTURA_RAYO;
    }
}
