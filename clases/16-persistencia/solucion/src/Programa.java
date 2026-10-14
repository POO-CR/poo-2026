// Archivo: Programa.java (solucion)
// La conexion se obtiene una vez, el DAO la recibe por constructor y las
// partidas reciben el DAO. Una sola conexion para todo el programa.

import java.sql.Connection;
import java.sql.SQLException;

import modelo.Partida;
import modelo.Puntaje;
import modelo.PuntajeDAO;
import persistencia.Conexion;
import persistencia.PuntajeDAOSQLite;

public class Programa {

    public static void main(String[] args) throws SQLException {
        Connection conexion = Conexion.getInstancia().get();
        PuntajeDAO puntajes = new PuntajeDAOSQLite(conexion);

        Partida ana = new Partida("Ana", puntajes);
        ana.sumarPuntos(800);
        ana.sumarPuntos(450);
        System.out.println("Guardado Ana: " + ana.terminar());

        Partida luis = new Partida("Luis", puntajes);
        luis.sumarPuntos(900);
        System.out.println("Guardado Luis: " + luis.terminar());

        System.out.println("Tabla de ranking:");
        for (Puntaje puntaje : luis.ranking()) {
            System.out.println("  " + puntaje.getJugador() + " " + puntaje.getPuntos() + " " + puntaje.getFecha());
        }
        System.out.println("Conexiones abiertas: " + Conexion.getConexionesAbiertas());

        // Parte 3: los mejores de Ana.
        System.out.println("Mejores de Ana:");
        for (Puntaje puntaje : puntajes.mejoresDe("Ana", 3)) {
            System.out.println("  " + puntaje.getPuntos() + " " + puntaje.getFecha());
        }
    }
}
