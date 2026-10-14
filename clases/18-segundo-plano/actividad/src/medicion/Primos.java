// Archivo: Primos.java
// Contar los primos de un rango, en un hilo o repartido entre varios. Es el
// ejemplo de la pre clase.
package medicion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Primos {

    public static boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    public static int contarPrimos(int desde, int hasta) {
        int cantidad = 0;
        for (int n = desde; n < hasta; n++) {
            if (esPrimo(n)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    // TODO 5: un tramo por hilo. Agregar un parametro tramos.
    public static int contarEnParalelo(int limite, int hilos) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(hilos);
        List<Future<Integer>> partes = new ArrayList<>();
        int tramo = limite / hilos;
        for (int i = 0; i < hilos; i++) {
            int desde = i * tramo;
            int hasta = (i == hilos - 1) ? limite : desde + tramo;
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
