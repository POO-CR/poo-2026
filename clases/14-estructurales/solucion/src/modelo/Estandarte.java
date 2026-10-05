// Archivo: Estandarte.java (solucion)
// Parte 3: un estandarte se le da a una escuadra entera, y ni Escuadra ni
// las otras piezas cambiaron para que entrara.

package modelo;

public class Estandarte extends Equipamiento {

    public Estandarte(Combatiente portador) {
        super(portador);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con estandarte";
    }

    @Override
    public int ataque() {
        return super.ataque() + 3;
    }
}
