import java.awt.Color;

public class Personaje {
    private String nombre;
    private int x;
    private int y;
    private Color color;
    private boolean seleccionado;
    private int hp;
    private int hpMax;
    private int ataque;
    private boolean esHeroe;


    public Personaje(String nombre, int x, int y, Color color, int hp, int ataque, boolean esHeroe) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.color = color;
        this.hpMax = hp;
        this.hp = hp;
        this.ataque = ataque;
        this.esHeroe = esHeroe;
        this.seleccionado = false;
    }

    public void recibirDanio(int cantidad) {
        this.hp -= cantidad;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean estaVivo() {
        return this.hp > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSeleccionado() { 
        return seleccionado; 
    }

    public void setSeleccionado(boolean seleccionado) { 
        this.seleccionado = seleccionado; 
    }

    public int getHp() { 
        return hp; 
    }

    public int getHpMax() { 
        return hpMax;
    }

    public int getAtaque() { 
        return ataque; 
    }

    public boolean isEsHeroe() { 
        return esHeroe; 
    }


}
