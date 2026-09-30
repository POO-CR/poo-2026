// Archivo: Heroe.java (solucion)
// Builder: lo obligatorio en el constructor del Builder, lo opcional con
// nombre y valor por defecto, y el constructor privado de Heroe valida todo
// junto en construir().

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

    private Heroe(Builder builder) {
        if (builder.nombre == null || builder.nombre.isBlank() || builder.audio == null) {
            throw new IllegalArgumentException("Nombre y gestor de audio son obligatorios");
        }
        if (builder.vida <= 0 || builder.ataque <= 0 || builder.defensa < 0) {
            throw new IllegalArgumentException("Vida, ataque y defensa deben ser validos");
        }
        this.nombre = builder.nombre;
        this.vida = builder.vida;
        this.ataque = builder.ataque;
        this.defensa = builder.defensa;
        this.arma = builder.arma;
        this.armadura = builder.armadura;
        this.montura = builder.montura;
        this.mascota = builder.mascota;
        this.audio = builder.audio;
    }

    public static class Builder {

        private final String nombre;
        private final GestorAudio audio;
        private int vida = 100;
        private int ataque = 10;
        private int defensa = 5;
        private String arma = "";
        private String armadura = "";
        private String montura = "";
        private String mascota = "";

        public Builder(String nombre, GestorAudio audio) {
            this.nombre = nombre;
            this.audio = audio;
        }

        public Builder vida(int vida) { this.vida = vida; return this; }
        public Builder ataque(int ataque) { this.ataque = ataque; return this; }
        public Builder defensa(int defensa) { this.defensa = defensa; return this; }
        public Builder conArma(String arma) { this.arma = arma; return this; }
        public Builder conArmadura(String armadura) { this.armadura = armadura; return this; }
        public Builder conMontura(String montura) { this.montura = montura; return this; }
        public Builder conMascota(String mascota) { this.mascota = mascota; return this; }

        public Heroe construir() {
            return new Heroe(this);
        }
    }

    public boolean atacar(Enemigo enemigo) {
        if (enemigo == null || !enemigo.estaVivo()) {
            return false;
        }
        this.audio.reproducir("espada");
        return enemigo.recibirDanio(this.ataque + this.bonusArma());
    }

    private int bonusArma() {
        return this.arma.isBlank() ? 0 : 5;
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
