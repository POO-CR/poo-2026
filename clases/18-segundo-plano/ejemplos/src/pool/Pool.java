// Archivo: Pool.java
// Ocho tareas en un pool de tres hilos: los nombres de los hilos se repiten
// porque se reutilizan. Cada tarea es un Callable que devuelve un valor, y
// submit() da un Future. Si se borra el shutdown(), el programa no termina.
package pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Pool {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Future<Integer>> resultados = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            int nivel = i;
            resultados.add(pool.submit(() -> {
                System.out.println("Nivel " + nivel + " en el hilo " + Thread.currentThread().getName());
                Thread.sleep(500);
                return nivel * 100;           // un Callable devuelve algo
            }));
        }
        System.out.println("main: las ocho tareas ya se enviaron, y sigo");

        int total = 0;
        for (Future<Integer> resultado : resultados) {
            total += resultado.get();         // espera si todavia no termino
        }
        System.out.println("main: total " + total);

        pool.shutdown();
    }
}
