// Archivo: Suscripcion.java
// No hereda de nadie. Cumple el mismo contrato que Libro y Alimento, y lo
// calcula sobre su propio precio mensual: la interfaz pide el resultado, no
// la forma de obtenerlo.

public class Suscripcion implements Descontable {

    private final String plan;
    private final double precioMensual;
    private int porcentajeDescuento;

    public Suscripcion(String plan, double precioMensual) {
        if (plan == null || plan.isBlank()) {
            throw new IllegalArgumentException("El plan es obligatorio");
        }
        if (precioMensual <= 0) {
            throw new IllegalArgumentException("El precio mensual debe ser mayor a cero");
        }
        this.plan = plan;
        this.precioMensual = precioMensual;
        this.porcentajeDescuento = 0;
    }

    public String getPlan() {
        return this.plan;
    }

    public double getPrecioMensual() {
        return this.precioMensual;
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
        return this.precioMensual * (1 - this.porcentajeDescuento / 100.0);
    }

    public void mostrarDetalle() {
        System.out.println("Suscripcion: " + this.plan);
        System.out.println("Precio mensual: $" + this.precioMensual);
    }
}
