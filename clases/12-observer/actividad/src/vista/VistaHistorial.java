// Archivo: VistaHistorial.java
// La ventana del historial: una tabla con las conversiones hechas. Se
// redibuja solo cuando alguien llama a actualizar().
//
// TODO 3: la vista implementa ObservadorConversor; actualizar() pasa a ser
// conversorCambio().

package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Conversor;

public class VistaHistorial {

    private final JFrame ventana;
    private final ModeloTablaConversiones modeloTabla;

    public VistaHistorial(Conversor conversor) {
        this.ventana = new JFrame("Historial");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.modeloTabla = new ModeloTablaConversiones(conversor);
        JTable tabla = new JTable(this.modeloTabla);
        JScrollPane panelTabla = new JScrollPane(tabla);
        panelTabla.setPreferredSize(new Dimension(360, 160));

        this.ventana.add(panelTabla, BorderLayout.CENTER);
        this.ventana.pack();
        this.ventana.setLocation(80, 80);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // La tabla vuelve a pedir filas y celdas al TableModel.
    public void actualizar() {
        this.modeloTabla.fireTableDataChanged();
    }
}
