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

 
}
