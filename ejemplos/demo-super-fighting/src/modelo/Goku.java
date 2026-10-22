package modelo;

/**
 * Goku, estilo Budokai. Su super es el Kamehameha.
 *
 * Aca estan los tiempos de sus animaciones y de donde sale el rayo. Como se ve cada
 * fotograma esta en la vista ({@code vista.AparienciaGoku}).
 */
public class Goku extends Luchador {

    /* Fotogramas clave de las animaciones (los numeros son posiciones en la animacion) */
    private static final int LANZAMIENTO_SUPER = 4;

    /**
     * Las manos del Kamehameha estan a ~70 px sobre el suelo; el rayo (166 px de alto)
     * se centra un poco mas arriba para que quede por encima del piso.
     */
    private static final double ALTURA_RAYO = 85;

    /**
     * Crea a Goku con sus animaciones.
     */
    public Goku() {
        /* cantidad de fotogramas, milisegundos por fotograma, si se repite: guardia, super, golpeado */
        super("GOKU", new Animacion(9, 90, true), new Animacion(10, 70, false), new Animacion(4, 90, false));
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
