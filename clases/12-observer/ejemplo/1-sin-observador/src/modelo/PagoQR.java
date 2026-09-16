// Archivo: PagoQR.java

package modelo;

public class PagoQR extends Pago {

    private static final double COMISION = 0.008;

    public PagoQR(double monto) {
        super(monto);
    }

    @Override
    public double montoAcreditado() {
        return this.getMonto() * (1 - COMISION);
    }

    @Override
    public String medio() {
        return "QR";
    }
}
