// Archivo: Libro.java
// Es un Producto y ademas sabe liquidarse: extends e implements conviven.

public class Libro extends Producto implements Descontable {

    private static final double IVA = 0.0; // el libro esta exento

    private final String autor;
    private final String isbn;
    private int porcentajeDescuento;

    public Libro(String codigo, String nombre, double precio, String autor, String isbn) {
        super(codigo, nombre, precio);
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN es obligatorio");
        }
        this.autor = autor;
        this.isbn = isbn;
        this.porcentajeDescuento = 0;
    }

    @Override
    public double getPrecioFinal() {
        return this.getPrecio() * (1 + IVA);
    }

    @Override
    public boolean aplicarDescuento(int porcentaje) {
        if (porcentaje < 0 || porcentaje > DESCUENTO_MAXIMO) {
            return false;
        }
        this.porcentajeDescuento = porcentaje;
        return true;
    }

    @Override
    public double getPrecioConDescuento() {
        return this.getPrecioFinal() * (1 - this.porcentajeDescuento / 100.0);
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Autor: " + this.autor);
        System.out.println("ISBN: " + this.isbn);
    }
}
