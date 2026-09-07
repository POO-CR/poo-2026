// Archivo: VistaCaja.java
// La vista. Tiene un JFrame (no hereda de el), arma los componentes y sabe
// mostrar cosas en ellos. No hace cuentas y no decide que pasa con el click.

package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VistaCaja {

    private final JFrame ventana;
    private final JTextField campoMonto;
    private final JLabel etiquetaTotal;
    private final JButton botonRegistrar;

    public VistaCaja() {
        this.ventana = new JFrame("Caja");
        this.ventana.setSize(360, 200);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);
        this.ventana.setLayout(null);

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField();
        this.botonRegistrar = new JButton("Registrar");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        etiquetaMonto.setBounds(20, 20, 120, 25);
        this.campoMonto.setBounds(150, 20, 170, 25);
        this.botonRegistrar.setBounds(90, 65, 180, 30);
        this.etiquetaTotal.setBounds(20, 115, 300, 25);

        this.ventana.add(etiquetaMonto);
        this.ventana.add(this.campoMonto);
        this.ventana.add(this.botonRegistrar);
        this.ventana.add(this.etiquetaTotal);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // La fuente del evento. Se expone para que el controlador se registre.
    public JButton getBotonRegistrar() {
        return this.botonRegistrar;
    }

    public String getTextoMonto() {
        return this.campoMonto.getText();
    }

    public void mostrarTotal(double total) {
        this.etiquetaTotal.setText(String.format("Total del dia: $%.2f", total));
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
