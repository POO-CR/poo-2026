package animacion;

import java.awt.image.BufferedImage;

/**
 * Personaje que se mueve por el panel y anima su ciclo de caminata mientras se mueve.
 */
public class Personaje {

    private static final int RETARDO_FOTOGRAMA_MS = 80;

    private final BufferedImage[] fotogramas;
    private final int ancho;
    private final int alto;

    private double x;
    private double y;
    private int indiceFotograma;
    private int temporizadorFotograma;
    private boolean mirandoIzquierda;

    /**
     * Crea el personaje en (x, y) con los fotogramas de su ciclo de caminata.
     *
     * @param fotogramas fotogramas del ciclo de caminata, en orden
     * @param x posicion horizontal inicial
     * @param y posicion vertical inicial
     * @param ancho ancho con el que se dibuja el sprite
     * @param alto alto con el que se dibuja el sprite
     */
    public Personaje(BufferedImage[] fotogramas, double x, double y, int ancho, int alto) {
        this.fotogramas = fotogramas;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Desplaza al personaje y lo mantiene dentro de los limites del panel.
     *
     * @param dx desplazamiento horizontal
     * @param dy desplazamiento vertical
     * @param anchoArea ancho del area donde se puede mover
     * @param altoArea alto del area donde se puede mover
     */
    public void mover(double dx, double dy, int anchoArea, int altoArea) {
        if (dx < 0) mirandoIzquierda = true;
        else if (dx > 0) mirandoIzquierda = false;

        x = limitar(x + dx, 0, Math.max(0, anchoArea - ancho));
        y = limitar(y + dy, 0, Math.max(0, altoArea - alto));
    }

    /**
     * Avanza el ciclo de caminata solo si el personaje se esta moviendo; si esta quieto, lo congela en el primer fotograma.
     *
     * @param deltaMs milisegundos transcurridos desde la ultima actualizacion
     * @param moviendose true si el personaje se desplazo en este tick
     */
    public void actualizarAnimacion(int deltaMs, boolean moviendose) {
        if (!moviendose) {
            temporizadorFotograma = 0;
            indiceFotograma = 0;
            return;
        }

        temporizadorFotograma += deltaMs;
        if (temporizadorFotograma >= RETARDO_FOTOGRAMA_MS) {
            temporizadorFotograma -= RETARDO_FOTOGRAMA_MS;
            indiceFotograma = (indiceFotograma + 1) % fotogramas.length;
        }
    }

    /**
     * Restringe un valor al rango [min, max].
     *
     * @param v valor a limitar
     * @param min limite inferior
     * @param max limite superior
     * @return el valor recortado al rango indicado
     */
    private static double limitar(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }

    /**
     * @return el indice del fotograma actual dentro del ciclo de caminata
     */
    public int getIndiceFotograma() {
        return indiceFotograma;
    }

    /**
     * @return la cantidad total de fotogramas del ciclo de caminata
     */
    public int getCantidadFotogramas() {
        return fotogramas.length;
    }

    /**
     * @return el fotograma actual del ciclo de caminata
     */
    public BufferedImage getFotogramaActual() {
        return fotogramas[indiceFotograma];
    }

    /**
     * @return la posicion horizontal actual
     */
    public double getX() {
        return x;
    }

    /**
     * @return la posicion vertical actual
     */
    public double getY() {
        return y;
    }

    /**
     * @return el ancho con el que se dibuja el sprite
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return el alto con el que se dibuja el sprite
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @return true si el personaje mira hacia la izquierda
     */
    public boolean isMirandoIzquierda() {
        return mirandoIzquierda;
    }
}
