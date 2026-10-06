// Archivo: TacticaCobarde.java (solucion)
// Parte 3: ataca hasta quedar con menos del 30% de la vida, y entonces huye.
// Enemigo y Partida no cambiaron para que entrara.

package modelo;

public class TacticaCobarde implements Tactica {

    @Override
    public Accion decidir(Unidad propia, Unidad objetivo) {
        if (propia.getVida() * 10 < propia.getVidaMaxima() * 3) {
            return Accion.HUIR;
        }
        return Accion.ATACAR;
    }
}
