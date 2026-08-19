package modelo;

public class Heroe extends Personaje{

	public Heroe(String nombre, int hp, int ataque, Posicion posicion) {
        super(nombre, hp, ataque, posicion);
    }

    @Override
    public boolean esHeroe() {
        return true;
    }
}
