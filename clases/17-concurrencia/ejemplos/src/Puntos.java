// Archivo: Puntos.java
// Dos hilos suman un millon de puntos cada uno sobre el mismo marcador, con
// tres marcadores distintos: sin proteger, synchronized y AtomicInteger.

import java.util.concurrent.atomic.AtomicInteger;

public class Puntos {

    interface Marcador {
        void sumar(int cantidad);
        int getPuntos();
    }

    static class MarcadorSinProteger implements Marcador {
        private int puntos;
        public void sumar(int cantidad) { this.puntos = this.puntos + cantidad; }
        public int getPuntos() { return this.puntos; }
    }

    static class MarcadorSincronizado implements Marcador {
        private int puntos;
        public synchronized void sumar(int cantidad) { this.puntos = this.puntos + cantidad; }
        public synchronized int getPuntos() { return this.puntos; }
    }

    static class MarcadorAtomico implements Marcador {
        private final AtomicInteger puntos = new AtomicInteger();
        public void sumar(int cantidad) { this.puntos.addAndGet(cantidad); }
        public int getPuntos() { return this.puntos.get(); }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int vuelta = 1; vuelta <= 3; vuelta++) {
            System.out.println("Vuelta " + vuelta);
            probar("sin proteger ", new MarcadorSinProteger());
            probar("synchronized ", new MarcadorSincronizado());
            probar("AtomicInteger", new MarcadorAtomico());
        }
    }

    static void probar(String nombre, Marcador marcador) throws InterruptedException {
        Runnable sumarMillon = () -> {
            for (int i = 0; i < 1000000; i++) {
                marcador.sumar(1);
            }
        };
        long inicio = System.nanoTime();
        Thread a = new Thread(sumarMillon);
        Thread b = new Thread(sumarMillon);
        a.start();
        b.start();
        a.join();
        b.join();
        long ms = (System.nanoTime() - inicio) / 1000000;
        System.out.println("  " + nombre + ": " + marcador.getPuntos() + " puntos de 2000000, en " + ms + " ms");
    }
}
