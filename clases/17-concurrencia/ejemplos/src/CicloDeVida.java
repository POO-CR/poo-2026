// Archivo: CicloDeVida.java
// Los estados de un hilo con getState(), y que pasa al llamar a start() dos
// veces.
//
// Para pensar antes de ejecutar: ¿a que hilo duerme cada Thread.sleep()?
// ¿Que estado va a imprimir cada linea, y por que el ultimo antes del join()?

public class CicloDeVida {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lock) {
                System.out.println("  el hilo consiguio el lock");
            }
        }, "trabajador");

        System.out.println("Recien creado:        " + hilo.getState());
        synchronized (lock) {
            hilo.start();
            System.out.println("Despues de start():   " + hilo.getState());
            Thread.sleep(100);
            System.out.println("Durmiendo en sleep(): " + hilo.getState());
            Thread.sleep(700);
            System.out.println("Esperando el lock:    " + hilo.getState());
        }
        hilo.join();
        System.out.println("Despues de join():    " + hilo.getState());

        System.out.println("Ahora start() otra vez...");
        hilo.start();
    }
}
