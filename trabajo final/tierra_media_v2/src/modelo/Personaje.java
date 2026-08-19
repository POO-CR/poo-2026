package modelo;

public abstract class Personaje {
	
	private String nombre;
    private int hp;
    private int hpMax;
    private int ataque;
    private Posicion posicion;
    
    public Personaje(String nombre, int hp, int ataque, Posicion posicion) {
        this.nombre = nombre;
        this.hpMax = hp;
        this.hp = hp;
        this.ataque = ataque;
        this.posicion = posicion;
    }

    public void recibirDanio(int danio) {
        this.hp = Math.max(0, this.hp - danio);
    }

    public boolean estaVivo() {
        return this.hp > 0;
    }

    public abstract boolean esHeroe();

    public String getNombre() { return nombre; }
    public int getHp() { return hp; }
    public int getHpMax() { return hpMax; }
    public int getAtaque() { return ataque; }
    public Posicion getPosicion() { return posicion; }
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }
    
}
