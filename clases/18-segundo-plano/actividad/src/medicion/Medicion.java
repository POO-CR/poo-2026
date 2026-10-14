// Archivo: Medicion.java
// Mide cuanto tarda contar los primos hasta LIMITE con distintas cantidades
// de hilos. Cada tiempo es la mediana de cinco corridas, despues de una
// corrida de calentamiento.
package medicion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Medicion {

    static final int LIMITE = 5000000;
    static final int CORRIDAS = 5;

    public static void main(String[] args) throws Exception {
        int procesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Procesadores: " + procesadores);
        List<Integer> cantidades = cantidadesDeHilos(procesadores);

        Primos.contarEnParalelo(LIMITE, 2);
        System.out.println();
        System.out.println("Un tramo por hilo");
        for (int hilos : cantidades) {
            long ms = medir(() -> Primos.contarEnParalelo(LIMITE, hilos));
            System.out.println("  " + hilos + " hilos: " + ms + " ms");
        }

        // TODO 5: repetir con cien tramos.
    }

    // 1, 2, 4, 8... hasta la cantidad de procesadores, y esa cantidad al final.
    static List<Integer> cantidadesDeHilos(int procesadores) {
        List<Integer> cantidades = new ArrayList<>();
        for (int hilos = 1; hilos < procesadores; hilos *= 2) {
            cantidades.add(hilos);
        }
        cantidades.add(procesadores);
        return cantidades;
    }

    // Ejecuta la tarea CORRIDAS veces y devuelve la mediana, en milisegundos.
    static long medir(Callable<Integer> tarea) throws Exception {
        long[] tiempos = new long[CORRIDAS];
        for (int i = 0; i < CORRIDAS; i++) {
            long inicio = System.nanoTime();
            tarea.call();
            tiempos[i] = (System.nanoTime() - inicio) / 1000000;
        }
        Arrays.sort(tiempos);
        return tiempos[CORRIDAS / 2];
    }
}
