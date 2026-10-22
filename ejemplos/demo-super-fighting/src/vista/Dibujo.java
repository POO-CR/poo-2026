package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Dibujos auxiliares de la pelea: el texto grande (amarillo con contorno negro)
 * y una imagen que llena un rectangulo manteniendo su proporcion. Es una clase
 * de metodos estaticos.
 */
public final class Dibujo {

    /** Amarillo de los textos grandes. */
    private static final Color AMARILLO = new Color(255, 215, 40);

    private Dibujo() {
    }

    /**
     * Texto amarillo con contorno negro, centrado en una x, con la fuente que ya
     * tiene el contexto grafico. El contorno es el mismo texto en negro, corrido
     * hacia los 8 costados.
     *
     * @param g       contexto grafico
     * @param texto   texto
     * @param centroX centro horizontal
     * @param y       linea base
     * @param borde   grosor del contorno
     */
    public static void dibujarTextoConContorno(Graphics2D g, String texto, int centroX, int y, int borde) {
        int x = centroX - g.getFontMetrics().stringWidth(texto) / 2;
        g.setColor(Color.BLACK);
        for (int dx = -borde; dx <= borde; dx += borde) {
            for (int dy = -borde; dy <= borde; dy += borde) {
                g.drawString(texto, x + dx, y + dy);
            }
        }
        g.setColor(AMARILLO);
        g.drawString(texto, x, y);
    }

    /**
     * Dibuja la imagen llenando todo el rectangulo {x0, 0, ancho, alto} sin
     * deformarla: se agranda hasta cubrirlo y lo que sobra a los costados queda
     * afuera, dejando a la vista la parte indicada por focoX. Lo que sobra arriba
     * y abajo se reparte por igual. No recorta: si hace falta, el que llama pone
     * el recorte.
     *
     * @param g      contexto grafico
     * @param imagen imagen a dibujar
     * @param focoX  parte de la imagen que queda a la vista, de 0 (borde
     *               izquierdo) a 1 (borde derecho); 0,5 es el centro
     * @param x0     borde izquierdo del rectangulo
     * @param ancho  ancho del rectangulo
     * @param alto   alto del rectangulo
     */
    public static void dibujarImagenCubriendo(Graphics2D g, BufferedImage imagen, double focoX, int x0, int ancho,
            int alto) {
        double escala = Math.max(ancho / (double) imagen.getWidth(), alto / (double) imagen.getHeight());
        int anchoDibujo = (int) Math.ceil(imagen.getWidth() * escala);
        int altoDibujo = (int) Math.ceil(imagen.getHeight() * escala);

        /* Se centra el foco en el rectangulo, sin dejar huecos a los costados */
        int izquierda = (int) (ancho / 2.0 - focoX * anchoDibujo);
        izquierda = Math.min(0, Math.max(ancho - anchoDibujo, izquierda));
        int arriba = (alto - altoDibujo) / 2;
        g.drawImage(imagen, x0 + izquierda, arriba, anchoDibujo, altoDibujo, null);
    }
}
