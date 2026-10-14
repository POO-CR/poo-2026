// Archivo: Intercambio.java
// Dos jugadores se pasan oro todo el tiempo, uno en cada direccion. El oro
// total no deberia cambiar nunca.

import modelo.Cofre;

public class Intercambio {

    static final int TRANSFERENCIAS = 50000;

    public static void main(String[] args) throws InterruptedException {
        Cofre deAna = new Cofre("Ana", 1000);
        Cofre deLuis = new Cofre("Luis", 1000);

        Thread ana = new Thread(() -> pasarOro(deAna, deLuis), "Ana");
        Thread luis = new Thread(() -> pasarOro(deLuis, deAna), "Luis");
        ana.start();
        luis.start();
        Vigia.esperar(ana, luis);

        System.out.println("Oro total: " + (deAna.getOro() + deLuis.getOro()) + "   (esperado 2000)");
    }

    private static void pasarOro(Cofre origen, Cofre destino) {
        for (int i = 1; i <= TRANSFERENCIAS; i++) {
            origen.transferir(destino, 1);
            if (i % 100 == 0) {
                System.out.println(origen.getNombre() + " lleva " + i + " transferencias");
            }
        }
    }
}
