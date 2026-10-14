// Archivo: PuntajeDAO.java (solucion)
// La interfaz del DAO vive en el modelo: la define lo que la partida necesita,
// en terminos de puntajes, sin una palabra de SQL.

package modelo;

import java.util.List;

public interface PuntajeDAO {
    boolean guardar(Puntaje puntaje);
    List<Puntaje> mejores(int cantidad);
    List<Puntaje> mejoresDe(String jugador, int cantidad);    // parte 3
}
