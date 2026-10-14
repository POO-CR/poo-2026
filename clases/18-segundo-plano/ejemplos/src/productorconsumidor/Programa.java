// Archivo: Programa.java
// El productor: el juego deja un puntaje cada 100 ms en una cola con lugar
// para tres. El guardador tarda mas, asi que la cola se llena y put()
// espera. Al final, FIN le avisa al guardador que termine.
package productorconsumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Programa {

    static final long INICIO = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> pendientes = new ArrayBlockingQueue<>(3);
        Thread guardador = new Thread(new Guardador(pendientes));
        guardador.start();

        for (int i = 1; i <= 8; i++) {
            String puntaje = "partida " + i;
            log("juego: dejo " + puntaje + " (en la cola: " + pendientes.size() + ")");
            pendientes.put(puntaje);                         // espera si esta llena
            Thread.sleep(100);
        }
        log("juego: termine de jugar");
        pendientes.put(Guardador.FIN);
        guardador.join();
        log("fin");
    }

    static void log(String texto) {
        System.out.printf("%5d ms  %s%n", System.currentTimeMillis() - INICIO, texto);
    }
}
