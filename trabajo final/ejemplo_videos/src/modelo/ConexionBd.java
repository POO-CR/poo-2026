package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {
    private static ConexionBd instancia;
    private Connection conexion;
    private static final String URL = "jdbc:sqlite:src/assets/base.db";

    private ConexionBd() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión a SQLite establecida con éxito.");
        } catch (ClassNotFoundException e) {
            System.err.println("El driver sqlite-jdbc no está en el classpath: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQLite: " + e.getMessage());
        }
    }

    public static synchronized ConexionBd getInstancia() {
        try {
            if (instancia == null || instancia.getConexion().isClosed()) {
                instancia = new ConexionBd();
            }
        } catch (SQLException e) {
            instancia = new ConexionBd();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión a SQLite cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
