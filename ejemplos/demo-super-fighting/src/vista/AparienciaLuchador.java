package vista;

import modelo.Animacion;
import modelo.EstadoGolpeado;
import modelo.EstadoLibre;
import modelo.EstadoSuper;
import modelo.IVisitanteEstado;
import modelo.Luchador;

/**
 * Como se ve un luchador: los dibujos de cada estado, su escala, la imagen de
 * su super y el nombre del super. Es la parte visual de un {@link Luchador}: el
 * modelo decide que hace y en que fotograma va; esta clase sabe como se ve ese
 * fotograma.
 *
 * Es visitante de los estados ({@link IVisitanteEstado}, patron Visitor): el
 * panel le pide al luchador {@code luchador.aceptar(apariencia)}, el estado
 * actual llama al {@code visitar} que le corresponde y la apariencia guarda los
 * dibujos de ese estado, que el panel lee con {@link #getFotogramasElegidos()}.
 *
 * Las subclases ({@link AparienciaKen}, {@link AparienciaGoku}) recortan sus
 * hojas de sprites y le pasan lo suyo al constructor, igual que
 * {@link modelo.Ken} y {@link modelo.Goku} pasan sus tiempos en el modelo.
 *
 * <pre>
 *   modelo                         vista
 *   Luchador                       AparienciaLuchador
 *     Ken   (tiempos)                AparienciaKen   (hojas, dibujos)
 *     Goku                           AparienciaGoku
 * </pre>
 */
public abstract class AparienciaLuchador implements IVisitanteEstado {

    private final double escala;
    private final Fotogramas quieto;
    private final Fotogramas superAtaque;
    private final Fotogramas golpeado;
    private final Retrato retrato;
    private final String nombreSuper;

    /** Los dibujos del estado de la ultima visita. */
    private Fotogramas fotogramasElegidos;

    /**
     * Recibe los dibujos de cada estado, con tantos fotogramas como dice la
     * animacion del modelo.
     *
     * @param escala      cuanto se agranda el sprite al dibujarlo
     * @param quieto      dibujos de la guardia
     * @param superAtaque dibujos de la pose del super
     * @param golpeado    dibujos de la reaccion a un golpe
     * @param retrato     imagen del personaje que se muestra al lanzar el super
     * @param nombreSuper nombre del super, que se muestra en pantalla al lanzarlo
     */
    protected AparienciaLuchador(double escala, Fotogramas quieto, Fotogramas superAtaque, Fotogramas golpeado,
            Retrato retrato, String nombreSuper) {
        this.escala = escala;
        this.quieto = quieto;
        this.superAtaque = superAtaque;
        this.golpeado = golpeado;
        this.retrato = retrato;
        this.nombreSuper = nombreSuper;
    }

    /**
     * Revisa que los dibujos de cada estado del luchador tengan tantos
     * fotogramas como su animacion en el modelo. Si no coinciden (por ejemplo,
     * se agrego un fotograma en la hoja y falta en el modelo), el juego se
     * detiene al arrancar y dice cual es el problema.
     *
     * @param luchador el luchador del modelo que se dibuja con esta apariencia
     */
    public void verificar(Luchador luchador) {
        verificar(luchador, "QUIETO", luchador.getAnimacionQuieto(), quieto);
        verificar(luchador, "SUPER", luchador.getAnimacionSuper(), superAtaque);
        verificar(luchador, "GOLPEADO", luchador.getAnimacionGolpeado(), golpeado);
    }

    /**
     * @param luchador  el luchador revisado
     * @param nombre    nombre de la animacion, para el mensaje de error
     * @param animacion tiempos de la animacion en el modelo
     * @param dibujos   dibujos de esa animacion en la vista
     */
    private void verificar(Luchador luchador, String nombre, Animacion animacion, Fotogramas dibujos) {
        int enModelo = animacion.getCantidad();
        int enVista = dibujos.getCantidad();
        if (enModelo == 0 || enModelo != enVista) {
            throw new IllegalStateException(luchador.getNombre() + ", " + nombre + ": el modelo tiene "
                    + enModelo + " fotogramas y la vista " + enVista + " dibujos");
        }
    }

    @Override
    public void visitar(EstadoLibre estado) {
        fotogramasElegidos = quieto;
    }

    @Override
    public void visitar(EstadoSuper estado) {
        fotogramasElegidos = superAtaque;
    }

    @Override
    public void visitar(EstadoGolpeado estado) {
        fotogramasElegidos = golpeado;
    }

    /**
     * @return los dibujos del estado de la ultima visita
     */
    public Fotogramas getFotogramasElegidos() {
        return fotogramasElegidos;
    }

    /**
     * @return cuanto se agranda el sprite al dibujarlo
     */
    public double getEscala() {
        return escala;
    }

    /**
     * @return la imagen del super
     */
    public Retrato getRetrato() {
        return retrato;
    }

    /**
     * @return el nombre del super
     */
    public String getNombreSuper() {
        return nombreSuper;
    }
}
