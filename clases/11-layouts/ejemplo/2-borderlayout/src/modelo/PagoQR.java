// Archivo: PagoQR.java
// Pago con codigo QR. La billetera se queda con una comision menor que la del
// debito. (En la pre clase de herencia tambien decodificaba el codigo
// escaneado; aca alcanza con el monto.)

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
}
