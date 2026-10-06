// Archivo: Terminada.java (solucion)
// Con la partida terminada no se puede hacer nada.

package modelo;

public class Terminada implements Fase {

    @Override
    public boolean atacar(Partida partida) {
        return false;
    }

    @Override
    public boolean pasarTurno(Partida partida) {
        return false;
    }

    @Override
    public boolean jugarEnemigo(Partida partida) {
        return false;
    }

    @Override
    public boolean terminada() {
        return true;
    }
}
