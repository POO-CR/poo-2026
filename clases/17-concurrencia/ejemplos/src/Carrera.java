// Archivo: Carrera.java
// Dos enemigos que tendrian que avanzar cada uno en su hilo, a la vez.

public class Carrera {

    static class Enemigo implements Runnable {
        private final String nombre;

        Enemigo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(this.nombre + " avanza al casillero " + i + "   (hilo " + Thread.currentThread().getName() + ")");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println(this.nombre + " llego");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread orco = new Thread(new Enemigo("Orco"), "orco");
        Thread troll = new Thread(new Enemigo("Troll"), "troll");
        orco.run();          // ¿que esta mal?
        troll.run();
        orco.join();
        troll.join();
        System.out.println("Llegaron los dos");
    }
}
