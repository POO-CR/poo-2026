// Archivo: Electronico.java
// Los electronicos tributan el IVA general del 21%.

public class Electronico extends Producto {

    // TODO 4: declarar el IVA del 21% e implementar getPrecioFinal().

    // Un electronico NO se liquida: el precio lo fija el fabricante. Esta
    // clase no implementa Descontable, y esa es justamente la gracia.

    private final String fabricante;
    private final int mesesGarantia;

    public Electronico(String codigo, String nombre, double precio, String fabricante, int mesesGarantia) {
        super(codigo, nombre, precio);
        if (fabricante == null || fabricante.isBlank()) {
            throw new IllegalArgumentException("El fabricante es obligatorio");
        }
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("La garantia no puede ser negativa");
        }
        this.fabricante = fabricante;
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Fabricante: " + this.fabricante);
        System.out.println("Garantia: " + this.mesesGarantia + " meses");
    }
}
