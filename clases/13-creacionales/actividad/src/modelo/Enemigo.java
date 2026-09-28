// Archivo: Enemigo.java
// Lo comun a todo enemigo: nombre, vida y ataque. Cada tipo fija sus valores.

package modelo;

public abstract class Enemigo {

    private final String nombre;
    private int vida;
    private final int ataque;

    protected Enemigo(String nombre, int vida, int ataque) {
        if (nombre == null || nombre.isBlank() || vida <= 0 || ataque <= 0) {
            throw new IllegalArgumentException("Nombre, vida y ataque son obligatorios");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public boolean recibirDanio(int puntos) {
        if (puntos <= 0) {
            return false;
        }
        this.vida = Math.max(0, this.vida - puntos);
        return true;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }
}
