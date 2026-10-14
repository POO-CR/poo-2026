// Archivo: Arena.java
// Ocho jugadores contra una oleada de enemigos, cada uno en su hilo. Al
// final se muestra un resumen con lo que tendria que haber pasado.

import java.util.ArrayList;
import java.util.List;
import modelo.Bitacora;
import modelo.Configuracion;
import modelo.Enemigo;
import modelo.Jugador;
import modelo.Marcador;
import modelo.Narrador;
import modelo.Reloj;

public class Arena {

    static final int JUGADORES = 8;
    static final int ENEMIGOS = 20000;
    static final int VIDA = 10;
    static final int GOLPES_POR_ENEMIGO = 10;

    public static void main(String[] args) throws InterruptedException {
        List<Enemigo> oleada = new ArrayList<>();
        for (int i = 1; i <= ENEMIGOS; i++) {
            oleada.add(new Enemigo("enemigo " + i, VIDA));
        }
        Marcador marcador = new Marcador();
        Bitacora bitacora = new Bitacora();

        Thread reloj = new Thread(new Reloj(), "Reloj");
        Thread narrador = new Thread(new Narrador(bitacora, "Jugador 1"), "Narrador");
        reloj.start();
        narrador.start();

        List<Thread> jugadores = new ArrayList<>();
        for (int i = 1; i <= JUGADORES; i++) {
            String nombre = "Jugador " + i;
            jugadores.add(new Thread(new Jugador(nombre, oleada, GOLPES_POR_ENEMIGO, marcador, bitacora), nombre));
        }
        for (Thread jugador : jugadores) {
            jugador.start();
        }
        for (Thread jugador : jugadores) {
            jugador.join();
        }
        narrador.interrupt();
        narrador.join();

        System.out.println();
        System.out.println("Puntos del equipo:      " + marcador.getPuntos() + "   (esperado " + JUGADORES * ENEMIGOS * GOLPES_POR_ENEMIGO + ")");
        System.out.println("Bajas en la bitacora:   " + bitacora.getCantidad() + "   (esperado " + ENEMIGOS + ")");
        System.out.println("Configuraciones creadas: " + Configuracion.getCreadas() + "   (esperado 1)");
        System.out.println();

        System.out.println("Deteniendo el reloj...");
        reloj.interrupt();
        reloj.join();
        System.out.println("Fin de la partida");
    }
}
