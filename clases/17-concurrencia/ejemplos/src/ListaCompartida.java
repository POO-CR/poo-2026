// Archivo: ListaCompartida.java
// Un hilo agrega balas a una lista mientras otro la recorre para dibujarlas.
// Despues, cambiar la ArrayList por una CopyOnWriteArrayList.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListaCompartida {

    public static void main(String[] args) throws InterruptedException {
        List<String> balas = new ArrayList<>();    // o new CopyOnWriteArrayList<>()

        Thread disparos = new Thread(() -> {
            for (int i = 1; i <= 20000; i++) {
                balas.add("bala " + i);
            }
        }, "disparos");
        Thread dibujo = new Thread(() -> {
            for (int cuadro = 1; cuadro <= 200; cuadro++) {
                int dibujadas = 0;
                for (String bala : balas) {
                    dibujadas++;
                }
                if (cuadro % 50 == 0) {
                    System.out.println("Cuadro " + cuadro + ": " + dibujadas + " balas");
                }
            }
        }, "dibujo");

        disparos.start();
        dibujo.start();
        disparos.join();
        dibujo.join();
        System.out.println("Balas en la lista: " + balas.size() + " de 20000");
    }
}
