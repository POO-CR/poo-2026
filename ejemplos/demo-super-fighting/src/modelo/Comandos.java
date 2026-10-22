package modelo;

/**
 * Lo que un jugador pide hacer en un tick. El controlador traduce el teclado a
 * comandos, y el modelo trabaja solo con esto, sin saber de donde vinieron.
 */
public class Comandos {

    private final boolean superAtaque;

    /**
     * @param superAtaque true si se acaba de presionar el super (Shinku Hadoken /
     *                    Kamehameha)
     */
    public Comandos(boolean superAtaque) {
        this.superAtaque = superAtaque;
    }

    /**
     * @return true si se acaba de presionar el super
     */
    public boolean isSuperAtaque() {
        return superAtaque;
    }
}
