package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonajeDAO {
    

    /**
     * Obtiene todos los personajes registrados en la base de datos.
     */
    public List<Personaje> obtenerTodos() {
        List<Personaje> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, ubicacion_inicial, color_tropa_gratis, tipo_habilidad, descripcion, imagen FROM personajes ORDER BY id ASC";
        
        Connection con = ConexionBd.getInstancia().getConexion();
        if (con == null) {
            System.err.println("No hay conexión activa a la base de datos.");
            return lista;
        }

        try (Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Personaje p = mapearPersonaje(rs);
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los personajes: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Busca un personaje por su ID único.
     */
    public Personaje obtenerPorId(int id) {
        String sql = "SELECT id, nombre, ubicacion_inicial, color_tropa_gratis, tipo_habilidad, descripcion, imagen FROM personajes WHERE id = ?";
        Connection con = ConexionBd.getInstancia().getConexion();
        if (con == null) return null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPersonaje(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar personaje por ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Busca un personaje por su nombre exacto (ej. "Gandalf", "Frodo y Sam").
     */
    public Personaje obtenerPorNombre(String nombre) {
        String sql = "SELECT id, nombre, ubicacion_inicial, color_tropa_gratis, tipo_habilidad, descripcion, imagen FROM personajes WHERE nombre = ?";
        Connection con = ConexionBd.getInstancia().getConexion();
        if (con == null) return null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPersonaje(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar personaje por Nombre: " + e.getMessage());
        }
        return null;
    }

    /**
     * Mapea una fila del ResultSet hacia la instancia concreta de Personaje usando el Factory.
     */
    private Personaje mapearPersonaje(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String ubicacionInicial = rs.getString("ubicacion_inicial");
        String colorTropaGratis = rs.getString("color_tropa_gratis");
        Personaje p = PersonajeFactory.crear(id, nombre, ubicacionInicial, colorTropaGratis);
        p.setRutaImagen(rs.getString("imagen"));
        return p;
    }

}
