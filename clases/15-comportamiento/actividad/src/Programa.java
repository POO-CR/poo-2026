// Archivo: Programa.java
// Una pelea entre Aragorn y un troll con tactica defensiva, turno a turno. El
// jugador juega con el control: ataca con la 'a' y en la segunda ronda pasa
// con la 'p'.

import controlador.Control;
import modelo.Enemigo;
import modelo.Partida;
import modelo.Unidad;
import vista.PantallaConsola;

public class Programa {

    public static void main(String[] args) {
        Unidad aragorn = new Unidad("Aragorn", 60, 12);
        Enemigo troll = new Enemigo(new Unidad("Troll", 50, 9), "defensiva");
        Partida partida = new Partida(aragorn, troll, new PantallaConsola());
        Control control = new Control(partida);

        System.out.println("El enemigo juega en el turno del jugador: " + partida.jugarEnemigo());
        int ronda = 1;
        while (!partida.estaTerminada()) {
            System.out.println("Ronda " + ronda);
            control.presionar(ronda == 2 ? 'p' : 'a');
            partida.jugarEnemigo();
            ronda++;
        }
        System.out.println("Atacar con la partida terminada: " + control.presionar('a'));
        System.out.println("Registro: " + control.getRegistro());

        // Para quien termina antes: un orco cobarde.
    }
}
