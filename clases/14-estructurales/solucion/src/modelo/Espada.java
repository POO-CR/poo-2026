// Archivo: Espada.java (solucion)

package modelo;

public class Espada extends Equipamiento {

    public Espada(Combatiente portador) {
        super(portador);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con espada";
    }

    @Override
    public int ataque() {
        return super.ataque() + 5;
    }
}
