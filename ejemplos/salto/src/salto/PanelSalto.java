package salto;

import java.awt.BasicStroke;
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
 * Panel que lee el input del jugador y hace avanzar la fisica del personaje.
 */
public class PanelSalto extends JPanel {

    private static final int ANCHO_SPRITE = 200;
    private static final int ALTO_SPRITE = 140;
    private static final int INTERVALO_TICK_MS = 16;
    private static final int MARGEN_SUELO = 40;
    private static final double VELOCIDAD_HORIZONTAL = 4.0;

    private static final Color COLOR_EN_SUELO = new Color(60, 220, 100);
    private static final Color COLOR_SALTO_BAJO = new Color(230, 220, 40);
    private static final Color COLOR_SALTO_MEDIO = new Color(255, 165, 0);
    private static final Color COLOR_SALTO_ALTO = new Color(220, 30, 30);

    /** A partir de este progreso de altura, el salto pasa de bajo a medio. */
    private static final double UMBRAL_SALTO_MEDIO = 1.0 / 3.0;

    /** A partir de este progreso de altura, el salto pasa de medio a alto. */
    private static final double UMBRAL_SALTO_ALTO = 2.0 / 3.0;

    /**
     * Transparencia del relleno que se muestra sobre el sprite mientras el
     * personaje esta en el aire.
     */
    private static final int ALPHA_RELLENO_AIRE = 70;

    private final Personaje personaje;
    private final Set<Integer> teclasPresionadas = new HashSet<>();

    /**
     * Crea el panel de salto a partir del sprite del personaje.
     *
     * @param sprite imagen del personaje
     */
    public PanelSalto(BufferedImage sprite) {
        setPreferredSize(new Dimension(960, 540));
        setBackground(new Color(24, 26, 34));
        setFocusable(true);

        double xInicial = (960 - ANCHO_SPRITE) / 2.0;
        personaje = new Personaje(sprite, xInicial, getAlturaSuelo(), ANCHO_SPRITE, ALTO_SPRITE);

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

        new Timer(INTERVALO_TICK_MS, e -> actualizar()).start();
    }

    /**
     * Activa o desactiva el corte de salto del personaje.
     *
     * @param habilitado true = salto de altura variable, false = salto de altura
     *                   fija
     */
    public void setSaltoVariable(boolean habilitado) {
        personaje.setSaltoVariable(habilitado);
    }

    /**
     * @return la posicion Y del suelo, en base a la altura actual del panel
     */
    private double getAlturaSuelo() {
        int altoPanel = getHeight() > 0 ? getHeight() : 540;
        return altoPanel - ALTO_SPRITE - MARGEN_SUELO;
    }

    /**
     * Lee el input y actualiza el movimiento horizontal y la fisica de salto del
     * personaje.
     */
    private void actualizar() {
        double dx = 0;
        if (teclasPresionadas.contains(KeyEvent.VK_A) || teclasPresionadas.contains(KeyEvent.VK_LEFT))
            dx -= VELOCIDAD_HORIZONTAL;
        if (teclasPresionadas.contains(KeyEvent.VK_D) || teclasPresionadas.contains(KeyEvent.VK_RIGHT))
            dx += VELOCIDAD_HORIZONTAL;
        personaje.moverHorizontal(dx, getWidth());

        boolean pidioSalto = teclasPresionadas.contains(KeyEvent.VK_SPACE)
                || teclasPresionadas.contains(KeyEvent.VK_W)
                || teclasPresionadas.contains(KeyEvent.VK_UP);
        personaje.actualizarFisica(pidioSalto, getAlturaSuelo());

        /*
         * Entre otras cosas, repaint llama al metodo paintComponent que redefinimos a
         * continuacion
         */
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

        int lineaSuelo = (int) getAlturaSuelo() + ALTO_SPRITE;
        g2.setColor(new Color(60, 60, 70));
        g2.fillRect(0, lineaSuelo, getWidth(), 2);

        dibujarPersonaje(g2);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("A D / Flechas para mover, ESPACIO para saltar", 16, 24);
        g2.drawString(String.format("velocidadY=%.1f  %s  |  salto %s", personaje.getVelocidadY(),
                personaje.estaEnSuelo() ? "en el suelo" : "en el aire",
                personaje.estaSaltoVariableHabilitado() ? "variable" : "fijo"), 16, 44);
    }

    /**
     * Dibuja el sprite del personaje y una caja con el color que indica su estado
     * (en el suelo / en el aire);
     * mientras esta en el aire, ademas rellena la caja con ese mismo color en forma
     * translucida.
     *
     * @param g contexto grafico donde dibujar
     */
    private void dibujarPersonaje(Graphics2D g) {
        int ix = (int) personaje.getX();
        int iy = (int) personaje.getY();
        int ancho = personaje.getAncho();
        int alto = personaje.getAlto();

        g.drawImage(personaje.getSprite(), ix, iy, ancho, alto, null);

        Color borde = getColorBorde();

        if (!personaje.estaEnSuelo()) {
            g.setColor(new Color(borde.getRed(), borde.getGreen(), borde.getBlue(), ALPHA_RELLENO_AIRE));
            g.fillRect(ix, iy, ancho, alto);
        }

        g.setColor(borde);
        g.setStroke(new BasicStroke(3f));
        g.drawRect(ix, iy, ancho, alto);
    }

    /**
     * Calcula el color de borde segun el estado del personaje: verde en el suelo;
     * en el aire, amarillo para
     * un salto bajo, naranja para uno medio y rojo para uno que se acerca a la
     * altura maxima alcanzable.
     *
     * @return el color de borde correspondiente al estado actual del personaje
     */
    private Color getColorBorde() {
        if (personaje.estaEnSuelo()) {
            return COLOR_EN_SUELO;
        }

        double progreso = personaje.getProgresoAlturaMaxima();
        if (progreso >= UMBRAL_SALTO_ALTO) {
            return COLOR_SALTO_ALTO;
        } else if (progreso >= UMBRAL_SALTO_MEDIO) {
            return COLOR_SALTO_MEDIO;
        } else {
            return COLOR_SALTO_BAJO;
        }
    }
}
