// Archivo: Partida.java (solucion)
// La partida recibe el DAO por constructor y no sabe si detras hay una base,
// un archivo o una lista. No importa nada de java.sql.

package modelo;

import java.time.LocalDate;
import java.util.List;

public class Partida {

    private final String jugador;
    private final PuntajeDAO puntajes;
    private int puntos;

    public Partida(String jugador, PuntajeDAO puntajes) {
        if (jugador == null || jugador.isBlank() || puntajes == null) {
            throw new IllegalArgumentException("Jugador y DAO de puntajes son obligatorios");
        }
        this.jugador = jugador;
        this.puntajes = puntajes;
    }

    public boolean sumarPuntos(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        this.puntos += cantidad;
        return true;
    }

    public boolean terminar() {
        return this.puntajes.guardar(new Puntaje(this.jugador, this.puntos, LocalDate.now()));
    }

    public List<Puntaje> ranking() {
        return this.puntajes.mejores(5);
    }
}
