package modelo;

/**
 * Algo que decide que hace un jugador en cada tick (aca, el teclado). El
 * controlador de la pelea trabaja con esta interfaz sin importarle de donde
 * vienen los comandos (polimorfismo).
 *
 * Por convencion del proyecto, los nombres de las interfaces empiezan con I.
 *
 * Esta en el modelo porque solo habla en terminos del modelo (devuelve
 * {@link Comandos}). La implementacion que lee al usuario (teclado) esta en el
 * paquete controlador: depende del modelo.
 */
public interface IFuenteComandos {

    /**
     * Se llama una vez por tick.
     *
     * @return lo que pide el jugador en este tick
     */
    Comandos leer();
}
