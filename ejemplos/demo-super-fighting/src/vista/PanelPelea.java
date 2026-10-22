package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import modelo.IObservadorPelea;
import modelo.Impacto;
import modelo.Luchador;
import modelo.Pelea;
import modelo.Rayo;

/**
 * Panel donde se dibuja la pelea. Solo lee el modelo ({@link Pelea}) y lo pinta;
 * las decisiones de juego estan en el modelo. El modelo dice que esta haciendo
 * cada luchador y en que fotograma va; como se ve eso lo dice la apariencia de
 * cada uno ({@link AparienciaLuchador}). El escenario, los rayos y las
 * imagenes de super los dibujan {@link Escenario}, {@link DibujanteRayo} y
 * {@link DibujanteRetratos}.
 *
 * Observa la pelea ({@link IObservadorPelea}, patron Observer): cada vez que
 * la pelea avisa que cambio, el panel se redibuja.
 *
 * Todo se dibuja en un area logica de {@link #ANCHO} x {@link #ALTO}. Si el
 * panel es mas grande (ventana maximizada), esa area se agranda para llenarlo
 * sin deformarse, y lo que sobra queda en negro a los costados o arriba y abajo.
 */
public class PanelPelea extends JPanel implements IObservadorPelea {

    private static final int ANCHO = 960;
    private static final int ALTO = 540;

    /** Distancia entre el suelo y el borde inferior del panel. */
    private static final int MARGEN_SUELO = 50;

    /** Oscurecimiento del escenario mientras hay un super en pantalla. */
    private static final Color SOMBRA_SUPER = new Color(0, 0, 20, 110);

    private final Pelea pelea;
    private final Escenario escenario;
    /** Como se ve cada luchador de la pelea. */
    private final Map<Luchador, AparienciaLuchador> apariencias = new HashMap<>();
    private final DibujanteRayo dibujanteRayo;
    private final DibujanteRetratos dibujanteRetratos;

