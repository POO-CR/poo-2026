// Archivo: Marcador.java
// Los puntos del equipo. Todos los jugadores suman en el mismo marcador.
package modelo;

public class Marcador {

    private int puntos;

    // TODO 1: dos jugadores que suman a la vez pierden puntos.
    public void sumar(int cantidad) {
        this.puntos = this.puntos + cantidad;
    }

    public int getPuntos() {
        return this.puntos;
    }
}
