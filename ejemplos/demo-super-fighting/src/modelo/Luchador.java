package modelo;

/**
 * Luchador generico: posicion, estado y animacion. Su comportamiento depende
 * del estado en que esta, y eso lo resuelve cada clase de estado
 * ({@link IEstadoLuchador}, patron State). Las subclases ({@link Ken},
 * {@link Goku}) solo pasan los tiempos de sus animaciones, el fotograma en que
 * sale el rayo y su altura. Cada estado toma del luchador la animacion que le corresponde.
 *
 * El luchador no tiene imagenes: sabe en que fotograma de cada animacion va
 * (de eso depende cuando sale el rayo), y la vista
 * ({@code vista.AparienciaLuchador}) sabe como se ve ese fotograma.
 *
 * <pre>
 *              super                     termina la animacion (y el rayo)
 *   QUIETO  &lt;---------------------------&gt;  SUPER
 *      |  recibe un golpe                         pasa el aturdimiento
 *      +--------------------&gt; GOLPEADO --------------------&gt; QUIETO
 * </pre>
 *
 * Coordenadas: {@code x} es el centro del cuerpo y {@code y} la altura de los
 * pies.
 */
public abstract class Luchador {

    private static final int ANCHO_CUERPO = 80;
    private static final int ALTO_CUERPO = 170;
    private static final int DURACION_ATURDIDO_MS = 350;
    /** Distancia horizontal del centro del cuerpo a las manos, de donde sale el rayo. */
    private static final int DISTANCIA_MANOS = 75;

    private final String nombre;
    private final Animacion animacionQuieto;
    private final Animacion animacionSuper;
    private final Animacion animacionGolpeado;

    private double x;
    private double y;
    private boolean mirandoIzquierda;
    private IEstadoLuchador estado = new EstadoLibre();
    private final ReproductorAnimacion reproductor = new ReproductorAnimacion();

    /** El rayo del super en curso, o null si todavia no salio. */
    private Rayo rayo;
    /** true desde que empieza un super hasta que la pelea muestra su imagen. */
    private boolean retratoPendiente;

    /**
     * @param nombre            nombre del luchador
     * @param animacionQuieto   tiempos de la guardia
     * @param animacionSuper    tiempos de la pose del super
     * @param animacionGolpeado tiempos de la reaccion a un golpe
     */
    protected Luchador(String nombre, Animacion animacionQuieto, Animacion animacionSuper,
            Animacion animacionGolpeado) {
        this.nombre = nombre;
        this.animacionQuieto = animacionQuieto;
        this.animacionSuper = animacionSuper;
        this.animacionGolpeado = animacionGolpeado;
    }

    /**
     * @return el fotograma de la animacion SUPER en el que sale el rayo
     */
    protected abstract int getFotogramaLanzamientoSuper();

    /**
     * @return a cuantos pixeles sobre los pies sale el centro del rayo del super
     */
    protected abstract double getAlturaRayo();

    /**
     * Pone al luchador en su posicion inicial.
     *
     * @param x                posicion horizontal
     * @param sueloY           altura del suelo
     * @param mirandoIzquierda hacia donde mira
     */
    public void ubicar(double x, double sueloY, boolean mirandoIzquierda) {
        this.x = x;
        this.y = sueloY;
        this.mirandoIzquierda = mirandoIzquierda;
        forzarEstado(new EstadoLibre());
    }

    /**
     * Avanza un tick: el estado actual decide que hacer con los comandos (patron
     * State), y despues se avanza la animacion.
     *
     * @param comandos lo que pide el jugador en este tick
     * @param deltaMs  milisegundos transcurridos desde la ultima actualizacion
     * @param rival    el otro luchador (para mirar hacia el)
     */
    public void actualizar(Comandos comandos, int deltaMs, Luchador rival) {
        estado.actualizar(this, comandos, deltaMs, rival);
        reproductor.avanzar(deltaMs);
    }

    /* Acciones que usan los estados (IEstadoLuchador) */

    /**
     * Se da vuelta para mirar al rival.
     *
     * @param rival el otro luchador
     */
    public void mirarHacia(Luchador rival) {
        mirandoIzquierda = rival.getX() < x;
    }

    /**
     * @return true si la animacion actual (sin loop) llego a su ultimo fotograma
     */
    public boolean isAnimacionTerminada() {
        return reproductor.isTerminada();
    }

