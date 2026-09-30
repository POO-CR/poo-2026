// Archivo: SoldadoConEspadaYEscudo.java
// Una subclase por combinacion. Con armadura harian falta cuatro mas.
// TODO 2: esta clase y las otras dos Soldado"Con" se borran.

package modelo;

public class SoldadoConEspadaYEscudo extends Soldado {

    public SoldadoConEspadaYEscudo(String nombre, int ataque, int defensa) {
        super(nombre, ataque, defensa);
    }

    @Override
    public String descripcion() {
        return super.descripcion() + " con espada con escudo";
    }

    @Override
    public int ataque() {
        return super.ataque() + 5;
    }

    @Override
    public int defensa() {
        return super.defensa() + 4;
    }
}
