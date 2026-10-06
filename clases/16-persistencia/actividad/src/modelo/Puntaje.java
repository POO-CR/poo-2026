// Archivo: Puntaje.java
// Un puntaje de la tabla de honor. Nace valido o no nace.

package modelo;

import java.time.LocalDate;

public class Puntaje {

    private final String jugador;
    private final int puntos;
    private final LocalDate fecha;

    public Puntaje(String jugador, int puntos, LocalDate fecha) {
        if (jugador == null || jugador.isBlank() || puntos < 0 || fecha == null) {
            throw new IllegalArgumentException("Jugador, puntos y fecha son obligatorios");
        }
        this.jugador = jugador;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    public String getJugador() {
        return this.jugador;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }
}
