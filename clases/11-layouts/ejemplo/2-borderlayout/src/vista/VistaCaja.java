// Archivo: VistaCaja.java (etapa 2: BorderLayout, primer intento)
// Se saca setLayout(null) y se usa el BorderLayout que el JFrame ya trae:
// cinco regiones, un componente en cada una. El campo queda en el centro y
// se estira hasta ocupar todo lo que sobra; los botones de los costados son
// tan altos como la ventana. Y para el QR no queda region.

package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VistaCaja {

    private final JFrame ventana;
    private final JTextField campoMonto;
    private final JLabel etiquetaTotal;
    private final JButton botonEfectivo;
    private final JButton botonDebito;
    private final JButton botonQR;

    public VistaCaja() {
        this.ventana = new JFrame("Caja");
        this.ventana.setSize(480, 200);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);
        // sin setLayout(null): el JFrame trae un BorderLayout

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField();
        this.botonEfectivo = new JButton("Efectivo");
        this.botonDebito = new JButton("Debito");
        this.botonQR = new JButton("QR");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        // Sin setBounds(): el segundo parametro de add() dice la region.
        this.ventana.add(etiquetaMonto, BorderLayout.NORTH);
        this.ventana.add(this.campoMonto, BorderLayout.CENTER);
        this.ventana.add(this.botonEfectivo, BorderLayout.WEST);
        this.ventana.add(this.botonDebito, BorderLayout.EAST);
        this.ventana.add(this.etiquetaTotal, BorderLayout.SOUTH);
        // Y el QR? Seis componentes, cinco regiones. El boton existe y el
        // controlador se registra en el, pero no esta en la ventana.
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
