// Archivo: Escuadra.java
// Un grupo de soldados. No es un Combatiente: quien la usa tiene que saber que
// es una escuadra y recorrerla. Y solo acepta soldados, asi que una escuadra
// no puede contener a otra.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO 1: que Escuadra sea un Combatiente que contiene Combatientes.
public class Escuadra {

    private final String nombre;
    private final List<Soldado> soldados = new ArrayList<>();

    public Escuadra(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }

    public boolean agregar(Soldado soldado) {
        if (soldado == null) {
            return false;
        }
        this.soldados.add(soldado);
        return true;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<Soldado> getSoldados() {
        return Collections.unmodifiableList(this.soldados);
    }
}
