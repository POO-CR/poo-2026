// Archivo: Heroe.java
// El heroe tiene cuatro datos obligatorios y cuatro opcionales, todos en el
// mismo constructor. Mirar como se lo llama desde Programa.

package modelo;

public class Heroe {

    private final String nombre;
    private int vida;
    private final int ataque;
    private final int defensa;
    private final String arma;
    private final String armadura;
    private final String montura;
    private final String mascota;
    private final GestorAudio audio;

    // TODO 2: reemplazar este constructor por un Builder. Lo obligatorio es el
    // nombre y el gestor de audio; vida, ataque y defensa tienen valores por
    // defecto (100, 10 y 5) y el equipamiento es opcional.
    public Heroe(String nombre, int vida, int ataque, int defensa,
                 String arma, String armadura, String montura, String mascota) {
        if (nombre == null || nombre.isBlank() || vida <= 0 || ataque <= 0 || defensa < 0) {
            throw new IllegalArgumentException("Nombre, vida, ataque y defensa son obligatorios");
        }
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.arma = arma;
        this.armadura = armadura;
        this.montura = montura;
        this.mascota = mascota;
        this.audio = new GestorAudio();    // TODO 1: recibirlo, no crearlo
    }

    public boolean atacar(Enemigo enemigo) {
        if (enemigo == null || !enemigo.estaVivo()) {
            return false;
        }
        this.audio.reproducir("espada");
        return enemigo.recibirDanio(this.ataque + this.bonusArma());
    }

    private int bonusArma() {
        return (this.arma == null || this.arma.isBlank()) ? 0 : 5;
    }

    public String descripcion() {
        return this.nombre + " (vida " + this.vida + ", ataque " + this.ataque + ", defensa " + this.defensa
                + ", arma " + this.arma + ", armadura " + this.armadura
                + ", montura " + this.montura + ", mascota " + this.mascota + ")";
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getVida() {
        return this.vida;
    }
}
