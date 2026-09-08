// Archivo: Pago.java
// El dominio de las pre clases 5 a 8, reducido a lo que necesita el ejemplo.

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
}
