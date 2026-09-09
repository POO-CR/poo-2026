import controller.MainController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import model.Obstaculo;
import model.Troll;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            final int ANCHO = 800;
            final int ALTO = 600;
            Troll jugador = new Troll(50, 50);
            List<Obstaculo> obstaculos = new ArrayList<>();
            obstaculos.add(new Obstaculo(350, 220, 100, 100, 0));
            obstaculos.add(new Obstaculo(180, 380, 200, 40, 0));
            MainController controlador = new MainController(jugador, obstaculos, ANCHO, ALTO);
            controlador.iniciar();
        });
    }
}
