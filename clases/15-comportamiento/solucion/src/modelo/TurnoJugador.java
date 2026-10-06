// Archivo: TurnoJugador.java (solucion)
// En el turno del jugador se puede atacar o pasar. Atacar lleva al turno del
// enemigo, o al final si el enemigo murio.

package modelo;

public class TurnoJugador implements Fase {

    @Override
    public boolean atacar(Partida partida) {
        partida.ejecutarAtaqueJugador();
        partida.cambiarFase(partida.enemigoVivo() ? new TurnoEnemigo() : new Terminada());
        return true;
    }

    @Override
    public boolean pasarTurno(Partida partida) {
        partida.cambiarFase(new TurnoEnemigo());
        return true;
    }

    @Override
    public boolean jugarEnemigo(Partida partida) {
        return false;
    }

    @Override
    public boolean terminada() {
        return false;
    }
}
