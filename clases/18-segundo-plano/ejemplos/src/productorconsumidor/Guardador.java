// Archivo: Guardador.java
// El consumidor: saca puntajes de la cola y tarda 500 ms en guardar cada
// uno. Termina cuando recibe FIN.
package productorconsumidor;

import java.util.concurrent.BlockingQueue;

public class Guardador implements Runnable {

    public static final String FIN = new String("FIN");

    private final BlockingQueue<String> pendientes;

    public Guardador(BlockingQueue<String> pendientes) {
        this.pendientes = pendientes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String puntaje = this.pendientes.take();    // espera si esta vacia
                if (puntaje == FIN) {
                    Programa.log("guardador: no viene nada mas");
                    return;
                }
                Thread.sleep(500);
                Programa.log("guardador: guardado " + puntaje);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
