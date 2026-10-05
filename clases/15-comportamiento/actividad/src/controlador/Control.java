// Archivo: Control.java
// El panel del jugador: recibe una tecla y hace la accion que corresponde.
// Guarda el registro de combate con las acciones que se pudieron hacer.

package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Partida;

public class Control {

    private final Partida partida;
    private final List<String> registro = new ArrayList<>();

    public Control(Partida partida) {
        if (partida == null) {
            throw new IllegalArgumentException("La partida es obligatoria");
        }
        this.partida = partida;
    }

    // Problema C: cada tecla esta fija en una rama del switch, y cada rama
    // llama a la partida y arma su propia entrada del registro.
    public boolean presionar(char tecla) {
        switch (tecla) {
            case 'a':
                if (this.partida.atacar()) {
                    this.registro.add("atacar");
                    return true;
                }
                return false;
            case 'p':
                if (this.partida.pasarTurno()) {
                    this.registro.add("pasar turno");
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public List<String> getRegistro() {
        return List.copyOf(this.registro);
    }
}
