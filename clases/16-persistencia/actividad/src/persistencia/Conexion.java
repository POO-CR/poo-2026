// Archivo: Conexion.java
// La conexion unica a juego.db, para el TODO 1. Hoy esta vacia.

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static int conexionesAbiertas = 0;

    // TODO 1: Singleton. Constructor privado que abre la conexion con
    // DriverManager.getConnection("jdbc:sqlite:juego.db") y suma uno a
    // conexionesAbiertas, atributo estatico con la unica instancia,
    // getInstancia() y un metodo get() que devuelve la Connection.

    public static int getConexionesAbiertas() {
        return conexionesAbiertas;
    }
}
