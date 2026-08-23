package colision;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Un personaje cuadrado con un hitbox (caja de colisiones).
 */
public class Personaje {

    private final String nombre;
    private final BufferedImage sprite;
    private final int ancho;
    private final int alto;
    private final Color colorReposo;

    private double x;
    private double y;
    private boolean golpeado;

    /**
     * Crea un personaje ubicado en (x, y) con su sprite y color de reposo.
     *
     * @param nombre etiqueta que se dibuja arriba del personaje
     * @param sprite imagen a dibujar dentro del hitbox
     * @param x posicion horizontal inicial
     * @param y posicion vertical inicial
     * @param ancho ancho del hitbox
     * @param alto alto del hitbox
     * @param colorReposo color del borde cuando no hay colision
     */
    public Personaje(String nombre, BufferedImage sprite, double x, double y, int ancho, int alto, Color colorReposo) {
        this.nombre = nombre;
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorReposo = colorReposo;
    }

    /**
     * Desplaza al personaje y lo mantiene dentro de los limites de la arena.
     *
     * @param dx desplazamiento horizontal
     * @param dy desplazamiento vertical
     * @param anchoArena ancho del area donde se puede mover
     * @param altoArena alto del area donde se puede mover
     */
    public void mover(double dx, double dy, int anchoArena, int altoArena) {
        x = limitar(x + dx, 0, anchoArena - ancho);
        y = limitar(y + dy, 0, altoArena - alto);
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
     * @return el rectangulo que representa el hitbox (caja de colisiones) actual del personaje
     */
    public Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, ancho, alto);
    }

    /**
     * Marca si el personaje esta colisionando con otro, para que se refleje en su color de borde.
     *
     * @param golpeado true si su hitbox se solapa con el de otro personaje
     */
    public void setGolpeado(boolean golpeado) {
        this.golpeado = golpeado;
    }

    /**
     * @return true si el personaje esta colisionando con otro
     */
    public boolean isGolpeado() {
        return golpeado;
    }

    /**
     * @return la etiqueta del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el sprite del personaje
     */
    public BufferedImage getSprite() {
        return sprite;
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
     * @return el ancho del hitbox
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return el alto del hitbox
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @return el color de borde cuando no hay colision
     */
    public Color getColorReposo() {
        return colorReposo;
    }
}
