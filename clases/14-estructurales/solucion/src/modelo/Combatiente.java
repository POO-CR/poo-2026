// Archivo: Combatiente.java
// Lo que el combate necesita de cualquier cosa que pelea: cuanto ataca, cuanto
// se defiende y como se llama para la bitacora.

package modelo;

public interface Combatiente {
    String descripcion();
    int ataque();
    int defensa();
}
