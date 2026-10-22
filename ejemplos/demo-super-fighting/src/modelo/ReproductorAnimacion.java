package modelo;

/**
 * Reproduce una {@link Animacion}: recuerda en que fotograma va y cuanto tiempo
 * lleva en el. La animacion en si no cambia (Ken y Goku tienen una por estado,
 * que se usa en todas las peleas); lo que avanza es el reproductor de cada
 * luchador.
 */
public class ReproductorAnimacion {

    private Animacion animacion;
    private int indice;
    private int temporizadorMs;
    private boolean terminada;

    /**
     * Crea un reproductor sin animacion (hay que darle una con {@link #empezar}).
     */
    public ReproductorAnimacion() {
    }

    /**
     * Pasa a reproducir una animacion desde su primer fotograma.
     *
     * @param animacion animacion a reproducir
     */
    public void empezar(Animacion animacion) {
        this.animacion = animacion;
        indice = 0;
        temporizadorMs = 0;
        terminada = false;
    }

    /**
     * Avanza el fotograma segun el tiempo transcurrido. Si la animacion no es en
     * loop, se queda en el ultimo fotograma y marca que termino.
     *
     * @param deltaMs milisegundos transcurridos desde la ultima actualizacion
     */
    public void avanzar(int deltaMs) {
        temporizadorMs += deltaMs;
        while (temporizadorMs >= animacion.getRetardoMs()) {
            temporizadorMs -= animacion.getRetardoMs();
            if (indice < animacion.getCantidad() - 1) {
                indice++;
            } else if (animacion.isEnLoop()) {
                indice = 0;
            } else {
                terminada = true;
            }
        }
    }

    /**
     * Salta a un fotograma.
     *
     * @param indice fotograma
     */
    public void irA(int indice) {
        this.indice = indice;
        temporizadorMs = 0;
    }

    /**
     * @return el numero de fotograma actual
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @return true si la animacion (sin loop) llego a su ultimo fotograma
     */
    public boolean isTerminada() {
        return terminada;
    }
}
