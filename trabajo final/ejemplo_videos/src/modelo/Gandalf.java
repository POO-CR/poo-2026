package modelo;

public class Gandalf extends Personaje{
    public Gandalf(int id) {
        super(id, "Gandalf", "Tharbad", null);
    }

    @Override
    public Integer getCantidadReclutamiento() {
        return 2;
    }

    public int getRangoMovimiento(boolean vaSolo) {
        return vaSolo ? 2 : 1;
    }
}
