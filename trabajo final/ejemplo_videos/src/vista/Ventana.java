package vista;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame{

    private static Ventana instancia;
    
    public static final int ANCHO_PANTALLA = 1920;
    public static final int ALTO_PANTALLA = 1080;

    public static final String  CARD_SPLASH_MATERIA = "SPLASH_MATERIA";
    public static final String  CARD_SPLASH_UNI = "SPLASH_UNI";
    public static final String  CARD_VIDEO = "SPLASH_VIDEO";
    public static final String  CARD_MENU = "MENU";
    public static final String CARD_OPCIONES = "OPCIONES";
    public static final String CARD_JUEGO = "NUEVO_JUEGO";

    private final CardLayout cardLayout;
    private final JPanel contenedor;

    private SplashVideo splashVideo;
    

    private Ventana(){
        this.setTitle("Juego de ejemplo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.setPreferredSize(new Dimension(ANCHO_PANTALLA,ALTO_PANTALLA));

        //Aca podria agregar los demas splash que quisiera tener
        contenedor.add(new SplashTexto(),CARD_SPLASH_MATERIA);
        contenedor.add(new SplashImagen("src/assets/universidad.png"),CARD_SPLASH_UNI);

        this.splashVideo = new SplashVideo(()->mostrarSplash(CARD_VIDEO));
        contenedor.add(splashVideo, CARD_VIDEO);

        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.add(contenedor);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static synchronized Ventana getInstancia() {
        if (instancia == null) {
            instancia = new Ventana();
        }
        return instancia;
    }

    public void mostrarSplash(String nombre){
        cardLayout.show(contenedor, nombre);
        contenedor.revalidate();
        contenedor.repaint();
    }

    public SplashVideo getPanelVideo() {
        return splashVideo;
    }

    public JPanel getContenedor(){
        return this.contenedor;
    }

}
