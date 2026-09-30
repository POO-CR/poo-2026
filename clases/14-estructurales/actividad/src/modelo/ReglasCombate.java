// Archivo: ReglasCombate.java
// Parte del subsistema de combate. Las reglas del juego: cuanto danio hace un
// ataque y cuando es critico.

package modelo;

public class ReglasCombate {

    public boolean esCritico(int tirada) {
        return tirada == 6;
    }

    public int danio(int ataque, int tirada, int defensa, boolean critico) {
        int base = Math.max(0, ataque + tirada - defensa);
        return critico ? base * 2 : base;
    }
}