    /**
     * @param pelea              la pelea a dibujar
     * @param aparienciaJugador1 como se ve el luchador de la izquierda
     * @param aparienciaJugador2 como se ve el luchador de la derecha
     * @param spritesRayo        piezas del rayo de los supers (las usan los dos)
     */
    public PanelPelea(Pelea pelea, AparienciaLuchador aparienciaJugador1, AparienciaLuchador aparienciaJugador2,
            SpritesRayo spritesRayo) {
        this.pelea = pelea;
        /* Si los dibujos no coinciden con las animaciones del modelo, mejor avisar ya */
        aparienciaJugador1.verificar(pelea.getJugador1());
        aparienciaJugador2.verificar(pelea.getJugador2());
        apariencias.put(pelea.getJugador1(), aparienciaJugador1);
        apariencias.put(pelea.getJugador2(), aparienciaJugador2);
        escenario = new Escenario(ANCHO, ALTO, getSueloY());
        dibujanteRayo = new DibujanteRayo(spritesRayo);
        dibujanteRetratos = new DibujanteRetratos(pelea, apariencias);
        setPreferredSize(new Dimension(ANCHO, ALTO));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    /**
     * @return el ancho del area logica de dibujo (tambien el ancho de la arena)
     */
    public static int getAncho() {
        return ANCHO;
    }

    /**
     * @return el alto del area logica de dibujo
     */
    public static int getAlto() {
        return ALTO;
    }

    /**
     * @return la altura del suelo dentro del area logica de dibujo
     */
    public static int getSueloY() {
        return ALTO - MARGEN_SUELO;
    }

    /**
     * La pelea cambio: se pide redibujar el panel (repaint termina llamando a
     * paintComponent).
     *
     * @param pelea la pelea que cambio
     */
    @Override
    public void peleaCambio(Pelea pelea) {
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
         * Escala para que el area de 960 x 540 llene el panel sin deformarse, centrada.
         * Se trabaja sobre una copia del contexto grafico, asi el original queda igual.
         */
        Graphics2D g2 = (Graphics2D) g.create();
        double escala = Math.min(getWidth() / (double) ANCHO, getHeight() / (double) ALTO);
        g2.translate((getWidth() - ANCHO * escala) / 2, (getHeight() - ALTO * escala) / 2);
        g2.scale(escala, escala);
        g2.clipRect(0, 0, ANCHO, ALTO);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        escenario.dibujar(g2);
        if (!pelea.getRayos().isEmpty()) {
            g2.setColor(SOMBRA_SUPER);
            g2.fillRect(0, 0, ANCHO, ALTO);
        }

        dibujarSombra(g2, pelea.getJugador1());
        dibujarSombra(g2, pelea.getJugador2());
        dibujarLuchador(g2, pelea.getJugador1());
        dibujarLuchador(g2, pelea.getJugador2());

        for (Rayo rayo : pelea.getRayos()) {
            dibujanteRayo.dibujar(g2, rayo);
        }
        for (Impacto impacto : pelea.getImpactos()) {
            dibujarImpacto(g2, impacto);
        }

        dibujarHud(g2);
        dibujarCartel(g2);
        dibujanteRetratos.dibujar(g2);
        g2.dispose();
    }

    /**
     * Sombra en el piso.
     *
     * @param g        contexto grafico donde dibujar
     * @param luchador luchador a dibujar
     */
    private void dibujarSombra(Graphics2D g, Luchador luchador) {
        double altura = pelea.getSueloY() - luchador.getY();
        double factor = Math.max(0.4, 1 - altura / 300);
        int ancho = (int) (110 * factor);
        int alto = (int) (18 * factor);
        g.setColor(new Color(0, 0, 0, 90));
        g.fillOval((int) luchador.getX() - ancho / 2, (int) pelea.getSueloY() - alto / 2, ancho, alto);
    }

    /**
     * Dibuja el fotograma actual del luchador, alineando el centro de su cuerpo
     * (el ancla) con su posicion x, y los pies con su posicion y.
     *
     * Las hojas miran a la derecha. Para mirar a la izquierda se dibuja espejado,
     * pasando a drawImage las esquinas de destino al reves (x1 &gt; x2):
     *
     * <pre>
     *  mirando a la derecha           mirando a la izquierda
     *  izq         x                           x         der
     *   |&lt;-ancla-&gt;|                           |&lt;-ancla-&gt;|
     *   +---------+---+               +---+---------+
     *   |  sprite     |               |     etirps  |
     *   +-------------+               +-------------+
     * </pre>
     *
     * @param g        contexto grafico donde dibujar
     * @param luchador luchador a dibujar
     */
    private void dibujarLuchador(Graphics2D g, Luchador luchador) {
        AparienciaLuchador apariencia = apariencias.get(luchador);
        /* El estado actual del luchador elige sus dibujos (patron Visitor) */
        luchador.aceptar(apariencia);
        Fotogramas fotogramas = apariencia.getFotogramasElegidos();
        int indice = luchador.getIndiceFotograma();

        dibujarSprite(g, fotogramas.getImagen(indice), fotogramas.getAnclaX(indice), apariencia.getEscala(),
                luchador.getX(), luchador.getY(), luchador.isMirandoIzquierda());
    }

    /**
     * @param g        contexto grafico donde dibujar
     * @param imagen   fotograma
     * @param anclaX   columna del fotograma que va en x
     * @param escala   escala del dibujo
     * @param x        posicion horizontal del ancla
     * @param base     posicion vertical del borde inferior del fotograma
     * @param espejado true para dibujarlo mirando a la izquierda
     */
    private void dibujarSprite(Graphics2D g, BufferedImage imagen, int anclaX, double escala, double x, double base,
            boolean espejado) {
        int ancho = (int) Math.round(imagen.getWidth() * escala);
        int alto = (int) Math.round(imagen.getHeight() * escala);
        int ancla = (int) Math.round(anclaX * escala);
        int abajo = (int) base;
        int arriba = abajo - alto;

        if (espejado) {
            int derecha = (int) x + ancla;
            g.drawImage(imagen, derecha, arriba, derecha - ancho, abajo, 0, 0, imagen.getWidth(), imagen.getHeight(),
                    null);
        } else {
            int izquierda = (int) x - ancla;
            g.drawImage(imagen, izquierda, arriba, izquierda + ancho, abajo, 0, 0, imagen.getWidth(),
                    imagen.getHeight(), null);
        }
    }

    /**
     * Chispa de golpe: un destello que crece y se desvanece.
     *
     * @param g       contexto grafico donde dibujar
     * @param impacto chispa a dibujar
     */
    private void dibujarImpacto(Graphics2D g, Impacto impacto) {
        double progreso = impacto.getProgreso();
        int alfa = (int) (255 * (1 - progreso));
        int radio = (int) (14 + 30 * progreso);
        int cx = (int) impacto.getX();
        int cy = (int) impacto.getY();

        g.setStroke(new BasicStroke(3f));
        g.setColor(new Color(255, 240, 150, alfa));
        for (int i = 0; i < 8; i++) {
            double angulo = i * Math.PI / 4;
            g.drawLine(cx + (int) (Math.cos(angulo) * radio * 0.4), cy + (int) (Math.sin(angulo) * radio * 0.4),
                    cx + (int) (Math.cos(angulo) * radio), cy + (int) (Math.sin(angulo) * radio));
        }
        g.setColor(new Color(255, 255, 255, alfa));
        g.fillOval(cx - radio / 3, cy - radio / 3, 2 * radio / 3, 2 * radio / 3);
    }

    /**
     * Ayuda de controles.
     *
     * @param g contexto grafico donde dibujar
     */
    private void dibujarHud(Graphics2D g) {
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.setColor(new Color(255, 255, 255, 190));
        dibujarCentrado(g, "KEN: T super   |   GOKU: I super   |   ESC salir", ALTO - 14);
    }

    /**
     * Nombre del super mientras su rayo esta en pantalla, del lado de quien lo
     * lanzo.
     *
     * @param g contexto grafico donde dibujar
     */
    private void dibujarCartel(Graphics2D g) {
        for (Rayo rayo : pelea.getRayos()) {
            int centroX = rayo.getDuenio() == pelea.getJugador1() ? ANCHO / 4 : 3 * ANCHO / 4;
            dibujarTextoGrande(g, apariencias.get(rayo.getDuenio()).getNombreSuper(), centroX, 150, 36);
        }
    }

    /**
     * Texto grande amarillo con contorno negro, centrado en una x dada.
     *
     * @param g       contexto grafico donde dibujar
     * @param texto   texto a dibujar
     * @param centroX centro horizontal del texto
     * @param y       linea base
     * @param tamano  tamanio de la fuente
     */
    private void dibujarTextoGrande(Graphics2D g, String texto, int centroX, int y, int tamano) {
        g.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, tamano));
        Dibujo.dibujarTextoConContorno(g, texto, centroX, y, 3);
    }

    /**
     * @param g     contexto grafico donde dibujar
     * @param texto texto a dibujar centrado horizontalmente
     * @param y     linea base
     */
    private void dibujarCentrado(Graphics2D g, String texto, int y) {
        int ancho = g.getFontMetrics().stringWidth(texto);
        g.drawString(texto, (ANCHO - ancho) / 2, y);
    }
}
