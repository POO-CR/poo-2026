// Archivo: Libro.java (solucion)

public class Libro extends Producto {

    private final String autor;
    private final String isbn;

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
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Autor: " + this.autor);
        System.out.println("ISBN: " + this.isbn);
    }
}
