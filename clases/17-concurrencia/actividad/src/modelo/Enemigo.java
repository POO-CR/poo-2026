// Archivo: Enemigo.java
// Un enemigo de la oleada. Varios jugadores lo atacan a la vez, y solo uno
// de ellos tendria que llevarse la baja.
package modelo;

public class Enemigo {

    private final String nombre;
    private int vida;

    public Enemigo(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
    }

    // Devuelve true si este golpe lo mato.
    // TODO 4: dos golpes a la vez pueden matarlo dos veces.
    public boolean recibirDanio(int danio) {
        if (this.vida > 0) {
            this.vida = this.vida - danio;
            if (this.vida <= 0) {
                return true;
            }
        }
        return false;
    }

    public String getNombre() {
        return this.nombre;
    }
}
