// Archivo: Programa.java (solucion)
// getInstancia() se llama una vez, aca, y el gestor viaja por constructor.
// El heroe se construye con el Builder y el nivel es una clase, no un texto.

import modelo.Enemigo;
import modelo.GestorAudio;
import modelo.Heroe;
import modelo.Nivel;
import modelo.NivelBosque;
import modelo.NivelPantano;

public class Programa {

    public static void main(String[] args) {
        GestorAudio audio = GestorAudio.getInstancia();
        audio.reproducir("musica");

        Heroe heroe = new Heroe.Builder("Aragorn", audio)
                .vida(120)
                .ataque(14)
                .defensa(6)
                .conArma("espada")
                .construir();

        Nivel bosque = new NivelBosque(audio);
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

        // Parte 3: el pantano, con arqueros. Nivel y Programa no cambiaron
        // para que entrara: es una clase nueva y estas dos lineas.
        Nivel pantano = new NivelPantano(audio);
        System.out.println("Oleada en el pantano: " + pantano.generarOleada(4)
                + ", " + pantano.getEnemigos().size() + " enemigos");
    }
}
