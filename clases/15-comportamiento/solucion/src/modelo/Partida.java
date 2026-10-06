// Archivo: Partida.java (solucion)
// State: la partida delega cada operacion en su Fase actual, y las fases
// deciden si la operacion se permite y a que fase se pasa. Partida conserva
// las acciones, visibles solo en el paquete para que las usen las fases.

package modelo;

public class Partida {

    private final Unidad jugador;
    private final Enemigo enemigo;
    private Fase fase = new TurnoJugador();

    public Partida(Unidad jugador, Enemigo enemigo) {
        if (jugador == null || enemigo == null) {
            throw new IllegalArgumentException("Jugador y enemigo son obligatorios");
        }
        this.jugador = jugador;
        this.enemigo = enemigo;
    }

    public boolean atacar() {
        return this.fase.atacar(this);
    }

    public boolean pasarTurno() {
        return this.fase.pasarTurno(this);
    }

    public boolean jugarEnemigo() {
        return this.fase.jugarEnemigo(this);
    }

    public boolean estaTerminada() {
        return this.fase.terminada();
    }

    void cambiarFase(Fase nueva) {
        this.fase = nueva;
    }

    void ejecutarAtaqueJugador() {
        this.enemigo.getUnidad().recibirDanio(this.jugador.getAtaque());
    }

    Accion ejecutarAccionEnemigo() {
        Accion accion = this.enemigo.decidir(this.jugador);
        switch (accion) {
            case ATACAR:
                this.jugador.recibirDanio(this.enemigo.getUnidad().getAtaque());
                break;
            case DEFENDER:
                this.enemigo.getUnidad().curar(5);
                break;
            case HUIR:
                break;
        }
        return accion;
    }

    boolean jugadorVivo() {
        return this.jugador.estaViva();
    }

    boolean enemigoVivo() {
        return this.enemigo.getUnidad().estaViva();
    }
}
