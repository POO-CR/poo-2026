// Archivo: Primos.java
// Contar los primos de un rango, en un hilo o repartido en tramos entre
// varios hilos con un Executor.
package speedup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Primos {

    public static int contarPrimos(int desde, int hasta) {
        int cantidad = 0;
        for (int n = Math.max(desde, 2); n < hasta; n++) {
            boolean primo = true;
            for (int d = 2; d * d <= n; d++) {
                if (n % d == 0) {
                    primo = false;
                    break;
                }
            }
            if (primo) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public static int contarEnParalelo(int limite, int hilos, int tramos) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(hilos);
        List<Future<Integer>> partes = new ArrayList<>();
        int tramo = limite / tramos;
        for (int i = 0; i < tramos; i++) {
            int desde = i * tramo;
            int hasta = (i == tramos - 1) ? limite : desde + tramo;
            partes.add(pool.submit(() -> contarPrimos(desde, hasta)));
        }
        int total = 0;
        for (Future<Integer> parte : partes) {
            total += parte.get();
        }
        pool.shutdown();
        return total;
    }
}
