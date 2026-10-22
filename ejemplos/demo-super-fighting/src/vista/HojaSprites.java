package vista;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Hoja de sprites de la que se recortan los fotogramas de cada animacion.
 *
 * Las hojas de este proyecto no son una grilla regular (cada fotograma tiene su
 * propio tamanio), por eso cada fotograma se indica con su rectangulo
 * {x, y, ancho, alto} dentro de la hoja.
 *
 * Todos los fotogramas que devuelve miran a la derecha: si la hoja tiene al
 * personaje mirando a la izquierda, se espejan al recortarlos. Asi el resto del
 * juego puede suponer siempre lo mismo.
 */
public class HojaSprites {

    private final BufferedImage imagen;
    private final IFondoHoja fondo;
    private final boolean miraALaIzquierda;

    /**
     * @param original         imagen de la hoja tal como se cargo del disco
     * @param fondo            el fondo de la hoja, que se quita de cada fotograma
     * @param miraALaIzquierda true si en la hoja el personaje mira a la izquierda
     *                         (los fotogramas se espejan al recortarlos)
     */
    public HojaSprites(BufferedImage original, IFondoHoja fondo, boolean miraALaIzquierda) {
        this.fondo = fondo;
        this.miraALaIzquierda = miraALaIzquierda;

        /* Se copia a una imagen ARGB para poder tener pixeles transparentes */
        imagen = copiar(original);
    }

    /**
     * Recorta varios fotogramas de la hoja.
     *
     * @param rectangulos un {x, y, ancho, alto} por fotograma, en orden
     * @return los fotogramas recortados
     */
    public BufferedImage[] recortar(int[][] rectangulos) {
        BufferedImage[] fotogramas = new BufferedImage[rectangulos.length];
        for (int i = 0; i < rectangulos.length; i++) {
            int[] rectangulo = rectangulos[i];
            fotogramas[i] = copiar(imagen.getSubimage(rectangulo[0], rectangulo[1], rectangulo[2], rectangulo[3]));
            fondo.quitar(fotogramas[i]);
            if (miraALaIzquierda) {
                fotogramas[i] = espejar(fotogramas[i]);
            }
        }
        return fotogramas;
    }

    /**
     * @param fotograma imagen original
     * @return una copia espejada horizontalmente (lo que miraba a la izquierda
     *         pasa a mirar a la derecha)
     */
    private static BufferedImage espejar(BufferedImage fotograma) {
        int ancho = fotograma.getWidth();
        BufferedImage espejado = new BufferedImage(ancho, fotograma.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = espejado.createGraphics();
        /* Mismo truco que al dibujar: las esquinas de destino al reves */
        g.drawImage(fotograma, ancho, 0, 0, fotograma.getHeight(), 0, 0, ancho, fotograma.getHeight(), null);
        g.dispose();
        return espejado;
    }

    /**
     * @param origen una imagen (o una parte de la hoja)
     * @return una copia ARGB independiente (modificarla no modifica el origen)
     */
    private static BufferedImage copiar(BufferedImage origen) {
        BufferedImage copia = new BufferedImage(origen.getWidth(), origen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copia.createGraphics();
        g.drawImage(origen, 0, 0, null);
        g.dispose();
        return copia;
    }
}
