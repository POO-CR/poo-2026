// Archivo: TacticaAgresiva.java (solucion)
// Siempre ataca.

package modelo;

public class TacticaAgresiva implements Tactica {

    @Override
    public Accion decidir(Unidad propia, Unidad objetivo) {
        return Accion.ATACAR;
    }
}
