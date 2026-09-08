// Archivo: PagoDebito.java
// El banco se queda con una comision por cada pago con tarjeta de debito.

package modelo;

public class PagoDebito extends Pago {

    private static final double COMISION = 0.015;

    public PagoDebito(double monto) {
        super(monto);
    }

    @Override
    public double montoAcreditado() {
        return this.getMonto() * (1 - COMISION);
    }
}
