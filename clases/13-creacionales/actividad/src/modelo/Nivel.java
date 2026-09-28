// Archivo: Nivel.java
// Un nivel genera oleadas de enemigos. El proceso es siempre el mismo; lo
// que cambia entre niveles es que enemigo se crea, y eso hoy lo decide un
// texto y un switch.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nivel {

    private final String nombre;
    private final String tipoEnemigo;
    private final List<Enemigo> enemigos = new ArrayList<>();
    private final GestorAudio audio;

    public Nivel(String nombre, String tipoEnemigo) {
        if (nombre == null || nombre.isBlank() || tipoEnemigo == null) {
            throw new IllegalArgumentException("Nombre y tipo de enemigo son obligatorios");
        }
        this.nombre = nombre;
        this.tipoEnemigo = tipoEnemigo;
        this.audio = new GestorAudio();    // TODO 1: recibirlo, no crearlo
    }

    public boolean generarOleada(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        for (int i = 0; i < cantidad; i++) {
            Enemigo enemigo;
            // TODO 3: este switch es el new que hay que delegar en una subclase.
            switch (this.tipoEnemigo) {
                case "orco":
                    enemigo = new Orco();
                    break;
                case "troll":
                    enemigo = new Troll();
                    break;
                default:
                    return false;
            }
            this.enemigos.add(enemigo);
        }
        this.audio.reproducir("cuerno");
        return true;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Enemigo> getEnemigos() {
        return Collections.unmodifiableList(this.enemigos);
    }
}
