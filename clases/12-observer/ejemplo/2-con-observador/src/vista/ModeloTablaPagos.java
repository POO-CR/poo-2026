// Archivo: ModeloTablaPagos.java
// El TableModel: lo que la JTable le pregunta para dibujarse. Cuantas filas,
// cuantas columnas, como se llama cada columna y que va en cada celda. Los
// datos no estan aca: se leen de la Caja cada vez que la tabla los pide.

package vista;

import javax.swing.table.AbstractTableModel;

import modelo.Caja;
import modelo.Pago;

public class ModeloTablaPagos extends AbstractTableModel {

    private static final String[] COLUMNAS = {"Medio", "Monto", "Acreditado"};

    private final Caja caja;

    public ModeloTablaPagos(Caja caja) {
        this.caja = caja;
    }

    @Override
    public int getRowCount() {
        return this.caja.getPagos().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int columna) {
        return COLUMNAS[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Pago pago = this.caja.getPagos().get(fila);
        switch (columna) {
            case 0: return pago.medio();
            case 1: return String.format("%.2f", pago.getMonto());
            case 2: return String.format("%.2f", pago.montoAcreditado());
            default: return "";
        }
    }
}
