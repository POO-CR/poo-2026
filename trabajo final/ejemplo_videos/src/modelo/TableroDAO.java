package modelo;

import java.sql.*;

public class TableroDAO {

    public Tablero cargarTableroCompleto() {
        Tablero tablero = new Tablero();
        Connection con = ConexionBd.getInstancia().getConexion();
        if (con == null) return tablero;

        // 1. Cargar todas las ubicaciones (Nodos)
        String sqlNodos = "SELECT id, nombre, region, es_refugio, es_fortaleza, tropas_sombra, tropas_aliadas, tipo_tropa_aliada, pos_x, pos_y FROM ubicaciones";
        try (Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlNodos)) {

            while (rs.next()) {
                Ubicacion u = new Ubicacion(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("region"),
                    rs.getInt("es_refugio") == 1,
                    rs.getInt("es_fortaleza") == 1,
                    rs.getInt("tropas_sombra"),
                    rs.getInt("tropas_aliadas"),
                    rs.getString("tipo_tropa_aliada"),
                    rs.getInt("pos_x"),
                    rs.getInt("pos_y")
                );
                tablero.agregarUbicacion(u);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar ubicaciones: " + e.getMessage());
        }

        // 2. Cargar todas las conexiones (Aristas bidireccionales o dirigidas)
        String sqlAristas = "SELECT origen_id, destino_id, tipo_ruta, simbolo_coste FROM conexiones";
        try (Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlAristas)) {

            while (rs.next()) {
                Ubicacion origen = tablero.getUbicacionPorId(rs.getInt("origen_id"));
                Ubicacion destino = tablero.getUbicacionPorId(rs.getInt("destino_id"));

                if (origen != null && destino != null) {
                    TipoRuta tipo = TipoRuta.valueOf(rs.getString("tipo_ruta"));
                    String strSimbolo = rs.getString("simbolo_coste");
                    Simbolo coste = (strSimbolo != null) ? Simbolo.valueOf(strSimbolo) : null;

                    origen.agregarRuta(destino, tipo, coste);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar rutas: " + e.getMessage());
        }

        return tablero;
    }
}