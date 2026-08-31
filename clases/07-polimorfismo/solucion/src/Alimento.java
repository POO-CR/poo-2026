// Archivo: Alimento.java (solucion)
// IVA reducido, y se liquida cuando se acerca el vencimiento.

public class Alimento extends Producto implements Descontable {

    private static final double IVA = 0.105;

    private final String fechaVencimiento;
    private int porcentajeDescuento;

    public Alimento(String codigo, String nombre, double precio, String fechaVencimiento) {
        super(codigo, nombre, precio);
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
        }
        this.fechaVencimiento = fechaVencimiento;
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
        System.out.println("Vence: " + this.fechaVencimiento);
    }
}
