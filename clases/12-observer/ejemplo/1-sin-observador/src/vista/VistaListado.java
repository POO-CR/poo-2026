// Archivo: VistaListado.java
// La segunda ventana: la tabla con los pagos del dia y el boton para anular
// el ultimo. La tabla se redibuja solo cuando alguien llama a actualizar().

package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Caja;

public class VistaListado {

    private final JFrame ventana;
    private final ModeloTablaPagos modeloTabla;
    private final JButton botonAnular;

    public VistaListado(Caja caja) {
        this.ventana = new JFrame("Pagos del dia");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.modeloTabla = new ModeloTablaPagos(caja);
        JTable tabla = new JTable(this.modeloTabla);
        JScrollPane panelTabla = new JScrollPane(tabla);   // barra de desplazamiento y encabezado
        panelTabla.setPreferredSize(new Dimension(360, 160));

        this.botonAnular = new JButton("Anular ultimo");

        this.ventana.add(panelTabla, BorderLayout.CENTER);
        this.ventana.add(this.botonAnular, BorderLayout.SOUTH);
        this.ventana.pack();
        this.ventana.setLocation(80, 80);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    public JButton getBotonAnular() {
        return this.botonAnular;
    }

    // La tabla vuelve a pedir filas y celdas al TableModel.
    public void actualizar() {
        this.modeloTabla.fireTableDataChanged();
    }
}
