package model;

import java.awt.Rectangle;

public abstract class Personaje implements Colisionable, Movible{

    private int x;
    private int y;
    private int ancho;
    private int alto;
    private int velocidad;
    private String assetPath;

    public Personaje(int x, int y, int ancho, int alto, int velocidad, String assetPath) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.assetPath = assetPath;
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

    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
    public String getAssetPath() {return assetPath;}
    @Override public int getX() { return x; }
    @Override public int getY() { return y; }
    @Override public int getAncho() { return ancho; }
    @Override public int getAlto() { return alto; }
    @Override public int getVelocidad() { return velocidad; }
}
