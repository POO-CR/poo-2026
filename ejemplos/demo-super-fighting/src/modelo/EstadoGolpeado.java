package modelo;

/**
 * Aturdido por un golpe: el luchador espera quieto hasta que pasa el
 * aturdimiento. Cada golpe crea un estado nuevo, asi un segundo golpe reinicia
 * el tiempo.
 */
public class EstadoGolpeado implements IEstadoLuchador {

    private int restanteMs;

    /**
     * @param duracionMs cuanto dura el aturdimiento
     */
    public EstadoGolpeado(int duracionMs) {
        this.restanteMs = duracionMs;
    }

    @Override
    public Animacion getAnimacion(Luchador luchador) {
        return luchador.getAnimacionGolpeado();
    }

    @Override
    public void actualizar(Luchador luchador, Comandos comandos, int deltaMs, Luchador rival) {
        restanteMs -= deltaMs;
        if (restanteMs <= 0) {
            luchador.forzarEstado(new EstadoLibre());
        }
    }

    @Override
    public void aceptar(IVisitanteEstado visitante) {
        visitante.visitar(this);
    }
}
