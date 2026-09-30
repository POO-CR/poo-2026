// Archivo: Programa.java
// Arma los dos bandos y resuelve tres ataques. Cada ataque son cuatro pasos
// contra el subsistema de combate, escritos aca a mano.

import java.util.ArrayList;
import java.util.List;

import modelo.Bitacora;
import modelo.Dado;
import modelo.Escuadra;
import modelo.ReglasCombate;
import modelo.Soldado;
import modelo.SoldadoConEspada;
import modelo.SoldadoConEspadaYEscudo;

public class Programa {

    public static void main(String[] args) {
        Soldado aragorn = new SoldadoConEspadaYEscudo("Aragorn", 12, 6);
        Soldado legolas = new SoldadoConEspada("Legolas", 10, 3);

        Escuadra rohan = new Escuadra("Rohan");
        rohan.agregar(new Soldado("Eomer", 8, 4));
        rohan.agregar(new Soldado("Eowyn", 7, 3));

        // El ejercito mezcla soldados sueltos y escuadras. Como no tienen nada
        // en comun, la lista es de Object.
        List<Object> ejercito = new ArrayList<>();
        ejercito.add(aragorn);
        ejercito.add(legolas);
        ejercito.add(rohan);

        Soldado orco = new Soldado("Orco", 6, 2);
        Soldado troll = new Soldado("Troll", 14, 8);

        Dado dado = new Dado(42);
        ReglasCombate reglas = new ReglasCombate();
        Bitacora bitacora = new Bitacora();

        // TODO 3: estos tres bloques pasan a ser una llamada cada uno.

        // Ataque 1: Aragorn contra el orco
        int tirada = dado.tirar();
        boolean critico = reglas.esCritico(tirada);
        int danio = reglas.danio(aragorn.ataque(), tirada, orco.defensa(), critico);
        bitacora.anotar(aragorn.descripcion() + " ataca a " + orco.descripcion()
                + ": tirada " + tirada + (critico ? ", critico" : "") + ", danio " + danio);

        // Ataque 2: Legolas contra el troll
        tirada = dado.tirar();
        critico = reglas.esCritico(tirada);
        danio = reglas.danio(legolas.ataque(), tirada, troll.defensa(), critico);
        bitacora.anotar(legolas.descripcion() + " ataca a " + troll.descripcion()
                + ": tirada " + tirada + (critico ? ", critico" : "") + ", danio " + danio);

        // Ataque 3: el ejercito entero contra el troll
        tirada = dado.tirar();
        critico = reglas.esCritico(tirada);
        danio = reglas.danio(ataqueTotal(ejercito), tirada, troll.defensa(), critico);
        bitacora.anotar("El ejercito ataca a " + troll.descripcion()
                + ": tirada " + tirada + (critico ? ", critico" : "") + ", danio " + danio);

        for (String linea : bitacora.getLineas()) {
            System.out.println(linea);
        }
        System.out.println("Ataque total del ejercito: " + ataqueTotal(ejercito));

        // Parte 3: el estandarte de Rohan.
    }

    // TODO 1: esta funcion desaparece.
    private static int ataqueTotal(List<Object> fuerzas) {
        int total = 0;
        for (Object fuerza : fuerzas) {
            if (fuerza instanceof Soldado) {
                total += ((Soldado) fuerza).ataque();
            } else if (fuerza instanceof Escuadra) {
                for (Soldado soldado : ((Escuadra) fuerza).getSoldados()) {
                    total += soldado.ataque();
                }
            }
        }
        return total;
    }
}
