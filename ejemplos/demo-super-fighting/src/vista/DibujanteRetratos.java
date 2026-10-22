package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Luchador;
import modelo.Pelea;

/**
 * Dibuja las imagenes de super de los luchadores mientras la pelea esta
 * congelada, y guarda cada imagen ya preparada al tamanio de pantalla.
 */
public class DibujanteRetratos {

    private static final int ANCHO = PanelPelea.getAncho();
    private static final int ALTO = PanelPelea.getAlto();

    /** Capa oscura sobre el resto de la pantalla mientras se muestra la imagen del super. */
    private static final Color CAPA_RETRATO = new Color(0, 0, 0, 160);

    /** Milisegundos que tarda la imagen del super en entrar deslizandose. */
    private static final int ENTRADA_RETRATO_MS = 150;

    /** Milisegundos que tarda la imagen del super en desvanecerse al final. */
    private static final int SALIDA_RETRATO_MS = 100;

    private final Pelea pelea;
    private final Map<Luchador, AparienciaLuchador> apariencias;
    /** Imagenes de super ya recortadas al tamanio de pantalla (clave: nombre, y si va espejada). */
    private final Map<String, BufferedImage> retratosPreparados = new HashMap<>();

    /**
     * @param pelea       la pelea a dibujar
     * @param apariencias como se ve cada luchador de la pelea
     */
    public DibujanteRetratos(Pelea pelea, Map<Luchador, AparienciaLuchador> apariencias) {
        this.pelea = pelea;
        this.apariencias = apariencias;
    }

    /**
     * Imagen del personaje que lanza el super, como en los juegos de Marvel vs.
     * Capcom: ocupa su mitad de pantalla de arriba a abajo y entra deslizandose
     * desde su borde; el resto de la pantalla queda cubierto por una capa oscura
     * transparente. Al final se desvanece. La imagen siempre mira hacia el centro
     * de la pantalla: si el personaje mira al reves en el archivo, se espeja.
     *
     * <pre>
     * +---------------+---------------+
     * |               |///////////////|
     * |    imagen     |// capa oscura/|
     * |  del que lanza|///////////////|
     * |  el super  &lt;--|///////////////|   (entra desde el borde de su lado)
     * +---------------+---------------+
     * </pre>
     *
     * @param g contexto grafico donde dibujar
     */
    public void dibujar(Graphics2D g) {
        List<Luchador> luchadores = pelea.getLuchadoresEnRetrato();
        if (luchadores.isEmpty()) {
            return;
        }
        int tiempo = pelea.getTiempoRetratoMs();
        float visible = (float) Math.min(1.0,
                (pelea.getDuracionRetratoMs() - tiempo) / (double) SALIDA_RETRATO_MS);
        double entrada = Math.min(1.0, tiempo / (double) ENTRADA_RETRATO_MS);

        Graphics2D gr = (Graphics2D) g.create();
        gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.max(0f, visible)));
        gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        /* Capa oscura sobre todo; la mitad de cada retrato la tapa la imagen */
        gr.setColor(CAPA_RETRATO);
        gr.fillRect(0, 0, ANCHO, ALTO);

        int mitad = ANCHO / 2;
        for (Luchador luchador : luchadores) {
            boolean izquierda = pelea.estaALaIzquierda(luchador);
            int x0 = izquierda ? 0 : mitad;
            int corrimiento = (int) ((1 - entrada) * mitad) * (izquierda ? -1 : 1);

            Graphics2D mitadG = (Graphics2D) gr.create();
            mitadG.clipRect(x0, 0, mitad, ALTO);
            mitadG.translate(corrimiento, 0);
            /* La imagen ya viene recortada y achicada al tamanio real en pantalla: copiarla es rapido */
            double escalaPantalla = gr.getTransform().getScaleX();
            /* En la mitad izquierda tiene que mirar a la derecha, y viceversa */
            boolean espejar = izquierda != apariencias.get(luchador).getRetrato().isMirandoALaDerecha();
            mitadG.drawImage(getRetratoPreparado(luchador, espejar, escalaPantalla), x0, 0, mitad, ALTO, null);

            /* Linea clara en el borde interior, que separa la imagen del resto */
            int bordeX = izquierda ? x0 + mitad - 4 : x0;
            mitadG.setColor(new Color(255, 255, 255, 220));
            mitadG.fillRect(bordeX, 0, 4, ALTO);
            mitadG.dispose();
        }
        gr.dispose();
    }

    /**
     * Devuelve la imagen del super del luchador ya recortada y achicada al tamanio
     * que ocupa en pantalla (media pantalla, en pixeles reales). Agrandar o achicar
     * una foto grande en cada cuadro es lento (el juego se atrasa y se ve en camara
     * lenta); asi se hace una sola vez, y de nuevo solo si cambia el tamanio de la
     * ventana.
     *
     * @param luchador       duenio de la imagen
     * @param espejar        true para darla vuelta horizontalmente
     * @param escalaPantalla pixeles reales por cada pixel logico del area de juego
     * @return la imagen lista para copiar en su mitad de pantalla
     */
    private BufferedImage getRetratoPreparado(Luchador luchador, boolean espejar, double escalaPantalla) {
        int ancho = (int) Math.round(ANCHO / 2 * escalaPantalla);
        int alto = (int) Math.round(ALTO * escalaPantalla);
        String clave = luchador.getNombre() + (espejar ? " espejado" : "");
        BufferedImage preparado = retratosPreparados.get(clave);
        if (preparado == null || preparado.getWidth() != ancho || preparado.getHeight() != alto) {
            preparado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = preparado.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            Retrato retrato = apariencias.get(luchador).getRetrato();
            if (espejar) {
                /* Espejo: x pasa a ser ancho - x, y el foco queda del otro lado */
                g.translate(ancho, 0);
                g.scale(-1, 1);
            }
            Dibujo.dibujarImagenCubriendo(g, retrato.getImagen(), retrato.getFocoX(), 0, ancho, alto);
            g.dispose();
            retratosPreparados.put(clave, preparado);
        }
        return preparado;
    }
}
