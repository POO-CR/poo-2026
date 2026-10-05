// Archivo: Partida.java
// Una pelea por turnos entre el jugador y un enemigo. Tiene cuatro problemas,
// marcados como A y D aca, B en Enemigo y C en Control.

package modelo;

import vista.PantallaConsola;    // Problema D: el modelo conoce a la vista

public class Partida {

    private enum Etapa { TURNO_JUGADOR, TURNO_ENEMIGO, TERMINADA }

    private final Unidad jugador;
    private final Enemigo enemigo;
    private final PantallaConsola pantalla;
    private Etapa etapa = Etapa.TURNO_JUGADOR;

    public Partida(Unidad jugador, Enemigo enemigo, PantallaConsola pantalla) {
        if (jugador == null || enemigo == null || pantalla == null) {
            throw new IllegalArgumentException("Jugador, enemigo y pantalla son obligatorios");
        }
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.pantalla = pantalla;
    }

    // Problema A: un switch sobre la etapa en cada operacion.

    public boolean atacar() {
        switch (this.etapa) {
            case TURNO_JUGADOR:
                this.ejecutarAtaqueJugador();
                this.etapa = this.enemigo.getUnidad().estaViva() ? Etapa.TURNO_ENEMIGO : Etapa.TERMINADA;
                return true;
            case TURNO_ENEMIGO:
                return false;
            case TERMINADA:
                return false;
        }
        return false;
    }

    public boolean pasarTurno() {
        switch (this.etapa) {
            case TURNO_JUGADOR:
                this.etapa = Etapa.TURNO_ENEMIGO;
                return true;
            case TURNO_ENEMIGO:
                return false;
            case TERMINADA:
                return false;
        }
        return false;
    }

    public boolean jugarEnemigo() {
        switch (this.etapa) {
            case TURNO_JUGADOR:
                return false;
            case TURNO_ENEMIGO:
                Accion accion = this.ejecutarAccionEnemigo();
                if (accion == Accion.HUIR || !this.jugador.estaViva()) {
                    this.etapa = Etapa.TERMINADA;
                } else {
                    this.etapa = Etapa.TURNO_JUGADOR;
                }
                return true;
            case TERMINADA:
                return false;
        }
        return false;
    }

    public boolean estaTerminada() {
        return this.etapa == Etapa.TERMINADA;
    }

    // Las acciones en si. TODO 2: pasan a ser visibles en el paquete.

    private void ejecutarAtaqueJugador() {
        this.enemigo.getUnidad().recibirDanio(this.jugador.getAtaque());
        this.pantalla.mostrarVida(this.enemigo.getUnidad());       // Problema D
    }

    private Accion ejecutarAccionEnemigo() {
        Accion accion = this.enemigo.decidir(this.jugador);
        switch (accion) {
            case ATACAR:
                this.jugador.recibirDanio(this.enemigo.getUnidad().getAtaque());
                this.pantalla.mostrarVida(this.jugador);               // Problema D
                break;
            case DEFENDER:
                this.enemigo.getUnidad().curar(5);
                this.pantalla.mostrarVida(this.enemigo.getUnidad());   // Problema D
                break;
            case HUIR:
                break;
        }
        return accion;
    }
}
