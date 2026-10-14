// Archivo: Narrador.java
// Mientras dura la pelea, cuenta cada tanto las bajas de un jugador.
package modelo;

public class Narrador implements Runnable {

    private final Bitacora bitacora;
    private final String jugador;

    public Narrador(Bitacora bitacora, String jugador) {
        this.bitacora = bitacora;
        this.jugador = jugador;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Narrador: " + this.jugador + " lleva " + this.bitacora.contarBajasDe(this.jugador) + " bajas");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
