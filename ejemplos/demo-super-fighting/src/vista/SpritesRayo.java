package vista;

import java.awt.image.BufferedImage;

/**
 * Piezas graficas del rayo, recortadas de la fila del Shinku Hadoken en la hoja
 * de Ken de Marvel vs. Capcom 2 (assets/ken_mvc.png). Las usan los dos luchadores: Ken para su Shinku Hadoken y Goku para
 * su Kamehameha (su hoja trae las poses del Kamehameha y Goku toma el rayo de aca).
 *
 * El rayo no es una sola imagen: se arma con piezas para poder tener cualquier
 * largo.
 *
 * <pre>
 *  anillos (en las manos, en loop mientras dura el rayo)
 *    (( )
 *  +------+--------+--------+--------+-----\
 *  | cola | cuerpo | cuerpo | cuerpo | punta )   &lt;- al tocar al rival, la punta
 *  +------+--------+--------+--------+-----/        pasa a ser el impacto: la
 *                                                   energia violeta y la azulada
 *                                                   que estan juntas en la hoja,
 *                                                   alternandose
 *  origen        (el cuerpo se repite)      largo
 * </pre>
 *
 * Hay 3 variantes de cola y cuerpo que se alternan para que el rayo "vibre".
 */
public class SpritesRayo {

    private static final int[][] COLAS = {
            { 966, 861, 59, 69 }, { 1094, 861, 59, 69 }, { 1222, 859, 58, 71 } };
    private static final int[][] CUERPOS = {
            { 1029, 861, 61, 69 }, { 1157, 861, 61, 69 }, { 1284, 859, 62, 71 } };
    private static final int[][] PUNTA = {
            { 1350, 843, 60, 103 } };
    /** La energia azulada y la violeta, las dos ultimas piezas de la fila. */
    private static final int[][] IMPACTO = {
            { 1350, 843, 60, 103 }, { 1414, 837, 58, 113 } };
    private static final int[][] ANILLOS = {
            { 19, 854, 48, 75 }, { 72, 832, 77, 113 }, { 153, 824, 103, 131 }, { 260, 818, 125, 143 } };

    /** Alto de referencia del cuerpo del rayo en la hoja (sin escalar). */
    private static final int ALTO_CUERPO = 69;

    /**
     * Escala de dibujo: la del Ken de la hoja de Marvel vs. Capcom, asi el rayo
     * tiene el tamanio que tiene en ese juego.
     */
    private static final double ESCALA = 2.4;

    private final BufferedImage[] colas;
    private final BufferedImage[] cuerpos;
    private final BufferedImage punta;
    private final BufferedImage[] impacto;
    private final BufferedImage[] anillos;

    /**
     * @param imagenHojaKen la hoja de Ken de Marvel vs. Capcom 2, cargada del disco
     */
    public SpritesRayo(BufferedImage imagenHojaKen) {
        HojaSprites hoja = new HojaSprites(imagenHojaKen, new FondoTransparente(), false);
        colas = hoja.recortar(COLAS);
        cuerpos = hoja.recortar(CUERPOS);
        punta = hoja.recortar(PUNTA)[0];
        impacto = hoja.recortar(IMPACTO);
        anillos = hoja.recortar(ANILLOS);
    }

    /**
     * @return la cantidad de variantes de cola y cuerpo
     */
    public int getCantidadVariantes() {
        return colas.length;
    }

    /**
     * @param variante numero de variante
     * @return la parte de atras del rayo (la que sale de las manos)
     */
    public BufferedImage getCola(int variante) {
        return colas[variante];
    }

    /**
     * @param variante numero de variante
     * @return el tramo del medio, que se repite a lo largo del rayo
     */
    public BufferedImage getCuerpo(int variante) {
        return cuerpos[variante];
    }

    /**
     * @return la punta del rayo mientras avanza
     */
    public BufferedImage getPunta() {
        return punta;
    }

    /**
     * @return cuantas piezas tiene el impacto
     */
    public int getCantidadImpactos() {
        return impacto.length;
    }

    /**
     * @param fase numero de pieza del impacto (se alternan)
     * @return la punta del rayo mientras golpea a alguien
     */
    public BufferedImage getImpacto(int fase) {
        return impacto[fase];
    }

    /**
     * @return los anillos de energia que aparecen en las manos mientras sale el rayo
     */
    public BufferedImage[] getAnillos() {
        return anillos;
    }

    /**
     * @return el alto de referencia del cuerpo del rayo en la hoja (sin escalar)
     */
    public int getAltoCuerpo() {
        return ALTO_CUERPO;
    }

    /**
     * @return la escala de dibujo de las piezas
     */
    public double getEscala() {
        return ESCALA;
    }
}
