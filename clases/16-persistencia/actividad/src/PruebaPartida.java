// Archivo: PruebaPartida.java
// Una prueba de Partida: suma puntos, termina y verifica que el puntaje
// aparezca en la tabla de honor. Para probar eso, hoy no hay otra forma que
// escribir en la base de verdad.

import modelo.Partida;
import modelo.Puntaje;

public class PruebaPartida {

    public static void main(String[] args) {
        Partida partida = new Partida("Prueba");
        partida.sumarPuntos(5000);
        boolean guardado = partida.terminar();

        boolean aparece = false;
        for (Puntaje puntaje : partida.tablaDeHonor()) {
            if (puntaje.getJugador().equals("Prueba") && puntaje.getPuntos() == 5000) {
                aparece = true;
            }
        }
        System.out.println(guardado && aparece ? "Prueba correcta" : "Prueba fallida");
    }
}
