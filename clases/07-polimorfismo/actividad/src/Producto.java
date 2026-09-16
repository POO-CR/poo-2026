// Archivo: Producto.java
// Abstracta: modela lo comun a todo producto, pero no existe un producto que
// no sea un libro, un electronico o un alimento.

public abstract class Producto {

    private final String codigo;
    private final String nombre;
    private final double precio;

    public Producto(String codigo, String nombre, double precio) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    // Abstracto: el valor depende del tipo de producto, Producto no la sabe.
    // Declararlo aca obliga a cada subclase a escribirlo y, al mismo tiempo,
    // permite que el resto del programa lo llame sobre cualquier producto.
    public abstract double getPrecioFinal();

    // Concreto, y llama a un metodo del que esta clase no tiene una sola linea.
    public void mostrarDetalle() {
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: $" + this.precio);
        System.out.println("Precio final (IVA incluido): $" + this.getPrecioFinal());
    }
}
