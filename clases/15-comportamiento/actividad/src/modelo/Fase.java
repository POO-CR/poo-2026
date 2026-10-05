// Archivo: Fase.java
// La interfaz de las etapas de la partida, para el TODO 1. Cada operacion
// recibe la Partida para poder pedirle las acciones y el cambio de fase, y
// devuelve si la operacion se pudo hacer en esta etapa.

package modelo;

public interface Fase {
    boolean atacar(Partida partida);
    boolean pasarTurno(Partida partida);
    boolean jugarEnemigo(Partida partida);
    boolean terminada();
}
