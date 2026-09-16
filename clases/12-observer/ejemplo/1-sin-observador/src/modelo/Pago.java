// Archivo: Pago.java
// El dominio de las pre clases de pagos, reducido a lo que necesita el ejemplo.

package modelo;

public abstract class Pago {

    private final double monto;

    public Pago(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        this.monto = monto;
    }

    public double getMonto() {
        return this.monto;
    }

    // Lo que le queda al local despues de comisiones. Cada medio lo sabe.
    public abstract double montoAcreditado();

    // El nombre del medio de pago, para mostrar.
    public abstract String medio();
}
