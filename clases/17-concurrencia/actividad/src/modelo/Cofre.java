// Archivo: Cofre.java
// Un cofre con oro. Una transferencia toma el lock de los dos cofres, para
// que nadie vea el oro a medio pasar de uno al otro.
package modelo;

public class Cofre {

    private final String nombre;
    private int oro;

    public Cofre(String nombre, int oro) {
        this.nombre = nombre;
        this.oro = oro;
    }

    // TODO 6: dos transferencias en sentidos opuestos se traban.
    public void transferir(Cofre destino, int cantidad) {
        synchronized (this) {
            synchronized (destino) {
                if (this.oro >= cantidad) {
                    this.oro = this.oro - cantidad;
                    destino.oro = destino.oro + cantidad;
                }
            }
        }
    }

    public synchronized int getOro() {
        return this.oro;
    }

    public String getNombre() {
        return this.nombre;
    }
}
