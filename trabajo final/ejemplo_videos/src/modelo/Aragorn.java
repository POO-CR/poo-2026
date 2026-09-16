package modelo;

public class Aragorn extends Personaje {
    public Aragorn(int id) {
        super(id, "Aragorn", "Colinas de los Vientos", null);
    }

    public int getBajasPorImpacto() {
        return 2;
    }
}