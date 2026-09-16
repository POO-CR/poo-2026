package modelo;

public class FrodoYSam extends Personaje{
    public FrodoYSam(int id) {
        super(id, "Frodo y Sam", "La Comarca", null);
    }

    public boolean requiereTiradaBusquedaAlViajar(boolean gastoSigilo) {
        return !gastoSigilo;
    }
}
