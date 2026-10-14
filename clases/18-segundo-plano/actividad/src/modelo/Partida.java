// Archivo: Partida.java
// La partida de la clase 16. Al terminar guarda el puntaje y vuelve a cero.
// No sabe nada de hilos ni de ventanas.
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

    public int getPuntos() {
        return this.puntos;
    }

    public boolean terminar() {
        boolean guardado = this.puntajes.guardar(new Puntaje(this.jugador, this.puntos, LocalDate.now()));
        this.puntos = 0;
        return guardado;
    }

    public List<Puntaje> ranking() {
        return this.puntajes.mejores(5);
    }
}
