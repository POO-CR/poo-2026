// Archivo: ObservadorUnidad.java (solucion)
// La interfaz la define el modelo, desde lo que necesita: avisar que la vida
// de una unidad cambio.

package modelo;

public interface ObservadorUnidad {
    void vidaCambio(Unidad unidad);
}
