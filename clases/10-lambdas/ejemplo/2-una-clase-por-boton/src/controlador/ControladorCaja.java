// Archivo: ControladorCaja.java
// Ya no implementa ActionListener: los
// receptores son ReceptorEfectivo y ReceptorDebito, y este constructor solo
// los crea y los registra.

package controlador;

import modelo.Caja;
import vista.VistaCaja;

public class ControladorCaja {

    public ControladorCaja(VistaCaja vista, Caja caja) {
        if (vista == null || caja == null) {
            throw new IllegalArgumentException("La vista y la caja son obligatorias");
        }
        vista.getBotonEfectivo().addActionListener(new ReceptorEfectivo(vista, caja));
        vista.getBotonDebito().addActionListener(new ReceptorDebito(vista, caja));
    }
}
