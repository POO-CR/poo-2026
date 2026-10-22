package vista;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La unica ventana del juego. Muestra el panel de la pelea
 * ({@link PanelPelea}). Arranca maximizada; el panel agranda su dibujo para
 * llenarla.
 */
public class VentanaJuego extends JFrame {

    public VentanaJuego() {
        super("Ken vs Goku");
        /* El controlador decide que hacer al cerrar */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setPreferredSize(new Dimension(PanelPelea.getAncho(), PanelPelea.getAlto()));
        pack();
        setLocationRelativeTo(null);
        /* Al restaurar la ventana vuelve al tamanio original de 960 x 540 */
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Cambia el panel que se ve en la ventana.
     *
     * @param panel panel a mostrar
     */
    public void mostrar(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
        panel.requestFocusInWindow();
    }
}
