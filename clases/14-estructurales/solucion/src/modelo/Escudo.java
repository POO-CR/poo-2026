// Archivo: Escudo.java (solucion)

package modelo;

public class Escudo extends Equipamiento {

    public Escudo(Combatiente portador) {
        super(portador);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con escudo";
    }

    @Override
    public int defensa() {
        return super.defensa() + 4;
    }
}
