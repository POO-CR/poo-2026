package animacion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Ejecutar desde la raiz del proyecto para que se encuentre la carpeta "assets".
 */
public class App {

    /** Ruta de la hoja de sprites con los fotogramas del ciclo de caminata. */
    private static final String RUTA_HOJA_SPRITES = "assets/caminata/caminata.png";

    /** Cantidad de columnas de la hoja de sprites. */
    private static final int COLUMNAS_HOJA = 4;

    /** Cantidad de filas de la hoja de sprites. */
    private static final int FILAS_HOJA = 3;

    /** Cantidad de fotogramas del ciclo de caminata dentro de la hoja; si se agregan o sacan sprites, hay que actualizar este valor junto con {@link #COLUMNAS_HOJA} y {@link #FILAS_HOJA}. */
    private static final int CANTIDAD_FOTOGRAMAS = 10;

    public static void main(String[] args) {
        BufferedImage[] fotogramas = cargarFotogramas();

        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Demo de Animacion de Caminata");
            PanelCaminata panel = new PanelCaminata(fotogramas);

            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.add(panel);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            panel.requestFocusInWindow();
        });
    }

    /**
     * Carga los fotogramas del ciclo de caminata recortandolos de la hoja de sprites.
     *
     * @return los fotogramas en orden, listos para animar
     */
    private static BufferedImage[] cargarFotogramas() {
        BufferedImage hoja;
        try {
            hoja = ImageIO.read(new File(RUTA_HOJA_SPRITES));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la hoja de sprites: " + RUTA_HOJA_SPRITES
                    + " (ejecutar el programa desde la raiz del proyecto para que 'assets/' este disponible)", e);
        }

        int anchoFotograma = hoja.getWidth() / COLUMNAS_HOJA;
        int altoFotograma = hoja.getHeight() / FILAS_HOJA;

        BufferedImage[] fotogramas = new BufferedImage[CANTIDAD_FOTOGRAMAS];
        for (int i = 0; i < CANTIDAD_FOTOGRAMAS; i++) {
            int columna = i % COLUMNAS_HOJA;
            int fila = i / COLUMNAS_HOJA;
            fotogramas[i] = hoja.getSubimage(columna * anchoFotograma, fila * altoFotograma, anchoFotograma, altoFotograma);
        }
        return fotogramas;
    }
}
