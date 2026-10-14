// Archivo: PuntajeDAOMemoria.java (solucion)
// La misma interfaz sobre una lista. Sirve para probar la partida sin tocar
// el disco, y para programar antes de tener la base.

package persistencia;

import java.util.ArrayList;
import java.util.List;

import modelo.Puntaje;
import modelo.PuntajeDAO;

public class PuntajeDAOMemoria implements PuntajeDAO {

    private final List<Puntaje> puntajes = new ArrayList<>();

    @Override
    public boolean guardar(Puntaje puntaje) {
        if (puntaje == null) {
            return false;
        }
        this.puntajes.add(puntaje);
        return true;
    }

    @Override
    public List<Puntaje> mejores(int cantidad) {
        return this.primeros(new ArrayList<>(this.puntajes), cantidad);
    }

    @Override
    public List<Puntaje> mejoresDe(String jugador, int cantidad) {
        List<Puntaje> delJugador = new ArrayList<>();
        for (Puntaje puntaje : this.puntajes) {
            if (puntaje.getJugador().equals(jugador)) {
                delJugador.add(puntaje);
            }
        }
        return this.primeros(delJugador, cantidad);
    }

    private List<Puntaje> primeros(List<Puntaje> lista, int cantidad) {
        lista.sort((a, b) -> b.getPuntos() - a.getPuntos());
        return new ArrayList<>(lista.subList(0, Math.max(0, Math.min(cantidad, lista.size()))));
    }
}
