// Archivo: Electronico.java (solucion)
// Con la herencia, la clase escribe solo sus lineas propias: no hay donde
// dejar un autor fantasma.

public class Electronico extends Producto {

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
