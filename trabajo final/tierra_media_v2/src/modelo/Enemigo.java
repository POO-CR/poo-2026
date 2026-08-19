package modelo;

public class Enemigo extends Personaje{
	public Enemigo(String nombre, int hp, int ataque, Posicion posicion) {
        super(nombre, hp, ataque, posicion);
    }

    @Override
    public boolean esHeroe() {
        return false;
    }
}
