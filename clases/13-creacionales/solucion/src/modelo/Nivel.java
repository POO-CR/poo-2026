// Archivo: Nivel.java (solucion)
// Factory Method: la clase base hace el proceso de la oleada y deja el new
// en crearEnemigo(), que cada subclase implementa.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Nivel {

    private final String nombre;
    private final List<Enemigo> enemigos = new ArrayList<>();
    private final GestorAudio audio;

    protected Nivel(String nombre, GestorAudio audio) {
        if (nombre == null || nombre.isBlank() || audio == null) {
            throw new IllegalArgumentException("Nombre y gestor de audio son obligatorios");
        }
        this.nombre = nombre;
        this.audio = audio;
    }

    public boolean generarOleada(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        for (int i = 0; i < cantidad; i++) {
            this.enemigos.add(this.crearEnemigo());    // el factory method
        }
        this.audio.reproducir("cuerno");
        return true;
    }

    protected abstract Enemigo crearEnemigo();

    public String getNombre() {
        return this.nombre;
    }

    public List<Enemigo> getEnemigos() {
        return Collections.unmodifiableList(this.enemigos);
    }
}
