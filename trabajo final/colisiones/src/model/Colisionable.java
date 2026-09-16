package model;

import java.awt.Rectangle;

public interface Colisionable {

    int getX();
    int getY();
    int getAncho();
    int getAlto();
    Rectangle getLimites();
    boolean colisionaCon(Colisionable otro);
    
}
