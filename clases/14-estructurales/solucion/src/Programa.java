// Archivo: Programa.java (solucion)
// El ejercito es una Escuadra que contiene soldados y escuadras, el
// equipamiento se apila envolviendo, y cada ataque es una llamada a Partida.

import modelo.Bitacora;
import modelo.Combatiente;
import modelo.Dado;
import modelo.Escuadra;
import modelo.Escudo;
import modelo.Espada;
import modelo.Estandarte;
import modelo.Partida;
import modelo.ReglasCombate;
import modelo.Soldado;

public class Programa {

    public static void main(String[] args) {
        Combatiente aragorn = new Escudo(new Espada(new Soldado("Aragorn", 12, 6)));
        Combatiente legolas = new Espada(new Soldado("Legolas", 10, 3));

        Escuadra rohan = new Escuadra("Rohan");
        rohan.agregar(new Soldado("Eomer", 8, 4));
        rohan.agregar(new Soldado("Eowyn", 7, 3));

        Escuadra ejercito = new Escuadra("Ejercito");
        ejercito.agregar(aragorn);
        ejercito.agregar(legolas);
        ejercito.agregar(new Estandarte(rohan));    // parte 3

        Combatiente orco = new Soldado("Orco", 6, 2);
        Combatiente troll = new Soldado("Troll", 14, 8);

        Bitacora bitacora = new Bitacora();
        Partida partida = new Partida(new Dado(42), new ReglasCombate(), bitacora);

        partida.atacar(aragorn, orco);
        partida.atacar(legolas, troll);
        partida.atacar(ejercito, troll);

        for (String linea : bitacora.getLineas()) {
            System.out.println(linea);
        }
        System.out.println("Ataque total del ejercito: " + ejercito.ataque());
    }
}
