// Archivo: Indumentaria.java (solucion)
// El producto nuevo de la parte 3. Ni Tienda ni ninguno de sus metodos tuvo
// que cambiar para que entrara al catalogo: alcanzo con que sea un Producto.

public class Indumentaria extends Producto {

    private static final double IVA = 0.21;

    private final String talle;
    private final String color;

    public Indumentaria(String codigo, String nombre, double precio, String talle, String color) {
        super(codigo, nombre, precio);
        if (talle == null || talle.isBlank()) {
            throw new IllegalArgumentException("El talle es obligatorio");
        }
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("El color es obligatorio");
        }
        this.talle = talle;
        this.color = color;
    }

    @Override
    public double getPrecioFinal() {
        return this.getPrecio() * (1 + IVA);
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Talle: " + this.talle);
        System.out.println("Color: " + this.color);
    }
}
