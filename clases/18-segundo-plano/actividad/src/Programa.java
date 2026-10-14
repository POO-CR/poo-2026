// Archivo: Programa.java
// Arma el juego: la base remota (lenta) con tres puntajes de ejemplo, la
// partida de Ana, la vista y el controlador.

import java.time.LocalDate;
import javax.swing.SwingUtilities;
import modelo.Partida;
import modelo.Puntaje;
import modelo.PuntajeDAO;
import persistencia.PuntajeDAOLento;
import persistencia.PuntajeDAOMemoria;
import controlador.ControladorJuego;
import vista.VistaJuego;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuntajeDAOMemoria memoria = new PuntajeDAOMemoria();
            memoria.guardar(new Puntaje("Eva", 1500, LocalDate.now()));
            memoria.guardar(new Puntaje("Luis", 900, LocalDate.now()));
            memoria.guardar(new Puntaje("Juan", 600, LocalDate.now()));
            PuntajeDAO puntajes = new PuntajeDAOLento(memoria);    // TODO 4

            Partida partida = new Partida("Ana", puntajes);
            VistaJuego vista = new VistaJuego("Ana");
            new ControladorJuego(vista, partida);
            vista.mostrar();
        });
    }
}
