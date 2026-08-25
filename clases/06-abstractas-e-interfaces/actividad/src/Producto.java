// Archivo: Producto.java
// La superclase de la clase pasada, tal como quedo. Se modifica en la parte 2.

public class Producto {
    // TODO 1: esta clase no deberia poder instanciarse. Declararla abstract.
    // TODO 2: el IVA no es el mismo para todos los productos, asi
    //         que esta constante se va de aca. Cada subclase declara la suya.
    private static final double IVA = 0.21;

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

    // TODO 2 (sigue): Producto no puede saber que IVA le corresponde a cada
    //         producto. Dejar este metodo declarado y sin cuerpo, marcado como
    //         abstract, para que cada subclase quede obligada a escribirlo.
    public double getPrecioFinal() {
        return this.precio * (1 + IVA);
    }

    // Este metodo se queda como esta: el encabezado del catalogo es igual para
    // todos. Vale la pena mirar que llama a getPrecioFinal() sin saber quien
    // lo va a responder.
    public void mostrarDetalle() {
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: $" + this.precio);
        System.out.println("Precio final (IVA incluido): $" + this.getPrecioFinal());
    }
}
