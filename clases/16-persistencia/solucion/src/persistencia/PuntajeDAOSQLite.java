// Archivo: PuntajeDAOSQLite.java (solucion)
// La unica clase del programa que sabe que existe una tabla puntajes. Recibe
// la conexion por constructor y traduce entre objetos y filas.

package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Puntaje;
import modelo.PuntajeDAO;

public class PuntajeDAOSQLite implements PuntajeDAO {

    private final Connection conexion;

    public PuntajeDAOSQLite(Connection conexion) throws SQLException {
        if (conexion == null) {
            throw new IllegalArgumentException("La conexion es obligatoria");
        }
        this.conexion = conexion;
        try (Statement sentencia = conexion.createStatement()) {
            sentencia.execute("CREATE TABLE IF NOT EXISTS puntajes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "jugador TEXT NOT NULL, puntos INTEGER NOT NULL, fecha TEXT NOT NULL)");
        }
    }

    @Override
    public boolean guardar(Puntaje puntaje) {
        if (puntaje == null) {
            return false;
        }
        String sql = "INSERT INTO puntajes (jugador, puntos, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql)) {
            sentencia.setString(1, puntaje.getJugador());
            sentencia.setInt(2, puntaje.getPuntos());
            sentencia.setString(3, puntaje.getFecha().toString());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Puntaje> mejores(int cantidad) {
        return this.consultar("SELECT jugador, puntos, fecha FROM puntajes ORDER BY puntos DESC LIMIT ?",
                null, cantidad);
    }

    @Override
    public List<Puntaje> mejoresDe(String jugador, int cantidad) {
        return this.consultar("SELECT jugador, puntos, fecha FROM puntajes WHERE jugador = ? "
                + "ORDER BY puntos DESC LIMIT ?", jugador, cantidad);
    }

    // Las dos consultas solo difieren en el filtro: el recorrido del ResultSet
    // se escribe una vez.
    private List<Puntaje> consultar(String sql, String jugador, int cantidad) {
        List<Puntaje> resultado = new ArrayList<>();
        try (PreparedStatement sentencia = this.conexion.prepareStatement(sql)) {
            int hueco = 1;
            if (jugador != null) {
                sentencia.setString(hueco++, jugador);
            }
            sentencia.setInt(hueco, cantidad);
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
}
