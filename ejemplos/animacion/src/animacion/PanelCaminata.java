package animacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel que lee el input del jugador y hace avanzar al personaje y su
 * animacion.
 */
public class PanelCaminata extends JPanel {

    private static final int ANCHO_SPRITE = 200;
    private static final int ALTO_SPRITE = 126;
    private static final int INTERVALO_TICK_MS = 16;
    private static final double VELOCIDAD = 4.0;

    private final Personaje personaje;
    private final Set<Integer> teclasPresionadas = new HashSet<>();

    /**
     * Crea el panel de animacion a partir de los fotogramas del ciclo de caminata.
     *
     * @param fotogramas fotogramas del ciclo de caminata, en orden
     */
    public PanelCaminata(BufferedImage[] fotogramas) {
        setPreferredSize(new Dimension(960, 540));
        setBackground(new Color(24, 26, 34));
        setFocusable(true);

        double xInicial = (960 - ANCHO_SPRITE) / 2.0;
        double yInicial = (540 - ALTO_SPRITE) / 2.0;
        personaje = new Personaje(fotogramas, xInicial, yInicial, ANCHO_SPRITE, ALTO_SPRITE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                teclasPresionadas.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasPresionadas.remove(e.getKeyCode());
            }
        });

        Timer temporizador = new Timer(INTERVALO_TICK_MS, e -> actualizar());
        temporizador.start();
    }

    /**
     * Lee el input, mueve al personaje y avanza su animacion solo si hubo
     * movimiento.
     */
    private void actualizar() {
        double dx = 0, dy = 0;
        if (teclasPresionadas.contains(KeyEvent.VK_A) || teclasPresionadas.contains(KeyEvent.VK_LEFT))
            dx -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_D) || teclasPresionadas.contains(KeyEvent.VK_RIGHT))
            dx += VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_W) || teclasPresionadas.contains(KeyEvent.VK_UP))
            dy -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_S) || teclasPresionadas.contains(KeyEvent.VK_DOWN))
            dy += VELOCIDAD;

        boolean moviendose = dx != 0 || dy != 0;

        personaje.mover(dx, dy, getWidth(), getHeight());
        personaje.actualizarAnimacion(INTERVALO_TICK_MS, moviendose);

        repaint();
    }

    /**
     * Redefinicion del metodo paintComponent, metodo originario de la clase base de
     * JPanel que se llama JComponent
     */
    @Override
    protected void paintComponent(Graphics g) {
        /* Dejamos que el Panel se dibuje como se dibuja normalmente */
        super.paintComponent(g);

        /*
         * Extendemos el comportamiento de paintComponent agregando el dibujado de
         * nuestro personaje
         */
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarPersonaje(g2);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("WASD / Flechas para mover - frame "
                + (personaje.getIndiceFotograma() + 1) + "/" + personaje.getCantidadFotogramas(), 16, 24);
    }

    /**
     * Dibuja el fotograma actual del personaje, espejado si mira hacia la
     * izquierda.
     *
     * @param g contexto grafico donde dibujar
     */
    private void dibujarPersonaje(Graphics2D g) {
        int ix = (int) personaje.getX();
        int iy = (int) personaje.getY();
        int ancho = personaje.getAncho();
        int alto = personaje.getAlto();
        BufferedImage fotograma = personaje.getFotogramaActual();

        if (personaje.isMirandoIzquierda()) {
            g.drawImage(fotograma, ix + ancho, iy, ix, iy + alto,
                    0, 0, fotograma.getWidth(), fotograma.getHeight(), null);
        } else {
            g.drawImage(fotograma, ix, iy, ix + ancho, iy + alto,
                    0, 0, fotograma.getWidth(), fotograma.getHeight(), null);
        }
    }
}
