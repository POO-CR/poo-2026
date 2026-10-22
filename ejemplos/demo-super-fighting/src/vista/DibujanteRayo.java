package vista;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import modelo.Rayo;

/**
 * Dibuja los rayos de los supers con las piezas de {@link SpritesRayo}.
 */
public class DibujanteRayo {

    /** Cada cuanto cambia el anillo de energia en las manos mientras sale el rayo. */
    private static final int RETARDO_ANILLO_MS = 70;

    /** Cada cuanto se pasa a la siguiente variante de las piezas del rayo (para que vibre). */
    private static final int RETARDO_VARIANTE_RAYO_MS = 60;

    private final SpritesRayo spritesRayo;

    /**
     * @param spritesRayo piezas del rayo de los supers (las usan los dos)
     */
    public DibujanteRayo(SpritesRayo spritesRayo) {
        this.spritesRayo = spritesRayo;
    }

    /**
     * Arma el rayo con sus piezas: cola en las manos, el cuerpo repetido a lo largo
     * y la punta (o el impacto, si esta tocando al rival). Ademas, mientras dura el
     * rayo, en las manos se dibujan los anillos de energia, en loop.
     *
     * Se dibuja en coordenadas propias del rayo: el origen en las manos y el eje x
     * hacia donde avanza. Para un rayo que va a la izquierda alcanza con espejar
     * ese eje (scale(-1, 1)).
     *
     * @param g    contexto grafico donde dibujar
     * @param rayo rayo a dibujar
     */
    public void dibujar(Graphics2D g, Rayo rayo) {
        double escala = spritesRayo.getEscala();
        /* Las piezas se alternan (y la punta de impacto tambien) para que el rayo vibre */
        int paso = rayo.getTiempoMs() / RETARDO_VARIANTE_RAYO_MS;
        int variante = paso % spritesRayo.getCantidadVariantes();
        int largo = (int) rayo.getLargo();

        Graphics2D gr = (Graphics2D) g.create();
        gr.translate(rayo.getOrigenX(), rayo.getY());
        if (rayo.getDireccion() < 0) {
            gr.scale(-1, 1);
        }

        /* Anillos de energia en las manos: uno distinto cada 70 ms, en loop mientras dure el rayo */
        BufferedImage[] anillos = spritesRayo.getAnillos();
        int indiceAnillo = (rayo.getTiempoMs() / RETARDO_ANILLO_MS) % anillos.length;
        dibujarPieza(gr, anillos[indiceAnillo], -anillos[indiceAnillo].getWidth() * escala / 3, escala);

        if (largo > 0) {
            BufferedImage cola = spritesRayo.getCola(variante);
            BufferedImage cuerpo = spritesRayo.getCuerpo(variante);
            BufferedImage punta = rayo.isImpactando() ? spritesRayo.getImpacto(paso % spritesRayo.getCantidadImpactos()) : spritesRayo.getPunta();
            double altoCuerpo = spritesRayo.getAltoCuerpo() * escala;
            double anchoCola = cola.getWidth() * escala;
            double anchoCuerpo = cuerpo.getWidth() * escala;
            double anchoPunta = punta.getWidth() * escala;

            /* El cuerpo y la cola terminan donde empieza la punta */
            Graphics2D recorte = (Graphics2D) gr.create();
            recorte.clipRect(0, (int) (-altoCuerpo), largo, (int) (2 * altoCuerpo));
            dibujarPieza(recorte, cola, 0, escala);
            for (double x = anchoCola - 1; x < largo - anchoPunta / 2; x += anchoCuerpo - 1) {
                dibujarPieza(recorte, cuerpo, x, escala);
            }
            recorte.dispose();

            dibujarPieza(gr, punta, largo - anchoPunta / 2, escala);
        }
        gr.dispose();
    }

    /**
     * Dibuja una pieza del rayo centrada verticalmente en el eje del rayo.
     *
     * @param g      contexto grafico (con el origen en el eje del rayo)
     * @param pieza  imagen de la pieza
     * @param x      borde izquierdo de la pieza
     * @param escala escala del dibujo
     */
    private void dibujarPieza(Graphics2D g, BufferedImage pieza, double x, double escala) {
        int ancho = (int) Math.round(pieza.getWidth() * escala);
        int alto = (int) Math.round(pieza.getHeight() * escala);
        g.drawImage(pieza, (int) Math.round(x), -alto / 2, ancho, alto, null);
    }
}
