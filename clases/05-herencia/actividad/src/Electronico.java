// Archivo: Electronico.java
// Nacio como copia de Libro.java. El que hizo la copia adapto casi todo.

public class Electronico {

    // TODO 6: lo mismo que el TODO 5, pero para esta clase. Al terminar,
    //         el autor fantasma desaparece solo.

    private static final double IVA = 0.21;

    private final String codigo;
    private final String nombre;
    private final double precio;
    private final String fabricante;
    private final int mesesGarantia;

    public Electronico(String codigo, String nombre, double precio, String fabricante, int mesesGarantia) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (fabricante == null || fabricante.isBlank()) {
            throw new IllegalArgumentException("El fabricante es obligatorio");
        }
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("La garantia no puede ser negativa");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.fabricante = fabricante;
        this.mesesGarantia = mesesGarantia;
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
        System.out.println("Autor: " + this.fabricante);
        System.out.println("Garantia: " + this.mesesGarantia + " meses");
    }
}
