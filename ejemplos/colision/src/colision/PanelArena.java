package colision;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
 * Arena donde dos personajes se mueven y cambian a un 3er color cuando sus
 * cajas de colision se tocan.
 */
public class PanelArena extends JPanel {

    private static final int ANCHO_PERSONAJE = 200;
    private static final int ALTO_PERSONAJE = 140;
    private static final double VELOCIDAD = 5.0;

    /** Color de borde compartido que indica que dos hitboxes se estan tocando. */
    private static final Color COLOR_GOLPE = new Color(255, 40, 40);

    private final Personaje jugador1;
    private final Personaje jugador2;
    private final Set<Integer> teclasPresionadas = new HashSet<>();

    /**
     * Arma la arena con los dos sprites recibidos y arranca el bucle de
     * actualizacion.
     *
     * @param troll1 sprite del jugador 1
     * @param troll2 sprite del jugador 2
     */
    public PanelArena(BufferedImage troll1, BufferedImage troll2) {
        setPreferredSize(new Dimension(960, 600));
        setBackground(new Color(24, 26, 34));
        setFocusable(true);

        jugador1 = new Personaje("P1 (WASD)", troll1, 100, 220, ANCHO_PERSONAJE, ALTO_PERSONAJE,
                new Color(60, 140, 255));
        jugador2 = new Personaje("P2 (Flechas)", troll2, 660, 220, ANCHO_PERSONAJE, ALTO_PERSONAJE,
                new Color(60, 220, 100));

        /*
         * Escuchador de eventos
         * En lineas generales, la presion de una tecla del teclado es un tipo de evento
         * Key adapter lee continuamente (Escucha) para detectar una tecla apretada
         */
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

        /* En cada ciclo de 16ms, se ejecuta el metodo actualizar */
        Timer temporizador = new Timer(16, e -> actualizar());
        temporizador.start();
    }

    /**
     * Mueve a ambos jugadores segun las teclas presionadas y actualiza el estado de
     * colision.
     */
    private void actualizar() {
        double dx1 = 0, dy1 = 0;
        if (teclasPresionadas.contains(KeyEvent.VK_A))
            dx1 -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_D))
            dx1 += VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_W))
            dy1 -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_S))
            dy1 += VELOCIDAD;
        jugador1.mover(dx1, dy1, getWidth(), getHeight());

        double dx2 = 0, dy2 = 0;
        if (teclasPresionadas.contains(KeyEvent.VK_LEFT))
            dx2 -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_RIGHT))
            dx2 += VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_UP))
            dy2 -= VELOCIDAD;
        if (teclasPresionadas.contains(KeyEvent.VK_DOWN))
            dy2 += VELOCIDAD;
        jugador2.mover(dx2, dy2, getWidth(), getHeight());

        boolean colisionando = jugador1.getHitbox().intersects(jugador2.getHitbox());
        jugador1.setGolpeado(colisionando);
        jugador2.setGolpeado(colisionando);

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
        g2.setFont(new Font("SansSerif", Font.BOLD, 14));

        dibujarPersonaje(g2, jugador1);
        dibujarPersonaje(g2, jugador2);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawString("P1: W A S D para moverse  |  P2: flechas para moverse  |  Las cajas se ponen rojas al chocar",
                16, 24);
    }

    /**
     * Dibuja el sprite, el hitbox y el nombre de un personaje.
     *
     * @param g         contexto grafico donde dibujar
     * @param personaje personaje a dibujar
     */
    private void dibujarPersonaje(Graphics2D g, Personaje personaje) {
        int ix = (int) personaje.getX();
        int iy = (int) personaje.getY();
        int ancho = personaje.getAncho();
        int alto = personaje.getAlto();

        g.drawImage(personaje.getSprite(), ix, iy, ancho, alto, null);

        Color borde = personaje.isGolpeado() ? COLOR_GOLPE : personaje.getColorReposo();

        if (personaje.isGolpeado()) {
            g.setColor(new Color(borde.getRed(), borde.getGreen(), borde.getBlue(), 70));
            g.fillRect(ix, iy, ancho, alto);
        }

        g.setColor(borde);
        g.setStroke(new BasicStroke(personaje.isGolpeado() ? 6f : 3f));
        g.drawRect(ix, iy, ancho, alto);

        g.setColor(Color.WHITE);
        g.drawString(personaje.getNombre(), ix + 4, iy - 6);
    }
}
