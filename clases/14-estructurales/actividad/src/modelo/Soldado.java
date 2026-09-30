// Archivo: Soldado.java
// Un combatiente individual, sin equipamiento.

package modelo;

public class Soldado implements Combatiente {

    private final String nombre;
    private final int ataque;
    private final int defensa;

    public Soldado(String nombre, int ataque, int defensa) {
        if (nombre == null || nombre.isBlank() || ataque <= 0 || defensa < 0) {
            throw new IllegalArgumentException("Nombre, ataque y defensa son obligatorios");
        }
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    @Override
    public String descripcion() {
        return this.nombre;
    }

    @Override
    public int ataque() {
        return this.ataque;
    }

    @Override
    public int defensa() {
        return this.defensa;
    }
}
