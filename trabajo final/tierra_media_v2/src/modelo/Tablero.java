package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private final int filas;
    private final int columnas;
    private final List<Personaje> personajes;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.personajes = new ArrayList<>();
    }

    public boolean esPosicionValida(Posicion pos) {
        return pos.getX() >= 0 && pos.getX() < columnas &&
               pos.getY() >= 0 && pos.getY() < filas;
    }

    public Personaje obtenerPersonajeEn(Posicion pos) {
        for (Personaje p : personajes) {
            if (p.estaVivo() && p.getPosicion().equals(pos)) {
                return p;
            }
        }
        return null;
    }

    public void agregarPersonaje(Personaje p) {
        if (esPosicionValida(p.getPosicion()) && obtenerPersonajeEn(p.getPosicion()) == null) {
            personajes.add(p);
        }
    }

    public void eliminarMuertos() {
        personajes.removeIf(p -> !p.estaVivo());
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public List<Personaje> getPersonajes() { return personajes; }
}
