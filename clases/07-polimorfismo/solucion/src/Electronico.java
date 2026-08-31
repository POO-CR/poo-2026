// Archivo: Electronico.java (solucion)
// Es un Producto y nada mas: el precio lo fija el fabricante, no se liquida.
// Por eso no implementa Descontable, y el compilador impide meterlo en la
// liquidacion por error.

public class Electronico extends Producto {

    private static final double IVA = 0.21;

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
    public double getPrecioFinal() {
        return this.getPrecio() * (1 + IVA);
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Fabricante: " + this.fabricante);
        System.out.println("Garantia: " + this.mesesGarantia + " meses");
    }
}
