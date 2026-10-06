// Archivo: Partida.java
// Una partida suma puntos y, al terminar, guarda el puntaje en la base. Todo
// el acceso a la base esta aca adentro: el modelo importa java.sql, abre su
// propia conexion y escribe el SQL.

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Partida {

    private static int conexionesAbiertas = 0;

    private final String jugador;
    private final Connection conexion;
    private int puntos;

    public Partida(String jugador) {
        if (jugador == null || jugador.isBlank()) {
            throw new IllegalArgumentException("El jugador es obligatorio");
        }
        this.jugador = jugador;
        try {
            this.conexion = DriverManager.getConnection("jdbc:sqlite:juego.db");    // TODO 1
            conexionesAbiertas++;
            try (Statement sentencia = this.conexion.createStatement()) {
                sentencia.execute("CREATE TABLE IF NOT EXISTS puntajes ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "jugador TEXT NOT NULL, puntos INTEGER NOT NULL, fecha TEXT NOT NULL)");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("No se pudo abrir la base: " + e.getMessage());
        }
    }

    public boolean sumarPuntos(int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        this.puntos += cantidad;
        return true;
    }

    // TODO 3: el SQL de estos dos metodos se muda a PuntajeDAOSQLite.

    public boolean terminar() {
        String sql = "INSERT INTO puntajes (jugador, puntos, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql)) {
            sentencia.setString(1, this.jugador);
            sentencia.setInt(2, this.puntos);
            sentencia.setString(3, LocalDate.now().toString());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Puntaje> tablaDeHonor() {
        String sql = "SELECT jugador, puntos, fecha FROM puntajes ORDER BY puntos DESC LIMIT ?";
        List<Puntaje> resultado = new ArrayList<>();
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql)) {
            sentencia.setInt(1, 5);
            try (ResultSet filas = sentencia.executeQuery()) {
                while (filas.next()) {
                    resultado.add(new Puntaje(filas.getString("jugador"), filas.getInt("puntos"),
                            LocalDate.parse(filas.getString("fecha"))));
                }
            }
        } catch (SQLException e) {
            return resultado;
        }
        return resultado;
    }

    public static int getConexionesAbiertas() {
        return conexionesAbiertas;
    }
}
