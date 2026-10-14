// Archivo: PuntajeDAO.java
// La interfaz del DAO de la clase 16, en el modelo.
package modelo;

import java.util.List;

public interface PuntajeDAO {
    boolean guardar(Puntaje puntaje);
    List<Puntaje> mejores(int cantidad);
}
