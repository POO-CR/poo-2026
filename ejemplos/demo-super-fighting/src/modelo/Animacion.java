package modelo;

/**
 * Los tiempos de una animacion por fotogramas (flipbook): cuantos fotogramas
 * tiene, cuanto se muestra cada uno y si al terminar vuelve a empezar.
 *
 * El modelo necesita esto porque las reglas del juego dependen del fotograma:
 * un golpe pega solo en algunos fotogramas, el Hadoken sale en uno en
 * particular, un ataque termina cuando termina su animacion. Como se ve cada
 * fotograma no le importa al modelo: eso esta en la vista
 * ({@code vista.Fotogramas}), que tiene un dibujo por cada fotograma.
 */
public class Animacion {

    private final int cantidad;
    private final int retardoMs;
    private final boolean enLoop;

    /**
     * @param cantidad  cantidad de fotogramas
     * @param retardoMs milisegundos que se muestra cada fotograma
     * @param enLoop    true si al terminar vuelve a empezar, false si se queda en
     *                  el ultimo fotograma
     */
    public Animacion(int cantidad, int retardoMs, boolean enLoop) {
        this.cantidad = cantidad;
        this.retardoMs = retardoMs;
        this.enLoop = enLoop;
    }

    /**
     * @return la cantidad de fotogramas
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @return los milisegundos que se muestra cada fotograma
     */
    public int getRetardoMs() {
        return retardoMs;
    }

    /**
     * @return true si la animacion se repite
     */
    public boolean isEnLoop() {
        return enLoop;
    }
}
