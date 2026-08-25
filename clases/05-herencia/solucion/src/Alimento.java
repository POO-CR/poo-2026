// Archivo: Alimento.java (solucion)
// El producto nuevo de la parte 3. Todo lo comun ya lo tiene por herencia.

public class Alimento extends Producto {

    private static final double DESCUENTO = 0.20;

    private final String fechaVencimiento;

    public Alimento(String codigo, String nombre, double precio, String fechaVencimiento) {
        super(codigo, nombre, precio);
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
        }
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public void mostrarDetalle() {
        super.mostrarDetalle();
        System.out.println("Vence: " + this.fechaVencimiento);
        System.out.println("Precio oferta (20% de descuento): $" + (this.getPrecioFinal() * (1 - DESCUENTO)));
    }
}
