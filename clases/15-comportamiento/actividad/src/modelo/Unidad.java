// Archivo: Unidad.java
// Un personaje en combate: nombre, vida y ataque.

package modelo;

public class Unidad {

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
        return true;
    }

    public boolean curar(int puntos) {
        if (puntos <= 0 || !this.estaViva()) {
            return false;
        }
        this.vida = Math.min(this.vidaMaxima, this.vida + puntos);
        return true;
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
