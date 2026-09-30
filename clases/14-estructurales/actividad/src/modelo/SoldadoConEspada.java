// Archivo: SoldadoConEspada.java
// TODO 2: esta clase y las otras dos Soldado"Con" se borran.

package modelo;

public class SoldadoConEspada extends Soldado {

    public SoldadoConEspada(String nombre, int ataque, int defensa) {
        super(nombre, ataque, defensa);
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
