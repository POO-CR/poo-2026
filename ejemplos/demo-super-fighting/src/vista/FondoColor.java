package vista;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Fondo de color liso (por ejemplo el verde agua de la hoja de Capcom vs.
 * SNK): los pixeles parecidos a ese color se vuelven transparentes.
 */
public class FondoColor implements IFondoHoja {

    /**
     * Distancia de color al fondo por debajo de la cual un pixel es fondo (queda
     * transparente), para las hojas con fondo de color.
     */
    private static final double DISTANCIA_FONDO = 35;

    /**
     * Distancia de color al fondo a partir de la cual un pixel es del personaje
     * (queda opaco). Entre las dos distancias el pixel queda semitransparente: es
     * el borde que el achique de la hoja mezclo con el fondo.
     */
    private static final double DISTANCIA_PERSONAJE = 65;

    /**
     * Cuanto mas verde que rojo tiene que tener un pixel para considerarlo borde
     * mezclado con el fondo verde agua.
     */
    private static final int MARGEN_TINTE = 8;

    /**
     * Brillo (suma de rojo, verde y azul) a partir del cual se aplica
     * {@link #MARGEN_TINTE}: las mezclas con el fondo son claras, y asi no se
     * borran los negros del personaje, que a veces tienen un poco mas de verde.
     */
    private static final int BRILLO_MINIMO_TINTE = 240;

    private final Color colorFondo;

    /**
     * @param colorFondo color del fondo a quitar
     */
    public FondoColor(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    /**
     * Vuelve transparentes los pixeles parecidos al color de fondo. A diferencia
     * del fondo negro, aca se revisan todos los pixeles (no solo los conectados
     * al borde): el verde del fondo nunca aparece en el personaje, y asi tambien
     * se borran los huecos de fondo encerrados, por ejemplo entre un brazo y el
     * cuerpo.
     *
     * <pre>
     *  alfa
     *  255 |                 +--------  personaje
     *      |                /
     *      |               /   borde mezclado con el fondo
     *    0 +--------------+
     *      0      DISTANCIA_FONDO  DISTANCIA_PERSONAJE    distancia al color de fondo
     * </pre>
     *
     * Ademas, el achique de la hoja dejo en el contorno pixeles mezclados con el
     * fondo que quedan lejos del verde agua puro pero "tiran" a ese color: tienen
     * mas verde que rojo. En el personaje eso no pasa (el rojo del traje, la piel,
     * el pelo amarillo y los guantes tienen menos verde que rojo), asi que esos
     * pixeles tambien se borran: si no, se ve un halo claro alrededor.
     *
     * @param fotograma imagen a modificar
     */
    @Override
    public void quitar(BufferedImage fotograma) {
        for (int y = 0; y < fotograma.getHeight(); y++) {
            for (int x = 0; x < fotograma.getWidth(); x++) {
                int argb = fotograma.getRGB(x, y);
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                double distancia = Math.sqrt(Math.pow(r - colorFondo.getRed(), 2)
                        + Math.pow(g - colorFondo.getGreen(), 2) + Math.pow(b - colorFondo.getBlue(), 2));
                /* Solo los claros: los oscuros (cinturon, contorno) pueden tener algo mas de verde */
                boolean tiraAlFondo = g - r > MARGEN_TINTE && r + g + b > BRILLO_MINIMO_TINTE;

                if (tiraAlFondo) {
                    fotograma.setRGB(x, y, 0);
                } else if (distancia < DISTANCIA_PERSONAJE) {
                    double opacidad = Math.max(0, (distancia - DISTANCIA_FONDO) / (DISTANCIA_PERSONAJE - DISTANCIA_FONDO));
                    int alfa = (int) (255 * opacidad);
                    fotograma.setRGB(x, y, (alfa << 24) | (argb & 0x00FFFFFF));
                }
            }
        }
        limpiarBorde(fotograma);
    }

    /**
     * Quita el halo del contorno ("defringe"): cada pixel del borde (visible y
     * pegado a uno transparente) toma el color promedio de sus vecinos del
     * interior del personaje. La silueta no cambia, solo el color de su orilla,
     * que el achique de la hoja habia mezclado con el fondo. Los pixeles de borde
     * que no tienen ningun vecino del interior son puntos sueltos de ruido y se
     * borran.
     *
     * <pre>
     *  . . . .      . = transparente
     *  . b b .      b = borde: toma el color promedio de sus vecinos i
     *  . b i i      i = interior (no se toca)
     * </pre>
     *
     * @param fotograma imagen a modificar
     */
    private static void limpiarBorde(BufferedImage fotograma) {
        int ancho = fotograma.getWidth();
        int alto = fotograma.getHeight();
        int[] pixeles = fotograma.getRGB(0, 0, ancho, alto, null, 0, ancho);
        int[] resultado = pixeles.clone();

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                if (esBorde(pixeles, ancho, alto, x, y)) {
                    int sumaR = 0;
                    int sumaG = 0;
                    int sumaB = 0;
                    int cantidad = 0;
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int vx = x + dx;
                            int vy = y + dy;
                            if (vx >= 0 && vy >= 0 && vx < ancho && vy < alto
                                    && visible(pixeles[vy * ancho + vx]) && !esBorde(pixeles, ancho, alto, vx, vy)) {
                                int vecino = pixeles[vy * ancho + vx];
                                sumaR += (vecino >> 16) & 0xFF;
                                sumaG += (vecino >> 8) & 0xFF;
                                sumaB += vecino & 0xFF;
                                cantidad++;
                            }
                        }
                    }
                    if (cantidad > 0) {
                        int alfa = pixeles[y * ancho + x] & 0xFF000000;
                        resultado[y * ancho + x] = alfa | (sumaR / cantidad << 16) | (sumaG / cantidad << 8)
                                | (sumaB / cantidad);
                    } else {
                        /* Pixel suelto, sin interior al lado: es ruido del fondo */
                        resultado[y * ancho + x] = 0;
                    }
                }
            }
        }
        fotograma.setRGB(0, 0, ancho, alto, resultado, 0, ancho);
    }

    /**
     * @param argb color de un pixel
     * @return true si el pixel es casi opaco
     */
    private static boolean visible(int argb) {
        return (argb >>> 24) > 128;
    }

    /**
     * @return true si el pixel (x, y) es visible y tiene algun vecino transparente
     *         (o esta en el borde de la imagen)
     */
    private static boolean esBorde(int[] pixeles, int ancho, int alto, int x, int y) {
        if (!visible(pixeles[y * ancho + x])) {
            return false;
        }
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int vx = x + dx;
                int vy = y + dy;
                if (vx < 0 || vy < 0 || vx >= ancho || vy >= alto || !visible(pixeles[vy * ancho + vx])) {
                    return true;
                }
            }
        }
        return false;
    }
}
