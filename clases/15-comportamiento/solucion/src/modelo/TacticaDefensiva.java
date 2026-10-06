// Archivo: TacticaDefensiva.java (solucion)
// Ataca mientras tiene mas de la mitad de la vida; despues se defiende.

package modelo;

public class TacticaDefensiva implements Tactica {

    @Override
    public Accion decidir(Unidad propia, Unidad objetivo) {
        if (propia.getVida() > propia.getVidaMaxima() / 2) {
            return Accion.ATACAR;
        }
        return Accion.DEFENDER;
    }
}
