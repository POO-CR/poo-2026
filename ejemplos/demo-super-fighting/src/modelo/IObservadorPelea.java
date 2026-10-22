package modelo;

/**
 * Observador de la pelea (patron Observer). La {@link Pelea} guarda una lista
 * de observadores y los llama al final de cada cambio; cada observador lee de
 * la pelea lo que necesita.
 *
 * Esta en el modelo y la implementa la vista ({@code vista.PanelPelea}): la
 * vista depende del modelo.
 */
public interface IObservadorPelea {

    /**
     * Se llama despues de cada cambio de la pelea.
     *
     * @param pelea la pelea que cambio
     */
    void peleaCambio(Pelea pelea);
}
