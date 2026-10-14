// Archivo: Bitacora.java
// La lista de bajas. Los jugadores agregan y el narrador la recorre.
package modelo;

import java.util.ArrayList;
import java.util.List;

public class Bitacora {

    // TODO 3: una ArrayList no soporta que la toquen varios hilos.
    private final List<String> bajas = new ArrayList<>();

    public void registrarBaja(String jugador, String enemigo) {
        this.bajas.add(jugador + " derroto a " + enemigo);
    }

    public int contarBajasDe(String jugador) {
        int cantidad = 0;
        for (String baja : this.bajas) {
            if (baja.startsWith(jugador + " ")) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int getCantidad() {
        return this.bajas.size();
    }
}
