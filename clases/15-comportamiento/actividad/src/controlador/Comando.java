// Archivo: Comando.java
// La interfaz de las acciones del jugador, para el TODO 4. Cada comando sabe
// ejecutarse, devuelve si se pudo, y tiene un texto para el registro de combate.

package controlador;

public interface Comando {
    boolean ejecutar();
    String descripcion();
}
