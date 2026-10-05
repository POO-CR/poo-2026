// Archivo: Partida.java (solucion)
// Facade: coordina el subsistema de combate. Quien juega pide un ataque con
// una llamada y no sabe que hay dado, reglas ni bitacora.

package modelo;

public class Partida {

    private final Dado dado;
    private final ReglasCombate reglas;
    private final Bitacora bitacora;

    public Partida(Dado dado, ReglasCombate reglas, Bitacora bitacora) {
        if (dado == null || reglas == null || bitacora == null) {
            throw new IllegalArgumentException("Dado, reglas y bitacora son obligatorios");
        }
        this.dado = dado;
        this.reglas = reglas;
        this.bitacora = bitacora;
    }

    public int atacar(Combatiente atacante, Combatiente defensor) {
        if (atacante == null || defensor == null) {
            return 0;
        }
        int tirada = this.dado.tirar();
        boolean critico = this.reglas.esCritico(tirada);
        int danio = this.reglas.danio(atacante.ataque(), tirada, defensor.defensa(), critico);
        this.bitacora.anotar(atacante.descripcion() + " ataca a " + defensor.descripcion()
                + ": tirada " + tirada + (critico ? ", critico" : "") + ", danio " + danio);
        return danio;
    }
}
