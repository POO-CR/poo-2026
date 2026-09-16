package modelo;

public class Mago extends Personaje{
	private Integer mana;

    public Mago(Integer id, String nombre, Bando bando, Integer salud, Integer ataque, Integer defensa, Integer velocidad, Integer mana) {
        super(id, nombre, bando, salud, ataque, defensa, velocidad);
        this.mana = mana;
    }

    @Override
    public int calcularAtaque() {
        return this.getAtaqueBase() + 5;
    }

    @Override
    public String ejecutarHabilidadEspecial(Personaje objetivo) {
        if (mana >= 20) {
            mana -= 20;
            int danioMagico = this.getAtaqueBase() * 3;
            objetivo.recibirDanio(danioMagico);
            return getNombre() + " lanza Conjuro Arcano causando " + danioMagico + " de daño directo!";
        }
        return getNombre() + " no tiene suficiente maná.";
    }
}
