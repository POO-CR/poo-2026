// Archivo: Descontable.java
// El contrato de lo que la tienda puede liquidar. No dice que es el objeto,
// dice que sabe hacer, asi que lo cumplen clases sin ningun parentesco entre
// si: un Libro, un Alimento y una Suscripcion, que no es un Producto.

public interface Descontable {

    // En una interfaz las constantes son public static final, aunque no se
    // escriba: no hay estado que ocultar, solo el contrato.
    int DESCUENTO_MAXIMO = 50;

    // Devuelve true si el descuento se pudo aplicar.
    boolean aplicarDescuento(int porcentaje);

    // Cada clase lo calcula sobre lo que para ella es el precio.
    double getPrecioConDescuento();
}
