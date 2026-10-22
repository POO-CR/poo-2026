import controlador.ControladorPelea;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import modelo.Goku;
import modelo.Ken;
import modelo.Pelea;
import vista.AparienciaGoku;
import vista.AparienciaKen;
import vista.PanelPelea;
import vista.SpritesRayo;
import vista.VentanaJuego;

/**
 * Punto de entrada: carga las imagenes, arma modelo, vista y controlador, y
 * arranca la pelea. Ejecutar desde la raiz del proyecto para que se encuentre
 * la carpeta "assets".
 */
public class App {

    /** Movimientos de Ken (Capcom vs. SNK). */
    private static final String RUTA_HOJA_KEN_CVS = "assets/ken_cvs.png";

    /** Efectos de Ken: rayo del Shinku Hadoken (Marvel vs. Capcom 2). */
    private static final String RUTA_HOJA_KEN_MVC = "assets/ken_mvc.png";

    private static final String RUTA_HOJA_GOKU = "assets/goku.png";

    /** Imagenes de los personajes que se muestran al lanzar el super. */
    private static final String RUTA_RETRATO_KEN = "assets/ken_super.jpg";
    private static final String RUTA_RETRATO_GOKU = "assets/goku_super.jpg";

    public static void main(String[] args) {
        BufferedImage hojaKenCvs = cargarImagen(RUTA_HOJA_KEN_CVS);
        BufferedImage hojaKenMvc = cargarImagen(RUTA_HOJA_KEN_MVC);
        BufferedImage hojaGoku = cargarImagen(RUTA_HOJA_GOKU);
        BufferedImage retratoKen = cargarImagen(RUTA_RETRATO_KEN);
        BufferedImage retratoGoku = cargarImagen(RUTA_RETRATO_GOKU);

        SwingUtilities.invokeLater(() -> {
            /* Modelo: los luchadores y la pelea */
            Pelea pelea = new Pelea(new Ken(), new Goku(), PanelPelea.getAncho(), PanelPelea.getSueloY());

            /* Vista: como se ve cada luchador, en el mismo orden que en la pelea */
            /* Las piezas del rayo salen de la hoja de Ken de MvC y las usan los dos supers */
            PanelPelea panel = new PanelPelea(
                pelea, 
                new AparienciaKen(hojaKenCvs, retratoKen),
                new AparienciaGoku(hojaGoku, retratoGoku),
                new SpritesRayo(hojaKenMvc));
            /* La vista se registra: la pelea le avisa despues de cada cambio */
            pelea.agregarObservador(panel);
            VentanaJuego ventana = new VentanaJuego();

            ControladorPelea controladorPelea = new ControladorPelea(ventana, pelea, panel);

            controladorPelea.empezar();
            ventana.setVisible(true);
        });
    }

    /**
     * Carga una imagen desde disco.
     *
     * @param ruta ruta relativa a la raiz del proyecto
     * @return la imagen cargada
     */
    private static BufferedImage cargarImagen(String ruta) {
        try {
            return ImageIO.read(new File(ruta));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la imagen: " + ruta
                    + " (ejecutar el programa desde la raiz del proyecto para que 'assets/' este disponible)", e);
        }
    }
}
