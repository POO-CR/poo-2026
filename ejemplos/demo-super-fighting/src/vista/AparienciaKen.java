package vista;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Como se ve Ken ({@link modelo.Ken}). Sus movimientos salen de
 * assets/ken_cvs.png (Capcom vs. SNK). Tiene fondo verde agua, que se quita al
 * recortar, y Ken mira a la izquierda, asi que los fotogramas se espejan.
 *
 * Cada fotograma es un rectangulo {x, y, ancho, alto} dentro de su hoja. Cada
 * estado tiene que tener tantos rectangulos como fotogramas dice su animacion
 * en {@link modelo.Ken} (lo revisa {@link AparienciaLuchador#verificar}).
 */
public class AparienciaKen extends AparienciaLuchador {

    /** Escala del cuerpo (hoja de CvS: la guardia mide ~73 px, queda en ~182). */
    private static final double ESCALA = 2.5;

    /** Color del fondo de la hoja de Capcom vs. SNK. */
    private static final Color FONDO_CVS = new Color(205, 245, 236);

    /* Movimientos: hoja de Capcom vs. SNK */
    /** Guardia respirando (los fotogramas siguientes de esa fila son Ken dandose vuelta y quedan sin usar). */
    private static final int[][] QUIETO = {
            { 29, 27, 40, 72 }, { 87, 29, 40, 71 }, { 141, 29, 40, 72 }, { 199, 27, 40, 74 }, { 254, 27, 40, 74 },
            { 308, 28, 40, 73 }, { 364, 29, 40, 72 } };
    /** Pose del Hadoken, sin la vuelta: la ultima (palmas adelante) se mantiene mientras sale el rayo. */
    private static final int[][] SUPER = {
            { 102, 2674, 49, 67 }, { 175, 2676, 51, 65 }, { 252, 2676, 56, 65 }, { 333, 2676, 69, 65 },
            { 422, 2676, 69, 65 }, { 519, 2676, 69, 63 }, { 603, 2675, 69, 64 }, { 693, 2672, 67, 65 } };
    private static final int[][] GOLPEADO = {
            { 91, 3310, 44, 69 }, { 146, 3306, 41, 71 } };

    /**
     * @param imagenMovimientos hoja de Capcom vs. SNK (movimientos)
     * @param retrato           imagen de Ken que se muestra al lanzar el super
     */
    public AparienciaKen(BufferedImage imagenMovimientos, BufferedImage retrato) {
        /* En la hoja de CvS Ken mira a la izquierda: se espeja al recortar */
        this(new HojaSprites(imagenMovimientos, new FondoColor(FONDO_CVS), true), retrato);
    }

    /**
     * @param hoja    hoja de Capcom vs. SNK, lista para recortar
     * @param retrato imagen de Ken que se muestra al lanzar el super
     */
    private AparienciaKen(HojaSprites hoja, BufferedImage retrato) {
        /* La cara esta un poco a la izquierda del centro de la imagen, y mira hacia la izquierda */
        super(ESCALA, new Fotogramas(hoja.recortar(QUIETO)), new Fotogramas(hoja.recortar(SUPER)),
                new Fotogramas(hoja.recortar(GOLPEADO)), new Retrato(retrato, 0.45, false), "SHINKU HADOKEN!");
    }
}
