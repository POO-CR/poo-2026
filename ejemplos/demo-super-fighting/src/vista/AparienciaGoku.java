package vista;

import java.awt.image.BufferedImage;

/**
 * Como se ve Goku ({@link modelo.Goku}), estilo Budokai. Su hoja trae las
 * poses del Kamehameha; el rayo se arma con las piezas del Shinku Hadoken de
 * la hoja de Ken ({@link SpritesRayo}).
 *
 * Cada fotograma es un rectangulo {x, y, ancho, alto} dentro de
 * assets/goku.png. Esta hoja tiene fondo negro, por eso se recorta con
 * {@link FondoNegro}.
 *
 * Cada estado tiene que tener tantos rectangulos como fotogramas dice su
 * animacion en {@link modelo.Goku} (lo revisa
 * {@link AparienciaLuchador#verificar}).
 */
public class AparienciaGoku extends AparienciaLuchador {

    /**
     * Escala del dibujo. Se eligio para que, parados derechos, Goku y Ken midan lo
     * mismo de la frente a los pies (~182 px): en su hoja Goku mide 110 px de la
     * frente a los pies (133 con el pelo) y Ken 74 px a escala 2,5. En guardia
     * Goku queda mas bajo que Ken porque su guardia es mas abierta y agachada.
     */
    private static final double ESCALA = 1.65;

    private static final int[][] QUIETO = {
            { 0, 0, 90, 99 }, { 92, 0, 90, 99 }, { 184, 0, 90, 99 }, { 276, 0, 90, 99 }, { 368, 1, 90, 98 },
            { 460, 2, 90, 97 }, { 552, 2, 90, 97 }, { 644, 2, 90, 97 }, { 736, 1, 90, 98 } };
    /** Kamehameha: manos atras ("kame-hame") y manos adelante con brillo ("ha!"), la ultima se mantiene. */
    private static final int[][] SUPER = {
            { 8, 3677, 88, 95 }, { 109, 3679, 86, 93 }, { 210, 3679, 85, 93 }, { 311, 3680, 85, 92 },
            { 405, 3675, 100, 97 }, { 506, 3673, 93, 99 }, { 607, 3671, 92, 101 }, { 708, 3673, 93, 99 },
            { 809, 3671, 92, 101 }, { 910, 3670, 90, 102 } };
    private static final int[][] GOLPEADO = {
            { 2, 4464, 95, 103 }, { 98, 4463, 95, 104 }, { 194, 4465, 94, 102 }, { 290, 4466, 94, 101 } };

    /**
     * @param imagenHoja la hoja de sprites de Goku, cargada del disco
     * @param retrato    imagen de Goku que se muestra al lanzar el super
     */
    public AparienciaGoku(BufferedImage imagenHoja, BufferedImage retrato) {
        this(new HojaSprites(imagenHoja, new FondoNegro(), false), retrato);
    }

    /**
     * @param hoja    hoja de sprites de Goku, lista para recortar
     * @param retrato imagen de Goku que se muestra al lanzar el super
     */
    private AparienciaGoku(HojaSprites hoja, BufferedImage retrato) {
        /*
         * La imagen es apaisada: se recorta alrededor de la cara (un poco a la derecha
         * del centro), lo que ademas deja afuera la marca de agua de la esquina. Goku
         * mira hacia la derecha.
         */
        super(ESCALA, new Fotogramas(hoja.recortar(QUIETO)), new Fotogramas(hoja.recortar(SUPER)),
                new Fotogramas(hoja.recortar(GOLPEADO)), new Retrato(retrato, 0.52, true), "KAMEHAMEHA!");
    }
}
