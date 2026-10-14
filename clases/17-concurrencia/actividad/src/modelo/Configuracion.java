// Archivo: Configuracion.java
// La configuracion del juego, un Singleton. Crearla tarda, porque lee el
// archivo de configuracion (aca se simula con una espera).
package modelo;

public class Configuracion {

    private static Configuracion instancia;
    private static int creadas;

    private final int danio;

    private Configuracion() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.danio = 1;
        creadas++;
    }

    // TODO 2: varios jugadores la piden a la vez y se crea mas de una.
    public static Configuracion getInstancia() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public static int getCreadas() {
        return creadas;
    }

    public int getDanio() {
        return this.danio;
    }
}
