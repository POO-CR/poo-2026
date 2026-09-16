// Archivo: Prestable.java (solucion)
// Lo que se puede llevar a domicilio. No lo cumplen todas las publicaciones,
// y por eso es una interfaz y no parte de Publicacion: una revista no tiene
// que escribir un prestar() que devuelve false.

public interface Prestable {

    // Devuelve true si el prestamo se pudo registrar.
    boolean prestar(String socio);

    // Devuelve true si la devolucion se pudo registrar.
    boolean devolver();

    double multaPorRetraso(int diasDeRetraso);
}
