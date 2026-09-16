package modelo;

// Gollum: No recluta, no ataca, no captura, pero guía en tiradas
public class Gollum extends Personaje {
    public Gollum(int id) {
        super(id, "Gollum", "Moria", null);
    }

    @Override public boolean puedeReclutar() { return false; }
    @Override public boolean puedeAtacar() { return false; }
    @Override public boolean puedeCapturar() { return false; }

    public int getReduccionDadosBusqueda() {
        return 3;
    }
}
