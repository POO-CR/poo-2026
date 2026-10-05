// Archivo: Tactica.java
// La interfaz de las formas de pelear de un enemigo, para el TODO 3. Recibe la
// unidad del enemigo y la de su objetivo, y devuelve que hacer.

package modelo;

public interface Tactica {
    Accion decidir(Unidad propia, Unidad objetivo);
}
