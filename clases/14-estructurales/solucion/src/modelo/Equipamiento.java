// Archivo: Equipamiento.java (solucion)
// Decorator: envuelve a un Combatiente y, por defecto, le delega todo. Cada
// pieza concreta sobrescribe lo que modifica.

package modelo;

public abstract class Equipamiento implements Combatiente {

    private final Combatiente portador;

    protected Equipamiento(Combatiente portador) {
        if (portador == null) {
            throw new IllegalArgumentException("El equipamiento necesita un portador");
        }
        this.portador = portador;
    }

    @Override
    public String descripcion() {
        return this.portador.descripcion();
    }

    @Override
    public int ataque() {
        return this.portador.ataque();
    }

    @Override
    public int defensa() {
        return this.portador.defensa();
    }
}
