package modelo;

public class Sigiloso extends Personaje{
	private double probabilidadCritico;

    public Sigiloso(Integer id, String nombre, Bando bando, Integer salud, Integer ataque, Integer defensa, Integer velocidad, double probabilidadCritico) {
        super(id, nombre, bando, salud, ataque, defensa, velocidad);
        this.probabilidadCritico = probabilidadCritico;
    }

    @Override
    public int calcularAtaque() {
        boolean esCritico = Math.random() < probabilidadCritico;
        return esCritico ? (this.getAtaqueBase() * 2) : this.getAtaqueBase();
    }

    @Override
    public String ejecutarHabilidadEspecial(Personaje objetivo) {
        int danio = this.getAtaqueBase() + 15;
        objetivo.recibirDanio(danio);
        return getNombre() + " ataca desde las sombras con Golpe Crítico.";
    }
}
