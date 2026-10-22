package modelo;

/**
 * Super: al llegar al fotograma de lanzamiento sale el rayo (una sola vez), y
 * la pose final se mantiene mientras el rayo siga en pantalla. Si el luchador
 * sale de este estado antes (por ejemplo, porque lo golpean), el rayo se corta.
 */
public class EstadoSuper implements IEstadoLuchador {

    @Override
    public Animacion getAnimacion(Luchador luchador) {
        return luchador.getAnimacionSuper();
    }

    @Override
    public void actualizar(Luchador luchador, Comandos comandos, int deltaMs, Luchador rival) {
        if (luchador.isLanzamientoAlcanzado()) {
            luchador.lanzarRayo();
        }
        if (luchador.isAnimacionTerminada() && !luchador.isRayoActivo()) {
            luchador.forzarEstado(new EstadoLibre());
        }
    }

    @Override
    public void aceptar(IVisitanteEstado visitante) {
        visitante.visitar(this);
    }
}
