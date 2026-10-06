// Archivo: Programa.java (solucion)
// La tactica se elige al armar el enemigo, la pantalla se registra como
// observadora de cada unidad, y la partida no conoce a la vista.

import modelo.Enemigo;
import modelo.Partida;
import modelo.TacticaCobarde;
import modelo.TacticaDefensiva;
import modelo.Unidad;
import vista.PantallaConsola;

public class Programa {

    public static void main(String[] args) {
        PantallaConsola pantalla = new PantallaConsola();

        Unidad aragorn = new Unidad("Aragorn", 60, 12);
        Unidad unidadTroll = new Unidad("Troll", 50, 9);
        aragorn.agregarObservador(pantalla);
        unidadTroll.agregarObservador(pantalla);
        Enemigo troll = new Enemigo(unidadTroll, new TacticaDefensiva());
        Partida partida = new Partida(aragorn, troll);

        System.out.println("El enemigo juega en el turno del jugador: " + partida.jugarEnemigo());
        int ronda = 1;
        while (!partida.estaTerminada()) {
            System.out.println("Ronda " + ronda);
            partida.atacar();
            partida.jugarEnemigo();
            ronda++;
        }
        System.out.println("Atacar con la partida terminada: " + partida.atacar());

        // Parte 3: un orco cobarde.
        System.out.println("Contra un orco cobarde");
        Unidad boromir = new Unidad("Boromir", 50, 10);
        Unidad unidadOrco = new Unidad("Orco", 40, 6);
        boromir.agregarObservador(pantalla);
        unidadOrco.agregarObservador(pantalla);
        Partida otra = new Partida(boromir, new Enemigo(unidadOrco, new TacticaCobarde()));
        while (!otra.estaTerminada()) {
            otra.atacar();
            otra.jugarEnemigo();
        }
        System.out.println("El orco sigue vivo: " + unidadOrco.estaViva());
    }
}
