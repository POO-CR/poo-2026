package modelo;

public class Guerrero extends Personaje{

	private Integer armaduraExtra;

    public Guerrero(Integer id, String nombre, Bando bando, Integer salud, Integer ataque, Integer defensa, Integer velocidad, Integer armaduraExtra) {
        super(id, nombre, bando, salud, ataque, defensa, velocidad);
        this.armaduraExtra = armaduraExtra;
    }
	
    @Override
    public int calcularAtaque() {
        return this.getAtaqueBase() + (int)(Math.random() * 8);
    }

    @Override
    public String ejecutarHabilidadEspecial(Personaje objetivo) {
        int golpe = (this.getAtaqueBase() * 2) - objetivo.getDefensaBase();
        objetivo.recibirDanio(golpe);
        return getNombre() + " ejecuta Golpe Devastador causando " + golpe + " de daño.";
    }
}
