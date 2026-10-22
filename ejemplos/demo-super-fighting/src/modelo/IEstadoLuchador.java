package modelo;

/**
 * Estado de un luchador (patron State). Cada estado es una clase que decide
 * como se comporta el luchador mientras esta en el: que animacion muestra, que
 * hace con los comandos y cuando pasa a otro estado. {@link Luchador} le
 * delega el comportamiento a su estado actual.
 *
 * <pre>
 *        IEstadoLuchador
 *    /         |           * Libre      Super      Golpeado
 * </pre>
 */
public interface IEstadoLuchador {

    /**
     * @param luchador el luchador que esta en este estado
     * @return la animacion de este estado, tomada del luchador
     */
    Animacion getAnimacion(Luchador luchador);

    /**
     * Avanza un tick en este estado.
     *
     * @param luchador el luchador que esta en este estado
     * @param comandos lo que pide el jugador en este tick
     * @param deltaMs  milisegundos transcurridos
     * @param rival    el otro luchador
     */
    void actualizar(Luchador luchador, Comandos comandos, int deltaMs, Luchador rival);

    /**
     * Recibe a un visitante y le pasa este estado concreto (patron Visitor).
     *
     * @param visitante quien visita
     */
    void aceptar(IVisitanteEstado visitante);
}
