// Archivo: Reloj.java
// El reloj de la partida. Cuenta decimas de segundo hasta que lo interrumpen.
package modelo;

public class Reloj implements Runnable {

    private int decimas;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.decimas++;
            if (this.decimas % 10 == 0) {
                System.out.println("Reloj: " + this.decimas / 10 + " s");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO 5: el reloj nunca se entera de que lo interrumpieron.
            }
        }
        System.out.println("Reloj detenido");
    }
}
