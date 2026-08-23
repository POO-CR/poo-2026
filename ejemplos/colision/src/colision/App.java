package colision;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Ejecutar desde la raiz del proyecto para que se encuentre
 * la carpeta "assets".
 */
public class App {

    public static void main(String[] args) {
        BufferedImage troll1 = cargarSprite("assets/troll_001.png");
        BufferedImage troll2 = cargarSprite("assets/troll_002.png");

        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Demo de Colision");
            PanelArena panelArena = new PanelArena(troll1, troll2);

            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.add(panelArena);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            panelArena.requestFocusInWindow();
        });
    }

    /**
     * Carga un sprite desde disco.
     *
     * @param ruta ruta relativa a la raiz del proyecto
     * @return la imagen cargada
     */
    private static BufferedImage cargarSprite(String ruta) {
        try {
            return ImageIO.read(new File(ruta));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el sprite: " + ruta
                    + " (ejecutar el programa desde la raiz del proyecto para que 'assets/' este disponible)", e);
        }
    }
}
