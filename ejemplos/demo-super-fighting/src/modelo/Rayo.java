package modelo;

/**
 * El rayo del super (Shinku Hadoken de Ken, Kamehameha de Goku). Sale de las
 * manos del luchador, crece hasta el borde de la arena y dura un tiempo fijo.
 * Mientras el rival este dentro del rayo, lo golpea varias veces seguidas.
 *
 * El rayo se corta cuando su duenio sale del super (por ejemplo, si le pegan
 * mientras lo lanza): lo hace {@link Luchador#forzarEstado}.
 */
public class Rayo {

    /** Pixeles por tick que crece el rayo. */
    private static final double VELOCIDAD_CRECIMIENTO = 60;
    private static final int DURACION_MS = 1300;
    private static final int INTERVALO_GOLPE_MS = 130;
    private static final int ALTO_HITBOX = 120;

    private final Luchador duenio;
    private final double origenX;
    private final double y;
    private final int direccion;

    private double largo;
    private int tiempoMs;
    private int temporizadorGolpe;
    private boolean activo = true;
    private boolean impactando;

    /**
     * @param duenio    luchador que lo lanza
     * @param origenX   posicion horizontal de las manos
     * @param y         altura del centro del rayo
     * @param direccion 1 = hacia la derecha, -1 = hacia la izquierda
     */
    public Rayo(Luchador duenio, double origenX, double y, int direccion) {
        this.duenio = duenio;
        this.origenX = origenX;
        this.y = y;
        this.direccion = direccion;
    }

    /**
     * Hace crecer el rayo hasta el borde de la arena (o hasta el choque con otro
     * rayo) y descuenta su duracion.
     *
     * @param deltaMs    milisegundos transcurridos desde la ultima actualizacion
     * @param anchoArena ancho de la arena
     */
    public void actualizar(int deltaMs, int anchoArena) {
        tiempoMs += deltaMs;
        temporizadorGolpe -= deltaMs;
        if (tiempoMs >= DURACION_MS) {
            activo = false;
        }

        double hastaElBorde = direccion > 0 ? anchoArena - origenX : origenX;
        largo = Math.min(largo + VELOCIDAD_CRECIMIENTO, hastaElBorde);

        /* Se recalcula en cada tick: la pelea lo marca si esta tocando al rival */
        impactando = false;
    }

    /**
     * Recorta el rayo (se usa cuando choca con otro rayo: los dos se frenan en el
     * punto de encuentro).
     *
     * @param maximo largo maximo permitido
     */
    public void limitarLargo(double maximo) {
        largo = Math.min(largo, Math.max(0, maximo));
    }

    /**
     * Corta el rayo (su duenio salio del super).
     */
    public void desactivar() {
        activo = false;
    }

    /**
     * Marca que la punta esta tocando al rival (se dibuja la punta de impacto).
     */
    public void marcarImpacto() {
        impactando = true;
    }

    /**
     * @return true si ya paso el intervalo desde el ultimo golpe
     */
    public boolean puedeGolpear() {
        return temporizadorGolpe <= 0;
    }

    /**
     * Registra que el rayo golpeo, para esperar el intervalo antes del proximo
     * golpe.
     */
    public void registrarGolpe() {
        temporizadorGolpe = INTERVALO_GOLPE_MS;
    }

    /**
     * @return la caja de colision: desde las manos hasta la punta
     */
    public CajaColision getHitbox() {
        double izquierda = direccion > 0 ? origenX : origenX - largo;
        return new CajaColision(izquierda, y - ALTO_HITBOX / 2.0, largo, ALTO_HITBOX);
    }

    /**
     * @return la posicion horizontal de la punta
     */
    public double getPuntaX() {
        return origenX + direccion * largo;
    }

    /**
     * @return true si sigue en juego
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @return true si en el ultimo tick estaba tocando al rival (se dibuja la
     *         punta de impacto)
     */
    public boolean isImpactando() {
        return impactando;
    }

    /**
     * @return el luchador que lo lanzo
     */
    public Luchador getDuenio() {
        return duenio;
    }

    /**
     * @return la posicion horizontal de las manos
     */
    public double getOrigenX() {
        return origenX;
    }

    /**
     * @return la altura del centro del rayo
     */
    public double getY() {
        return y;
    }

    /**
     * @return 1 si va a la derecha, -1 si va a la izquierda
     */
    public int getDireccion() {
        return direccion;
    }

    /**
     * @return el largo actual
     */
    public double getLargo() {
        return largo;
    }

    /**
     * @return los milisegundos desde que salio
     */
    public int getTiempoMs() {
        return tiempoMs;
    }
}
