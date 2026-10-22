package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Timer;
import modelo.Comandos;
import modelo.IFuenteComandos;
import modelo.Pelea;
import vista.PanelPelea;
import vista.VentanaJuego;

/**
 * Controlador de la pelea: recibe el modelo y la vista ya armados, escucha el
 * teclado, y en cada tick del Timer le pasa los comandos al modelo. La vista se
 * redibuja cuando el modelo le avisa ({@link modelo.IObservadorPelea}).
 */
public class ControladorPelea {

    private static final int INTERVALO_TICK_MS = 16;

    /**
     * Maximo de pasos de 16 ms que se simulan en un mismo tick del Timer. Si la
     * computadora se atrasa mas que esto, el tiempo perdido se descarta (el juego
     * se frena un instante en vez de "saltar" hacia adelante).
     */
    private static final int MAXIMO_PASOS_POR_TICK = 5;

    private final Pelea pelea;
    private final PanelPelea panel;
    private final VentanaJuego ventana;
    private final Timer temporizador;

    private final Set<Integer> teclasPresionadas = new HashSet<>();
    private final Set<Integer> teclasNuevas = new HashSet<>();

    private final IFuenteComandos teclado1;
    private final IFuenteComandos teclado2;

    /* Paso fijo: cuanto tiempo real paso y todavia falta simular */
    private long ultimoTickNanos;
    private double tiempoPendienteMs;

    /**
     * @param ventana la ventana del juego
     * @param pelea   el modelo de la pelea
     * @param panel   la vista de la pelea
     */
    public ControladorPelea(VentanaJuego ventana, Pelea pelea, PanelPelea panel) {
        this.ventana = ventana;
        this.pelea = pelea;
        this.panel = panel;

        teclado1 = new TecladoJugador(teclasNuevas, new int[] { KeyEvent.VK_T });
        teclado2 = new TecladoJugador(teclasNuevas, new int[] { KeyEvent.VK_I, KeyEvent.VK_NUMPAD4 });

        /*
         * Escuchador de eventos: guarda que teclas estan apretadas. "add" devuelve
         * false si la tecla ya estaba (repeticion automatica del sistema), asi que
         * solo la primera pulsacion cuenta como tecla nueva.
         */
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (teclasPresionadas.add(e.getKeyCode())) {
                    teclasNuevas.add(e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasPresionadas.remove(e.getKeyCode());
            }
        });

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }
        });

        /* En cada ciclo de 16ms, se ejecuta el metodo actualizar (arranca con empezar()) */
        temporizador = new Timer(INTERVALO_TICK_MS, e -> actualizar());
    }

    /**
     * Empieza la pelea: muestra el panel de la pelea en la ventana, ubica a los
     * luchadores y pone a andar el Timer.
     */
    public void empezar() {
        pelea.reiniciar();
        teclasPresionadas.clear();
        teclasNuevas.clear();
        tiempoPendienteMs = 0;
        ultimoTickNanos = System.nanoTime();
        ventana.mostrar(panel);
        temporizador.start();
    }

    /**
     * Un tick del Timer: lee las entradas y avanza el modelo.
     *
     * El Timer de Swing es aproximado: en Windows, uno de 16 ms suele dispararse
     * cada 20 o 30 ms, y si dibujar tarda mucho tambien se atrasa. Si cada tick
     * avanzara la pelea 16 ms fijos, el juego iria en camara lenta. Por eso se
     * mide el tiempo real que paso y se avanza la pelea en pasos fijos de 16 ms
     * hasta alcanzarlo ("paso fijo"):
     *
     * <pre>
     *  tiempo real:  |--- 23 ms ---|--- 31 ms ---|--- 18 ms ---|
     *  pasos de 16:  |  1 paso  (+7 pendiente)   |
     *                              |  2 pasos (+6 pendiente)   |
     *                                            |  1 paso (+8)|
     * </pre>
     *
     * La fisica sigue igual (siempre en pasos de 16 ms) y la velocidad del juego
     * es la misma en cualquier computadora.
     */
    private void actualizar() {
        long ahora = System.nanoTime();
        tiempoPendienteMs += (ahora - ultimoTickNanos) / 1_000_000.0;
        ultimoTickNanos = ahora;
        if (tiempoPendienteMs < INTERVALO_TICK_MS) {
            /* Todavia falta para un paso entero: las entradas quedan para el proximo tick */
            return;
        }

        if (teclasNuevas.contains(KeyEvent.VK_ESCAPE)) {
            salir();
            return;
        }

        Comandos comandos1 = teclado1.leer();
        Comandos comandos2 = teclado2.leer();
        Comandos ninguno = new Comandos(false);

        int pasos = 0;
        while (tiempoPendienteMs >= INTERVALO_TICK_MS && pasos < MAXIMO_PASOS_POR_TICK) {
            /* El super cuenta solo en el primer paso */
            Comandos paso1 = pasos == 0 ? comandos1 : ninguno;
            Comandos paso2 = pasos == 0 ? comandos2 : ninguno;
            pelea.actualizar(paso1, paso2, INTERVALO_TICK_MS);
            tiempoPendienteMs -= INTERVALO_TICK_MS;
            pasos++;
        }
        if (pasos == MAXIMO_PASOS_POR_TICK) {
            tiempoPendienteMs = 0;
        }

        teclasNuevas.clear();
    }

    /**
     * Detiene el juego y cierra. Lo usa el cierre de la ventana y la tecla ESC.
     */
    private void salir() {
        temporizador.stop();
        ventana.dispose();
        System.exit(0);
    }
}
