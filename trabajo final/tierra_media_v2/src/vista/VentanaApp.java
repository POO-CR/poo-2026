package vista;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaApp extends JFrame {

	public static final int ANCHO_PANTALLA = 1920;
    public static final int ALTO_PANTALLA = 1080;

    public static final String CARD_SPLASH_MATERIA = "SPLASH_MATERIA";
    public static final String CARD_SPLASH_UNI = "SPLASH_UNI";
    public static final String CARD_VIDEO = "VIDEO";
    public static final String CARD_MENU = "MENU";
    
    private final CardLayout cardLayout;
    private final JPanel contenedor;
    private PanelVideoIntro panelVideo;
    
    public VentanaApp() {
        setTitle("The Lord of the Rings - Journeys in Middle-Earth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        
        contenedor.setPreferredSize(new Dimension(ANCHO_PANTALLA, ALTO_PANTALLA));

        contenedor.add(new SplashMateria(), CARD_SPLASH_MATERIA);
        contenedor.add(new SplashUniversidad(), CARD_SPLASH_UNI);
        
        this.panelVideo = new PanelVideoIntro(() -> mostrarTarjeta(CARD_MENU));
        contenedor.add(panelVideo, CARD_VIDEO);
        
        PanelMenuPrincipal menu = new PanelMenuPrincipal(
                () -> JOptionPane.showMessageDialog(this, "Iniciando selección de héroes..."),
                () -> JOptionPane.showMessageDialog(this, "Menú de Opciones: Volumen, Dificultad, etc.")
            );
            contenedor.add(menu, CARD_MENU);
        
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(contenedor);
        pack();
        setLocationRelativeTo(null);
    }

    public void mostrarTarjeta(String nombreTarjeta) {
        cardLayout.show(contenedor, nombreTarjeta);
        contenedor.revalidate();
        contenedor.repaint();
    }
    
    public PanelVideoIntro getPanelVideo() {
        return panelVideo;
    }
	
}
