// Archivo: VistaConversor.java (solucion)
// La vista principal. Implementa ObservadorConversor: cuando el Conversor
// cambia, lee la ultima conversion y la muestra.

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

import modelo.Conversion;
import modelo.Conversor;
import modelo.ObservadorConversor;

public class VistaConversor implements ObservadorConversor {

    private final JFrame ventana;
    private final JTextField campoPesos;
    private final JLabel etiquetaResultado;
    private final JButton botonDolares;
    private final JButton botonEuros;
    private final JButton botonReales;

    public VistaConversor() {
        this.ventana = new JFrame("Conversor de moneda");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLayout(new BorderLayout(10, 10));

        JLabel etiquetaPesos = new JLabel("Monto en pesos:");
        this.campoPesos = new JTextField(12);
        this.botonDolares = new JButton("A dolares");
        this.botonEuros = new JButton("A euros");
        this.botonReales = new JButton("A reales");
        this.etiquetaResultado = new JLabel("Resultado: ...");

        JPanel panelMonto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonto.add(etiquetaPesos);
        panelMonto.add(this.campoPesos);

        JPanel panelBotones = new JPanel(new GridLayout(1, 0, 8, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelBotones.add(this.botonDolares);
        panelBotones.add(this.botonEuros);
        panelBotones.add(this.botonReales);

        this.etiquetaResultado.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        this.ventana.add(panelMonto, BorderLayout.NORTH);
        this.ventana.add(panelBotones, BorderLayout.CENTER);
        this.ventana.add(this.etiquetaResultado, BorderLayout.SOUTH);

        this.ventana.pack();
        this.ventana.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // TODO 2: lo que el Conversor llama despues de cada conversion.
    @Override
    public void conversorCambio(Conversor conversor) {
        Conversion ultima = conversor.getUltima();
        this.mostrarResultado(String.format("%.2f %s", ultima.getResultado(), ultima.getMoneda()));
    }

    public JButton getBotonDolares() {
        return this.botonDolares;
    }

    public JButton getBotonEuros() {
        return this.botonEuros;
    }

    public JButton getBotonReales() {
        return this.botonReales;
    }

    public JTextField getCampoPesos() {
        return this.campoPesos;
    }

    public String getTextoPesos() {
        return this.campoPesos.getText();
    }

    public void mostrarResultado(String texto) {
        this.etiquetaResultado.setText("Resultado: " + texto);
    }

    public void limpiar() {
        this.campoPesos.setText("");
        this.etiquetaResultado.setText("Resultado: ...");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
