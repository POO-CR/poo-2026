package salto;

import java.awt.image.BufferedImage;

/**
 * Personaje afectado por gravedad, que salta al presionar la tecla de salto (solo si esta en el suelo).
 */
public class Personaje {

    private static final double GRAVEDAD = 0.6;
    private static final double IMPULSO_SALTO = -14.0;

    /** Al soltar la tecla de salto en pleno ascenso, la velocidad vertical se multiplica por esto (salto corto). */
    private static final double FACTOR_CORTE_SALTO = 0.5;

    /** Altura maxima que alcanza un salto completo, segun el impulso y la gravedad (formula de caida libre: v0^2 / (2*g)). */
    private static final double ALTURA_MAXIMA_SALTO = (IMPULSO_SALTO * IMPULSO_SALTO) / (2 * GRAVEDAD);

    private final BufferedImage sprite;
    private final int ancho;
    private final int alto;

    private double x;
    private double y;
    private double alturaSuelo;
    private double velocidadY;
    private boolean enSuelo = true;
    private boolean teclaSaltoPresionadaAntes;
    private boolean saltoVariableHabilitado = true;

    /**
     * Crea el personaje en (x, y) con su sprite.
     *
     * @param sprite imagen del personaje
     * @param x posicion horizontal inicial
     * @param y posicion vertical inicial (se espera que sea la altura del suelo)
     * @param ancho ancho con el que se dibuja el sprite
     * @param alto alto con el que se dibuja el sprite
     */
    public Personaje(BufferedImage sprite, double x, double y, int ancho, int alto) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.alturaSuelo = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Desplaza al personaje horizontalmente y lo mantiene dentro de los limites del panel.
     *
     * @param dx desplazamiento horizontal
     * @param anchoArea ancho del area donde se puede mover
     */
    public void moverHorizontal(double dx, int anchoArea) {
        x = limitar(x + dx, 0, Math.max(0, anchoArea - ancho));
    }

    /**
     * Aplica impulso de salto (o su corte), gravedad, y resuelve el choque contra el suelo.
     *
     * @param pidioSalto true si la tecla de salto esta presionada en este tick
     * @param alturaSuelo posicion Y del suelo
     */
    public void actualizarFisica(boolean pidioSalto, double alturaSuelo) {
        this.alturaSuelo = alturaSuelo;

        if (pidioSalto && enSuelo) {
            velocidadY = IMPULSO_SALTO;
            enSuelo = false;
        } else if (saltoVariableHabilitado && !pidioSalto && teclaSaltoPresionadaAntes && velocidadY < 0) {
            velocidadY *= FACTOR_CORTE_SALTO;
        }
        teclaSaltoPresionadaAntes = pidioSalto;

        velocidadY += GRAVEDAD;
        y += velocidadY;

        if (y >= alturaSuelo) {
            y = alturaSuelo;
            velocidadY = 0;
            enSuelo = true;
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
     * Activa o desactiva el corte de salto al soltar la tecla antes de tiempo.
     *
     * @param habilitado true = salto de altura variable, false = salto de altura fija
     */
    public void setSaltoVariable(boolean habilitado) {
        this.saltoVariableHabilitado = habilitado;
    }

    /**
     * @return true si el corte de salto esta habilitado (altura variable)
     */
    public boolean estaSaltoVariableHabilitado() {
        return saltoVariableHabilitado;
    }

    /**
     * @return la velocidad vertical actual (negativa = subiendo, positiva = cayendo)
     */
    public double getVelocidadY() {
        return velocidadY;
    }

    /**
     * @return true si el personaje esta apoyado en el suelo
     */
    public boolean estaEnSuelo() {
        return enSuelo;
    }

    /**
     * @return que tan cerca esta la altura actual del personaje de la altura maxima que puede alcanzar en un salto, en el rango [0, 1]
     */
    public double getProgresoAlturaMaxima() {
        double alturaActual = alturaSuelo - y;
        return limitar(alturaActual / ALTURA_MAXIMA_SALTO, 0, 1);
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
}
