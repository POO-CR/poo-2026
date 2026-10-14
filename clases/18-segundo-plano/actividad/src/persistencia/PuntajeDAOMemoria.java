// Archivo: PuntajeDAOMemoria.java
// El DAO en memoria de la clase 16. Sus metodos son synchronized porque mas
// de un hilo puede usarlo a la vez.
package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Puntaje;
import modelo.PuntajeDAO;

public class PuntajeDAOMemoria implements PuntajeDAO {

    private final List<Puntaje> puntajes = new ArrayList<>();

    @Override
    public synchronized boolean guardar(Puntaje puntaje) {
        if (puntaje == null) {
            return false;
        }
        this.puntajes.add(puntaje);
        return true;
    }

    @Override
    public synchronized List<Puntaje> mejores(int cantidad) {
        List<Puntaje> copia = new ArrayList<>(this.puntajes);
        copia.sort((a, b) -> b.getPuntos() - a.getPuntos());
        return new ArrayList<>(copia.subList(0, Math.min(cantidad, copia.size())));
    }
}
