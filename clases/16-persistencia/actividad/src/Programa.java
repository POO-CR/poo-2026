// Archivo: Programa.java
// Dos partidas, una de Ana y una de Luis. Cada una guarda su puntaje, y al
// final se muestra la tabla de honor y cuantas conexiones se abrieron.

import modelo.Partida;
import modelo.Puntaje;

public class Programa {

    public static void main(String[] args) {
        Partida ana = new Partida("Ana");
        ana.sumarPuntos(800);
        ana.sumarPuntos(450);
        System.out.println("Guardado Ana: " + ana.terminar());

        Partida luis = new Partida("Luis");
        luis.sumarPuntos(900);
        System.out.println("Guardado Luis: " + luis.terminar());

        System.out.println("Tabla de honor:");
        for (Puntaje puntaje : luis.tablaDeHonor()) {
            System.out.println("  " + puntaje.getJugador() + " " + puntaje.getPuntos() + " " + puntaje.getFecha());
        }
        System.out.println("Conexiones abiertas: " + Partida.getConexionesAbiertas());

        // Parte 3: los mejores de Ana.
    }
}
