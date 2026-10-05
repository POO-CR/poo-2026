// Archivo: Dado.java
// Parte del subsistema de combate. Un dado de seis caras con semilla fija,
// para que cada ejecucion de el mismo resultado.

package modelo;

import java.util.Random;

public class Dado {

    private final Random azar;

    public Dado(long semilla) {
        this.azar = new Random(semilla);
    }

    public int tirar() {
        return 1 + this.azar.nextInt(6);
    }
}
