package vista;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * El fondo de la pelea: cielo al atardecer, montanias y piso. Se pinta una sola
 * vez en una imagen, que despues se copia en cada cuadro.
 */
public class Escenario {

    private final BufferedImage imagen;

    /**
     * @param ancho ancho del escenario
     * @param alto  alto del escenario
     * @param suelo altura del suelo
     */
    public Escenario(int ancho, int alto, int suelo) {
        imagen = crear(ancho, alto, suelo);
    }

    /**
     * @param g contexto grafico donde dibujar
     */
    public void dibujar(Graphics2D g) {
        g.drawImage(imagen, 0, 0, null);
    }

    /**
     * Arma una sola vez el escenario: cielo al atardecer, montanias y piso.
     *
     * @param ancho ancho del escenario
     * @param alto  alto del escenario
     * @param suelo altura del suelo
     * @return la imagen del fondo
     */
    private static BufferedImage crear(int ancho, int alto, int suelo) {
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagen.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setPaint(new GradientPaint(0, 0, new Color(40, 30, 90), 0, suelo, new Color(250, 140, 70)));
        g.fillRect(0, 0, ancho, suelo);

        g.setColor(new Color(255, 220, 130));
        g.fillOval(ancho / 2 - 70, suelo - 230, 140, 140);

        g.setColor(new Color(90, 50, 90));
        g.fillPolygon(new Polygon(new int[] { 0, 120, 260, 380, 520, 640, 780, 900, 960, 960, 0 },
                new int[] { suelo - 120, suelo - 200, suelo - 130, suelo - 210, suelo - 110, suelo - 190, suelo - 120,
                        suelo - 180, suelo - 140, suelo, suelo },
                11));
        g.setColor(new Color(60, 35, 70));
        g.fillPolygon(new Polygon(new int[] { 0, 180, 330, 470, 610, 760, 960, 960, 0 },
                new int[] { suelo - 60, suelo - 110, suelo - 70, suelo - 120, suelo - 60, suelo - 100, suelo - 70,
                        suelo, suelo },
                9));

        g.setPaint(new GradientPaint(0, suelo, new Color(120, 90, 70), 0, alto, new Color(60, 40, 30)));
        g.fillRect(0, suelo, ancho, alto - suelo);

        /* Lineas del piso en perspectiva */
        g.setColor(new Color(0, 0, 0, 50));
        for (int x = -ancho; x <= 2 * ancho; x += 80) {
            g.drawLine(ancho / 2 + (x - ancho / 2) / 3, suelo, x, alto);
        }
        g.setColor(new Color(255, 255, 255, 60));
        g.drawLine(0, suelo, ancho, suelo);

        g.dispose();
        return imagen;
    }
}
