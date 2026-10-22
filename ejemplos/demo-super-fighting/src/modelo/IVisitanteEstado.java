package modelo;

/**
 * Visitante de los estados de un luchador (patron Visitor). Cada estado llama
 * al metodo que le corresponde ({@code visitante.visitar(this)}), asi quien
 * visita recibe el estado concreto por despacho doble y guarda lo que
 * necesita de esa visita.
 *
 * Esta en el modelo y la implementa la vista ({@code vista.AparienciaLuchador}
 * elige los dibujos de cada estado): la vista depende del modelo.
 *
 * <pre>
 *  luchador.aceptar(v) --&gt; estado.aceptar(v) --&gt; v.visitar(this)
 *                            EstadoSuper           visitar(EstadoSuper)
 * </pre>
 */
public interface IVisitanteEstado {

    /**
     * @param estado el estado libre (QUIETO)
     */
    void visitar(EstadoLibre estado);

    /**
     * @param estado el estado de super
     */
    void visitar(EstadoSuper estado);

    /**
     * @param estado el estado de golpeado
     */
    void visitar(EstadoGolpeado estado);
}
