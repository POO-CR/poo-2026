// Archivo: Programa.java
// Arma un heroe, un nivel con su oleada, y simula un ataque. Al final
// imprime cuantos dispositivos de audio se abrieron.

import modelo.Enemigo;
import modelo.GestorAudio;
import modelo.Heroe;
import modelo.Nivel;

public class Programa {

    public static void main(String[] args) {
        GestorAudio audio = new GestorAudio();
        audio.reproducir("musica");

        // TODO 2: con el Builder, esta linea se lee sola.
        Heroe heroe = new Heroe("Aragorn", 120, 14, 6, "espada", null, "", null);

        // TODO 3: con Factory Method, el nivel es una clase, no un texto.
        Nivel bosque = new Nivel("Bosque", "orco");
        bosque.generarOleada(3);

        System.out.println(heroe.descripcion());
        System.out.println("Nivel " + bosque.getNombre() + ": " + bosque.getEnemigos().size() + " enemigos");
        for (Enemigo enemigo : bosque.getEnemigos()) {
            System.out.println("  " + enemigo.getNombre() + ", vida " + enemigo.getVida());
        }

        Enemigo primero = bosque.getEnemigos().get(0);
        heroe.atacar(primero);
        System.out.println("Despues del ataque: " + primero.getNombre() + ", vida " + primero.getVida());

        System.out.println("Dispositivos de audio abiertos: " + GestorAudio.getDispositivosAbiertos());

        // Parte 3: el pantano, con arqueros.
        // Nivel pantano = new Nivel("Pantano", "arquero");
        // System.out.println("Oleada en el pantano: " + pantano.generarOleada(4));
    }
}
