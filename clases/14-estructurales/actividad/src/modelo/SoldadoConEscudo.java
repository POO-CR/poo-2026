// Archivo: SoldadoConEscudo.java
// TODO 2: esta clase y las otras dos Soldado"Con" se borran.

package modelo;

public class SoldadoConEscudo extends Soldado {

    public SoldadoConEscudo(String nombre, int ataque, int defensa) {
        super(nombre, ataque, defensa);
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
