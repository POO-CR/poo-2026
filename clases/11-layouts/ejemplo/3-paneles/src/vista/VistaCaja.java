// Archivo: VistaCaja.java (etapa 3: paneles anidados)
// Norte: un panel con la etiqueta y el campo. Centro: un panel con los
// botones. Sur: el total. Cada panel tiene su propio layout manager. Ningun
// componente tiene coordenadas: la ventana se dimensiona con pack(). Al
// agrandarla, los layouts recalculan y las fuentes se escalan en proporcion
// (escalarFuentes), porque el layout no cambia el tamano del texto.

package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VistaCaja {

    private final JFrame ventana;
    private final JTextField campoMonto;
    private final JLabel etiquetaTotal;
    private final JButton botonEfectivo;
    private final JButton botonDebito;
    private final JButton botonQR;
    private final int anchoInicial;
    private final int altoInicial;
    private final float fuenteInicial;

    public VistaCaja() {
        this.ventana = new JFrame("Caja");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLayout(new BorderLayout(10, 10));   // 10 px entre regiones

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField(12);   // 12 columnas: el campo pide su ancho
        this.botonEfectivo = new JButton("Efectivo");
        this.botonDebito = new JButton("Debito");
        this.botonQR = new JButton("QR");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        // Arriba: la etiqueta y el campo, uno al lado del otro.
        JPanel panelMonto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonto.add(etiquetaMonto);
        panelMonto.add(this.campoMonto);

        // Centro: los botones, en una fila de celdas iguales.
        JPanel panelBotones = new JPanel(new GridLayout(1, 0, 8, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));   // margen
        panelBotones.add(this.botonEfectivo);
        panelBotones.add(this.botonDebito);
        panelBotones.add(this.botonQR);

        // Abajo: el total.
        this.etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        this.ventana.add(panelMonto, BorderLayout.NORTH);
        this.ventana.add(panelBotones, BorderLayout.CENTER);
        this.ventana.add(this.etiquetaTotal, BorderLayout.SOUTH);

        this.ventana.pack();                         // el tamano sale del tamano preferido de los componentes
        this.ventana.setLocationRelativeTo(null);    // despues de pack(), para centrar con el tamano real

        // Al cambiar el tamano de la ventana, las fuentes se escalan en proporcion.
        this.anchoInicial = this.ventana.getWidth();
        this.altoInicial = this.ventana.getHeight();
        this.fuenteInicial = this.etiquetaTotal.getFont().getSize2D();
        this.ventana.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evento) {
                escalarFuentes();
            }
        });
    }


    // Un layout manager calcula posicion y tamano de los componentes; la fuente
    // no cambia. Para que el texto crezca con la ventana, se escalan las fuentes
    // en proporcion y los layouts recalculan (el tamano preferido depende de la fuente).
    private void escalarFuentes() {
        float factor = Math.min((float) this.ventana.getHeight() / this.altoInicial,
                                (float) this.ventana.getWidth() / this.anchoInicial);
        float tamano = this.fuenteInicial * Math.max(1f, factor);
        escalar(this.ventana.getContentPane(), tamano);
        this.ventana.revalidate();
    }

    private void escalar(Container contenedor, float tamano) {
        for (Component hijo : contenedor.getComponents()) {
            hijo.setFont(hijo.getFont().deriveFont(tamano));
            if (hijo instanceof Container) {
                escalar((Container) hijo, tamano);
            }
        }
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // Las fuentes de eventos. Se exponen para que el controlador se registre.
    public JButton getBotonEfectivo() {
        return this.botonEfectivo;
    }

    public JButton getBotonDebito() {
        return this.botonDebito;
    }

    public JButton getBotonQR() {
        return this.botonQR;
    }

    public JTextField getCampoMonto() {
        return this.campoMonto;
    }

    public String getTextoMonto() {
        return this.campoMonto.getText();
    }

    public void mostrarTotal(double total) {
        this.etiquetaTotal.setText(String.format("Total del dia: $%.2f", total));
    }

    public void limpiarMonto() {
        this.campoMonto.setText("");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
