// Archivo: SingletonConHilos.java
// Ocho hilos piden a la vez un Singleton que tarda en crearse: primero con
// getInstancia() comun, despues con getInstancia() synchronized.

import java.util.ArrayList;
import java.util.List;

public class SingletonConHilos {

    static class Audio {
        private static Audio instancia;
        static int creados;

        private Audio() {
            esperar();    // carga los sonidos
            creados++;
        }

        public static Audio getInstancia() {
            if (instancia == null) {
                instancia = new Audio();
            }
            return instancia;
        }
    }

    static class AudioSeguro {
        private static AudioSeguro instancia;
        static int creados;

        private AudioSeguro() {
            esperar();
            creados++;
        }

        public static synchronized AudioSeguro getInstancia() {
            if (instancia == null) {
                instancia = new AudioSeguro();
            }
            return instancia;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        lanzarOcho(() -> Audio.getInstancia());
        System.out.println("getInstancia():              " + Audio.creados + " objetos creados");
        lanzarOcho(() -> AudioSeguro.getInstancia());
        System.out.println("synchronized getInstancia(): " + AudioSeguro.creados + " objetos creados");
    }

    static void lanzarOcho(Runnable pedir) throws InterruptedException {
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            hilos.add(new Thread(pedir));
        }
        for (Thread hilo : hilos) {
            hilo.start();
        }
        for (Thread hilo : hilos) {
            hilo.join();
        }
    }

    static void esperar() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
