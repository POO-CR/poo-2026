package modelo;

/**
 * Chispa de un golpe: donde conecto y cuanto tiempo lleva en pantalla.
 */
public class Impacto {

    private static final int DURACION_MS = 220;

    private final double x;
    private final double y;
    private int restanteMs = DURACION_MS;

    /**
     * @param x posicion horizontal del golpe
     * @param y posicion vertical del golpe
     */
    public Impacto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param deltaMs milisegundos transcurridos desde la ultima actualizacion
     */
    public void actualizar(int deltaMs) {
        restanteMs -= deltaMs;
    }

    /**
     * @return true si ya termino de mostrarse
     */
    public boolean terminado() {
        return restanteMs <= 0;
    }

    /**
     * @return cuanto avanzo la chispa, de 0 (recien aparece) a 1 (desaparece)
     */
    public double getProgreso() {
        return 1.0 - Math.max(0, restanteMs) / (double) DURACION_MS;
    }

    /**
     * @return la posicion horizontal
     */
    public double getX() {
        return x;
    }

    /**
     * @return la posicion vertical
     */
    public double getY() {
        return y;
    }
}
