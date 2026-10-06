// Archivo: TurnoEnemigo.java (solucion)
// En el turno del enemigo solo juega el enemigo. Despues vuelve el turno del
// jugador, salvo que el enemigo huya o el jugador muera.

package modelo;

public class TurnoEnemigo implements Fase {

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
        Accion accion = partida.ejecutarAccionEnemigo();
        if (accion == Accion.HUIR || !partida.jugadorVivo()) {
            partida.cambiarFase(new Terminada());
        } else {
            partida.cambiarFase(new TurnoJugador());
        }
        return true;
    }

    @Override
    public boolean terminada() {
        return false;
    }
}
