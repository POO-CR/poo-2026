// Archivo: Conexion.java (solucion)
// Singleton para la conexion: dos conexiones al mismo archivo SQLite se
// bloquean entre si. Se obtiene una vez en Programa y viaja por constructor.

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;
    private static int conexionesAbiertas = 0;

    private final Connection conexion;

    private Conexion() throws SQLException {
        this.conexion = DriverManager.getConnection("jdbc:sqlite:juego.db");
        conexionesAbiertas++;
    }

    public static Conexion getInstancia() throws SQLException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection get() {
        return this.conexion;
    }

    public static int getConexionesAbiertas() {
        return conexionesAbiertas;
    }
}
