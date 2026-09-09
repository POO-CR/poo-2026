package model;

public interface Movible {
    void mover(int deltaX, int deltaY);
    void setPosicion(int x, int y);
    int getVelocidad();
}
