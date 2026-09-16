// Archivo: VistaCaja.java
// La vista de la caja. Implementa ObservadorCaja: cuando la Caja cambia, la
// llama con cajaCambio() y la vista lee el total. Nadie mas tiene que
// acordarse de actualizarla.

package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Caja;
import modelo.ObservadorCaja;

public class VistaCaja implements ObservadorCaja {

    private final JFrame ventana;
    private final JTextField campoMonto;
    private final JLabel etiquetaTotal;
    private final JButton botonEfectivo;
    private final JButton botonDebito;
    private final JButton botonQR;

    public VistaCaja() {
        this.ventana = new JFrame("Caja");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLayout(new BorderLayout(10, 10));

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField(12);
        this.botonEfectivo = new JButton("Efectivo");
        this.botonDebito = new JButton("Debito");
        this.botonQR = new JButton("QR");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        JPanel panelMonto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonto.add(etiquetaMonto);
        panelMonto.add(this.campoMonto);

        JPanel panelBotones = new JPanel(new GridLayout(1, 0, 8, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelBotones.add(this.botonEfectivo);
        panelBotones.add(this.botonDebito);
        panelBotones.add(this.botonQR);

        this.etiquetaTotal.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        this.ventana.add(panelMonto, BorderLayout.NORTH);
        this.ventana.add(panelBotones, BorderLayout.CENTER);
        this.ventana.add(this.etiquetaTotal, BorderLayout.SOUTH);

        this.ventana.pack();
        this.ventana.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // Lo que la Caja llama despues de cada cambio.
    @Override
    public void cajaCambio(Caja caja) {
        this.mostrarTotal(caja.total());
    }

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
