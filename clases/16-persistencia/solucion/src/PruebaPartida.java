// Archivo: PruebaPartida.java (solucion)
// La misma prueba, con el DAO en memoria. No abre ninguna base ni deja
// rastro en juego.db.

import modelo.Partida;
import modelo.Puntaje;
import persistencia.PuntajeDAOMemoria;

public class PruebaPartida {

    public static void main(String[] args) {
        Partida partida = new Partida("Prueba", new PuntajeDAOMemoria());
        partida.sumarPuntos(5000);
        boolean guardado = partida.terminar();

        boolean aparece = false;
        for (Puntaje puntaje : partida.ranking()) {
            if (puntaje.getJugador().equals("Prueba") && puntaje.getPuntos() == 5000) {
                aparece = true;
            }
        }
        System.out.println(guardado && aparece ? "Prueba correcta" : "Prueba fallida");
    }
}
