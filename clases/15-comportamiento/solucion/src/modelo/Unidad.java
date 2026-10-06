// Archivo: Unidad.java (solucion)
// Un personaje en combate. Observer: avisa a sus observadores cada vez que su
// vida cambia, y no sabe quienes son.

package modelo;

import java.util.ArrayList;
import java.util.List;

public class Unidad {

    private final List<ObservadorUnidad> observadores = new ArrayList<>();

    private final String nombre;
    private final int vidaMaxima;
    private int vida;
    private final int ataque;

    public Unidad(String nombre, int vidaMaxima, int ataque) {
        if (nombre == null || nombre.isBlank() || vidaMaxima <= 0 || ataque <= 0) {
            throw new IllegalArgumentException("Nombre, vida y ataque son obligatorios");
        }
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.ataque = ataque;
    }

    public boolean recibirDanio(int puntos) {
        if (puntos <= 0 || !this.estaViva()) {
            return false;
        }
        this.vida = Math.max(0, this.vida - puntos);
        this.notificar();
        return true;
    }

    public boolean curar(int puntos) {
        if (puntos <= 0 || !this.estaViva()) {
            return false;
        }
        this.vida = Math.min(this.vidaMaxima, this.vida + puntos);
        this.notificar();
        return true;
    }

    public boolean agregarObservador(ObservadorUnidad observador) {
        if (observador == null) {
            return false;
        }
        this.observadores.add(observador);
        return true;
    }

    private void notificar() {
        for (ObservadorUnidad observador : this.observadores) {
            observador.vidaCambio(this);
        }
    }

    public boolean estaViva() {
        return this.vida > 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public int getAtaque() {
        return this.ataque;
    }
}
