package modelo;

/**
 * Estado en el que el jugador maneja al luchador (QUIETO). Desde aca puede
 * empezar el super.
 */
public class EstadoLibre implements IEstadoLuchador {

    @Override
    public Animacion getAnimacion(Luchador luchador) {
        return luchador.getAnimacionQuieto();
    }

    @Override
    public void actualizar(Luchador luchador, Comandos comandos, int deltaMs, Luchador rival) {
        luchador.mirarHacia(rival);

        if (comandos.isSuperAtaque()) {
            luchador.empezarSuper();
        }
    }

    @Override
    public void aceptar(IVisitanteEstado visitante) {
        visitante.visitar(this);
    }
}
