package salto;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada: ejecutar desde la raiz del proyecto para que se encuentre la carpeta "assets".
 */
public class App {

    public static void main(String[] args) {
        BufferedImage sprite = cargarSprite("assets/troll.png");

        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Demo de Salto");
            PanelSalto panel = new PanelSalto(sprite);

            JCheckBox checkboxSaltoVariable = new JCheckBox("Salto variable (soltar ESPACIO antes de tiempo lo corta)", true);
            checkboxSaltoVariable.addActionListener(e -> {
                panel.setSaltoVariable(checkboxSaltoVariable.isSelected());
                panel.requestFocusInWindow();
            });

            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.add(checkboxSaltoVariable, BorderLayout.NORTH);
            ventana.add(panel, BorderLayout.CENTER);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            panel.requestFocusInWindow();
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
