// Archivo: Speedup.java
// Cuenta los primos hasta LIMITE con 1, 2, 4... hilos, con un tramo por hilo
// y con cien tramos, y calcula el speed-up y la eficiencia en esta maquina.
package speedup;

public class Speedup {

    static final int LIMITE = 5000000;

    public static void main(String[] args) throws Exception {
        int procesadores = Runtime.getRuntime().availableProcessors();
        System.out.println("Procesadores: " + procesadores);
        System.out.println("Hilos | un tramo por hilo         | cien tramos");

        Primos.contarEnParalelo(LIMITE, 2, 2);                // calentamiento
        long unoT1 = 0;
        long cienT1 = 0;
        for (int hilos = 1; hilos <= procesadores; hilos = siguiente(hilos, procesadores)) {
            long uno = medir(hilos, hilos);
            long cien = medir(hilos, 100);
            if (hilos == 1) {
                unoT1 = uno;
                cienT1 = cien;
            }
            System.out.printf("%5d | %5d ms  S %5.2f  E %4.2f | %5d ms  S %5.2f  E %4.2f%n", hilos,
                    uno, (double) unoT1 / uno, (double) unoT1 / uno / hilos,
                    cien, (double) cienT1 / cien, (double) cienT1 / cien / hilos);
        }
    }

    // 1, 2, 4, 8... y al final la cantidad de procesadores.
    static int siguiente(int hilos, int procesadores) {
        if (hilos == procesadores) {
            return procesadores + 1;
        }
        return Math.min(hilos * 2, procesadores);
    }

    // El mejor de tres tiempos, en milisegundos.
    static long medir(int hilos, int tramos) throws Exception {
        long mejor = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            long inicio = System.nanoTime();
            Primos.contarEnParalelo(LIMITE, hilos, tramos);
            mejor = Math.min(mejor, (System.nanoTime() - inicio) / 1000000);
        }
        return mejor;
    }
}
