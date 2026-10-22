package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Los dos luchadores y sus supers. Es el modelo principal del juego: trabaja
 * con comandos, sin teclas ni dibujo.
 *
 * En cada tick ({@link #actualizar}):
 * <ol>
 * <li>Cada luchador interpreta sus comandos, aplica fisica y anima.</li>
 * <li>Se lanzan y resuelven los rayos de los supers.</li>
 * </ol>
 */
public class Pelea {

    /**
     * Cuanto dura la imagen del personaje al lanzar un super. Mientras se muestra,
     * la pelea queda congelada (como el "super flash" de Marvel vs. Capcom).
     */
    private static final int DURACION_RETRATO_MS = 1000;

    /** Cada cuantos ticks aparece una chispa donde chocan dos rayos. */
    private static final int TICKS_ENTRE_CHISPAS_CHOQUE = 5;

    private final Luchador jugador1;
    private final Luchador jugador2;
    private final int anchoArena;
    private final double sueloY;
    private final List<Rayo> rayos = new ArrayList<>();
    private final List<Impacto> impactos = new ArrayList<>();
    private final List<IObservadorPelea> observadores = new ArrayList<>();

    private int ticksChoqueRayos;
    private final List<Luchador> enRetrato = new ArrayList<>();
    private int tiempoRetratoMs;

    /* Supers pedidos mientras la pelea esta congelada: se aplican al terminar el congelado */
    private boolean superPendiente1;
    private boolean superPendiente2;

    /**
     * @param jugador1   luchador de la izquierda
     * @param jugador2   luchador de la derecha
     * @param anchoArena ancho de la arena
     * @param sueloY     altura del suelo
     */
    public Pelea(Luchador jugador1, Luchador jugador2, int anchoArena, double sueloY) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.anchoArena = anchoArena;
        this.sueloY = sueloY;
        reiniciar();
    }

    /**
     * Vuelve a las posiciones iniciales, sin rayos ni chispas.
     */
    public void reiniciar() {
        jugador1.ubicar(anchoArena * 0.25, sueloY, false);
        jugador2.ubicar(anchoArena * 0.75, sueloY, true);
        rayos.clear();
        impactos.clear();
        enRetrato.clear();
        tiempoRetratoMs = 0;
        ticksChoqueRayos = 0;
        superPendiente1 = false;
        superPendiente2 = false;
        notificar();
    }

    /**
     * Registra un observador, que se llama al final de cada cambio de la pelea
     * (patron Observer).
     *
     * @param observador el observador a registrar
     */
    public void agregarObservador(IObservadorPelea observador) {
        observadores.add(observador);
    }

    /**
     * Saca un observador de la lista: deja de recibir los avisos de la pelea.
     *
     * @param observador el observador a sacar
     */
    public void quitarObservador(IObservadorPelea observador) {
        observadores.remove(observador);
    }

    /**
     * Avisa a cada observador que la pelea cambio.
     */
    private void notificar() {
        for (IObservadorPelea observador : observadores) {
            observador.peleaCambio(this);
        }
    }

    /**
     * Avanza la pelea un tick.
     *
     * @param comandos1 lo que pide el jugador 1
     * @param comandos2 lo que pide el jugador 2
     * @param deltaMs   milisegundos transcurridos desde la ultima actualizacion
     */
    public void actualizar(Comandos comandos1, Comandos comandos2, int deltaMs) {
        /* Imagen del super en pantalla: todo queda congelado hasta que termine */
        if (!enRetrato.isEmpty()) {
            superPendiente1 = superPendiente1 || comandos1.isSuperAtaque();
            superPendiente2 = superPendiente2 || comandos2.isSuperAtaque();
            tiempoRetratoMs += deltaMs;
            if (tiempoRetratoMs >= DURACION_RETRATO_MS) {
                /* La imagen hizo de carga: el rayo sale en el proximo paso, sin repetir la preparacion */
                for (Luchador luchador : enRetrato) {
                    luchador.saltarALanzamientoDelSuper();
                }
                enRetrato.clear();
            }
            notificar();
            return;
        }

        Comandos efectivos1 = superPendiente1 ? new Comandos(true) : comandos1;
        Comandos efectivos2 = superPendiente2 ? new Comandos(true) : comandos2;
        superPendiente1 = false;
        superPendiente2 = false;

        jugador1.actualizar(efectivos1, deltaMs, jugador2);
        jugador2.actualizar(efectivos2, deltaMs, jugador1);
        mostrarRetratoSiEmpiezaSuper(jugador1);
        mostrarRetratoSiEmpiezaSuper(jugador2);

        agregarRayo(jugador1.getRayo());
        agregarRayo(jugador2.getRayo());
        actualizarRayos(deltaMs);

        for (Impacto impacto : impactos) {
            impacto.actualizar(deltaMs);
        }
        for (int i = impactos.size() - 1; i >= 0; i--) {
            if (impactos.get(i).terminado()) {
                impactos.remove(i);
            }
        }
        notificar();
    }

    /**
     * Si el luchador acaba de empezar su super, congela la pelea un segundo a
     * partir del proximo paso (mientras, la vista muestra la imagen del super).
     *
     * @param luchador un luchador
     */
    private void mostrarRetratoSiEmpiezaSuper(Luchador luchador) {
        if (!luchador.isRetratoPendiente()) {
            return;
        }
        luchador.marcarRetratoMostrado();
        if (enRetrato.isEmpty()) {
            tiempoRetratoMs = 0;
        }
        enRetrato.add(luchador);
    }

    /**
     * Aturde al luchador y deja la chispa del golpe.
     *
     * @param defensor quien recibe el golpe
     * @param x        posicion horizontal de la chispa
     * @param y        posicion vertical de la chispa
     */
    private void golpear(Luchador defensor, double x, double y) {
        defensor.recibirGolpe();
        impactos.add(new Impacto(x, y));
    }

    /**
     * @param luchador un luchador
     * @return el otro luchador
     */
    private Luchador rivalDe(Luchador luchador) {
        return luchador == jugador1 ? jugador2 : jugador1;
    }

    /**
     * Suma a la pelea el rayo de un luchador, la primera vez que aparece.
     *
     * @param rayo el rayo del super en curso de un luchador, o null
     */
    private void agregarRayo(Rayo rayo) {
        if (rayo != null && rayo.isActivo() && !rayos.contains(rayo)) {
            rayos.add(rayo);
        }
    }

    /**
     * Hace crecer los rayos y resuelve:
     * <ul>
     * <li>choque entre dos rayos: los dos se frenan en el punto medio entre sus
     * origenes y saltan chispas;</li>
     * <li>rayo contra el rival: se frena en su cuerpo y lo golpea cada cierto
     * intervalo mientras lo toque.</li>
     * </ul>
     *
     * @param deltaMs milisegundos transcurridos desde la ultima actualizacion
     */
    private void actualizarRayos(int deltaMs) {
        for (Rayo rayo : rayos) {
            rayo.actualizar(deltaMs, anchoArena);
        }
        for (int i = rayos.size() - 1; i >= 0; i--) {
            if (!rayos.get(i).isActivo()) {
                rayos.remove(i);
            }
        }

        if (rayos.size() == 2 && rayos.get(0).getDireccion() != rayos.get(1).getDireccion()) {
            Rayo rayoA = rayos.get(0);
            Rayo rayoB = rayos.get(1);
            double medio = (rayoA.getOrigenX() + rayoB.getOrigenX()) / 2;
            boolean seTocan = rayoA.getHitbox().seSuperponeCon(rayoB.getHitbox());
            rayoA.limitarLargo(Math.abs(medio - rayoA.getOrigenX()));
            rayoB.limitarLargo(Math.abs(medio - rayoB.getOrigenX()));
            if (seTocan && ++ticksChoqueRayos % TICKS_ENTRE_CHISPAS_CHOQUE == 0) {
                impactos.add(new Impacto(medio, (rayoA.getY() + rayoB.getY()) / 2));
            }
        }

        for (Rayo rayo : rayos) {
            CajaColision zonaRayo = rayo.getHitbox();
            Luchador objetivo = rivalDe(rayo.getDuenio());
            if (zonaRayo.seSuperponeCon(objetivo.getHitbox())) {
                /* El rayo se frena en el cuerpo del rival: la punta de impacto queda sobre el */
                rayo.limitarLargo(Math.abs(objetivo.getX() - rayo.getOrigenX()));
                rayo.marcarImpacto();
                if (rayo.puedeGolpear()) {
                    rayo.registrarGolpe();
                    golpear(objetivo, rayo.getPuntaX(), rayo.getY());
                }
            }
        }
    }

    /**
     * @return el luchador de la izquierda
     */
    public Luchador getJugador1() {
        return jugador1;
    }

    /**
     * @return el luchador de la derecha
     */
    public Luchador getJugador2() {
        return jugador2;
    }

    /**
     * @return los luchadores cuya imagen de super se esta mostrando (vacia si no
     *         hay ninguna; puede haber dos si los dos lanzaron el super a la vez)
     */
    public List<Luchador> getLuchadoresEnRetrato() {
        return Collections.unmodifiableList(enRetrato);
    }

    /**
     * @return los milisegundos que lleva en pantalla la imagen del super
     */
    public int getTiempoRetratoMs() {
        return tiempoRetratoMs;
    }

    /**
     * @return cuanto dura la imagen del super
     */
    public int getDuracionRetratoMs() {
        return DURACION_RETRATO_MS;
    }

    /**
     * @param luchador un luchador
     * @return true si esta a la izquierda de su rival (su imagen de super va en la
     *         mitad izquierda de la pantalla)
     */
    public boolean estaALaIzquierda(Luchador luchador) {
        Luchador rival = rivalDe(luchador);
        return luchador.getX() < rival.getX()
                || (luchador.getX() == rival.getX() && luchador == jugador1);
    }

    /**
     * @return los rayos de super en juego (solo lectura)
     */
    public List<Rayo> getRayos() {
        return Collections.unmodifiableList(rayos);
    }

    /**
     * @return las chispas de golpe visibles (solo lectura)
     */
    public List<Impacto> getImpactos() {
        return Collections.unmodifiableList(impactos);
    }

    /**
     * @return la altura del suelo
     */
    public double getSueloY() {
        return sueloY;
    }
}
