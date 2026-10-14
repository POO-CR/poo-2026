// Archivo: PuntajeDAOLento.java
// Un Decorator que simula una base en un servidor lejano: cada operacion
// tarda dos segundos y despues la hace el DAO de adentro. No se modifica.
package persistencia;

import java.util.List;
import modelo.Puntaje;
import modelo.PuntajeDAO;

public class PuntajeDAOLento implements PuntajeDAO {

    private static final int DEMORA = 2000;

    private final PuntajeDAO adentro;

    public PuntajeDAOLento(PuntajeDAO adentro) {
        this.adentro = adentro;
    }

    @Override
    public boolean guardar(Puntaje puntaje) {
        System.out.println("Base remota: guardando " + puntaje.getJugador() + " " + puntaje.getPuntos()
                + " (hilo " + Thread.currentThread().getName() + ")");
        esperar();
        return this.adentro.guardar(puntaje);
    }

    @Override
    public List<Puntaje> mejores(int cantidad) {
        System.out.println("Base remota: buscando los " + cantidad + " mejores (hilo " + Thread.currentThread().getName() + ")");
        esperar();
        return this.adentro.mejores(cantidad);
    }

    private static void esperar() {
        try {
            Thread.sleep(DEMORA);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
