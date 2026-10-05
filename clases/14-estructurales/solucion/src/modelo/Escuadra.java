// Archivo: Escuadra.java (solucion)
// Composite: una escuadra es un Combatiente que contiene Combatientes, que
// pueden ser soldados, soldados equipados u otras escuadras. Cada operacion
// se resuelve recorriendo los hijos.

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Escuadra implements Combatiente {

    private final String nombre;
    private final List<Combatiente> miembros = new ArrayList<>();

    public Escuadra(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    public boolean agregar(Combatiente combatiente) {
        if (combatiente == null || combatiente == this) {
            return false;
        }
        this.miembros.add(combatiente);
        return true;
    }

    @Override
    public String descripcion() {
        List<String> nombres = new ArrayList<>();
        for (Combatiente miembro : this.miembros) {
            nombres.add(miembro.descripcion());
        }
        return this.nombre + " " + nombres;
    }

    @Override
    public int ataque() {
        int total = 0;
        for (Combatiente miembro : this.miembros) {
            total += miembro.ataque();
        }
        return total;
    }

    @Override
    public int defensa() {
        int total = 0;
        for (Combatiente miembro : this.miembros) {
            total += miembro.defensa();
        }
        return total;
    }
}
