// Archivo: RelojInterrumpido.java
// Un reloj que se detiene con interrupt(). Si se borra la linea del catch,
// la excepcion baja la marca y el reloj no se detiene nunca.

public class RelojInterrumpido {

    public static void main(String[] args) throws InterruptedException {
        Thread reloj = new Thread(() -> {
            int segundos = 0;
            while (!Thread.currentThread().isInterrupted()) {
                segundos++;
                System.out.println("Reloj: " + segundos + " s");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();    // volver a levantar la marca
                }
            }
            System.out.println("Reloj detenido");
        }, "reloj");

        reloj.start();
        Thread.sleep(3500);
        System.out.println("main: interrupt()");
        reloj.interrupt();
        reloj.join();
        System.out.println("Fin");
    }
}