    /**
     * @return true si la animacion actual ya llego al fotograma en que sale el
     *         rayo del super
     */
    public boolean isLanzamientoAlcanzado() {
        return reproductor.getIndice() >= getFotogramaLanzamientoSuper();
    }

    /**
     * Empieza el super: pasa al estado de super y pide que la pelea muestre su
     * imagen.
     */
    public void empezarSuper() {
        forzarEstado(new EstadoSuper());
        retratoPendiente = true;
    }

    /**
     * Lanza el rayo del super desde las manos, una sola vez por super.
     */
    public void lanzarRayo() {
        if (rayo == null) {
            int direccion = getDireccion();
            rayo = new Rayo(this, x + direccion * DISTANCIA_MANOS, y - getAlturaRayo(), direccion);
        }
    }

    /**
     * @return true si el rayo del super en curso sigue en juego
     */
    public boolean isRayoActivo() {
        return rayo != null && rayo.isActivo();
    }

    /**
     * @return el numero de fotograma de la animacion actual
     */
    public int getIndiceFotograma() {
        return reproductor.getIndice();
    }

    /**
     * Cambia de estado: corta el rayo del super en curso (por ejemplo, si lo
     * golpean mientras lo lanza) y la animacion empieza desde el primer
     * fotograma de la del estado nuevo.
     *
     * @param nuevo estado nuevo
     */
    public void forzarEstado(IEstadoLuchador nuevo) {
        if (rayo != null) {
            rayo.desactivar();
            rayo = null;
        }
        estado = nuevo;
        reproductor.empezar(nuevo.getAnimacion(this));
    }

    /**
     * Recibe un golpe: queda aturdido en su lugar.
     */
    public void recibirGolpe() {
        /* Siempre un estado nuevo: un segundo golpe reinicia el aturdimiento */
        forzarEstado(new EstadoGolpeado(DURACION_ATURDIDO_MS));
    }

    /**
     * Si esta cargando el super, salta directo al fotograma en que sale el rayo. Se
     * usa al terminar la imagen del super: la imagen ya hizo de "carga", asi el
     * rayo sale enseguida en vez de repetir la animacion de preparacion.
     */
    public void saltarALanzamientoDelSuper() {
        if (!isLanzamientoAlcanzado()) {
            reproductor.irA(getFotogramaLanzamientoSuper());
        }
    }

    /**
     * @return el rayo del super en curso, o null si todavia no salio
     */
    public Rayo getRayo() {
        return rayo;
    }

    /**
     * @return la caja de colision del cuerpo
     */
    public CajaColision getHitbox() {
        return new CajaColision(x - ANCHO_CUERPO / 2.0, y - ALTO_CUERPO, ANCHO_CUERPO, ALTO_CUERPO);
    }

    /**
     * @return true si empezo un super y la pelea todavia no mostro su imagen
     */
    public boolean isRetratoPendiente() {
        return retratoPendiente;
    }

    /**
     * Registra que la pelea ya mostro la imagen de su super.
     */
    public void marcarRetratoMostrado() {
        retratoPendiente = false;
    }

    /**
     * Le pasa el estado actual a un visitante (patron Visitor): la vista lo usa
     * para elegir los dibujos del estado.
     *
     * @param visitante quien visita
     */
    public void aceptar(IVisitanteEstado visitante) {
        estado.aceptar(visitante);
    }

    /**
     * @return los tiempos de la guardia
     */
    public Animacion getAnimacionQuieto() {
        return animacionQuieto;
    }

    /**
     * @return los tiempos de la pose del super
     */
    public Animacion getAnimacionSuper() {
        return animacionSuper;
    }

    /**
     * @return los tiempos de la reaccion a un golpe
     */
    public Animacion getAnimacionGolpeado() {
        return animacionGolpeado;
    }

    /**
     * @return el nombre del luchador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return la posicion horizontal del centro del cuerpo
     */
    public double getX() {
        return x;
    }

    /**
     * @return la altura de los pies
     */
    public double getY() {
        return y;
    }

    /**
     * @return -1 si mira hacia la izquierda, 1 si mira hacia la derecha (sirve
     *         para ubicar delante suyo lo que lanza)
     */
    private int getDireccion() {
        return mirandoIzquierda ? -1 : 1;
    }

    /**
     * @return true si mira hacia la izquierda
     */
    public boolean isMirandoIzquierda() {
        return mirandoIzquierda;
    }
}
