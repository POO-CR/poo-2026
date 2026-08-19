// Archivo: Libro.java
// Completa y funcionando. Es la clase mas vieja del catalogo.

public class Libro {

    // TODO 5: heredar de Producto con extends, borrar lo que quedo duplicado,
    //         llamar a super(...) como primera instruccion del constructor y
    //         sobrescribir mostrarDetalle() reutilizando super.mostrarDetalle()
    //         para el encabezado y agregando el autor y el ISBN.

    private static final double IVA = 0.21;

    private final String codigo;
    private final String nombre;
    private final double precio;
    private final String autor;
    private final String isbn;

    public Libro(String codigo, String nombre, double precio, String autor, String isbn) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN es obligatorio");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.autor = autor;
        this.isbn = isbn;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void mostrarDetalle() {
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: $" + this.precio);
        System.out.println("Precio final (IVA incluido): $" + (this.precio * (1 + IVA)));
        System.out.println("Autor: " + this.autor);
        System.out.println("ISBN: " + this.isbn);
    }
}
