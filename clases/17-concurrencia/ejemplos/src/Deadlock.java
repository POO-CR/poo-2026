// Archivo: Deadlock.java
// Ana le pasa oro a Luis y Luis a Ana, cada transferencia con los locks de
// los dos cofres. Si se traba, no termina nunca: se corta con Stop.

public class Deadlock {

    static class Cofre {
        final String nombre;
        int oro = 1000;

        Cofre(String nombre) {
            this.nombre = nombre;
        }

        void transferir(Cofre destino, int cantidad) {
            synchronized (this) {
                synchronized (destino) {
                    if (this.oro >= cantidad) {
                        this.oro -= cantidad;
                        destino.oro += cantidad;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Cofre ana = new Cofre("Ana");
        Cofre luis = new Cofre("Luis");
        Thread h1 = new Thread(() -> pasar(ana, luis));
        Thread h2 = new Thread(() -> pasar(luis, ana));
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println("Oro total: " + (ana.oro + luis.oro));
    }

    static void pasar(Cofre origen, Cofre destino) {
        System.out.println(origen.nombre + " empieza a pasarle oro a " + destino.nombre);
        for (int i = 0; i < 100000; i++) {
            origen.transferir(destino, 1);
        }
        System.out.println(origen.nombre + " termino");
    }
}
