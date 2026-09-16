// Archivo: ModeloTablaConversiones.java
// El TableModel de la tabla del historial: lo que la JTable le pregunta para
// dibujarse. Los datos se leen del Conversor cada vez que la tabla los pide.

package vista;

import javax.swing.table.AbstractTableModel;

import modelo.Conversion;
import modelo.Conversor;

public class ModeloTablaConversiones extends AbstractTableModel {

    private static final String[] COLUMNAS = {"Pesos", "Moneda", "Resultado"};

    private final Conversor conversor;

    public ModeloTablaConversiones(Conversor conversor) {
        this.conversor = conversor;
    }

    @Override
    public int getRowCount() {
        return this.conversor.getHistorial().size();
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
        Conversion conversion = this.conversor.getHistorial().get(fila);
        switch (columna) {
            case 0: return String.format("%.2f", conversion.getPesos());
            case 1: return conversion.getMoneda();
            case 2: return String.format("%.2f", conversion.getResultado());
            default: return "";
        }
    }
}
