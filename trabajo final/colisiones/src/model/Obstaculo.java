package model;

import java.awt.Rectangle;

public class Obstaculo implements Colisionable, Movible {

    private int x;
    private int y;
    private final int ancho;
    private final int alto;
    private final int velocidad;

    public Obstaculo(int x, int y, int ancho, int alto, int velocidad) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
    }

    @Override
    public Rectangle getLimites() {
        return new Rectangle(x, y, ancho, alto);
    }

    @Override
    public boolean colisionaCon(Colisionable otro) {
        if (otro == null || otro == this) return false;
        return this.getLimites().intersects(otro.getLimites());
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public int getX() { return x; }
    @Override public int getY() { return y; }
    @Override public int getAncho() { return ancho; }
    @Override public int getAlto() { return alto; }
    @Override public int getVelocidad() { return velocidad; }
    
}
