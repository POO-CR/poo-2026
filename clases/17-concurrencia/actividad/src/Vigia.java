// Archivo: Vigia.java
// Mira cada dos segundos si hay hilos trabados esperandose entre si. Si los
// encuentra, muestra quien espera a quien y termina el programa. No se
// modifica.

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class Vigia {

    public static void esperar(Thread... hilos) throws InterruptedException {
        ThreadMXBean jvm = ManagementFactory.getThreadMXBean();
        for (Thread hilo : hilos) {
            while (hilo.isAlive()) {
                hilo.join(2000);
                long[] trabados = jvm.findDeadlockedThreads();
                if (trabados != null) {
                    System.out.println();
                    System.out.println("Vigia: hay hilos trabados (deadlock)");
                    for (ThreadInfo info : jvm.getThreadInfo(trabados)) {
                        System.out.println("  " + info.getThreadName() + " espera un lock que tiene " + info.getLockOwnerName());
                    }
                    System.exit(1);
                }
            }
        }
    }
}
