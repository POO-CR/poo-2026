// Archivo: PagoEfectivo.java

package modelo;

public class PagoEfectivo extends Pago {

    public PagoEfectivo(double monto) {
        super(monto);
    }

    @Override
    public double montoAcreditado() {
        return this.getMonto();   // sin comisiones
    }
}
