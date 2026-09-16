// Archivo: PagoDebito.java

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

    @Override
    public String medio() {
        return "Debito";
    }
}
