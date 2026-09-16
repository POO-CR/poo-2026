// Archivo: VistaConversor.java (solucion)
// La vista. Arma la ventana y sabe mostrar cosas en ella. No hace cuentas y
// no decide que pasa cuando se presiona un boton: expone el boton para que
// el controlador se registre.

package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VistaConversor {

    private final JFrame ventana;
    private final JTextField campoPesos;
    private final JLabel etiquetaResultado;
    private final JButton botonDolares;
    private final JButton botonEuros;   // TODO 6

    public VistaConversor() {
        this.ventana = new JFrame("Conversor de moneda");
        this.ventana.setSize(360, 200);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);
        this.ventana.setLayout(null);

        JLabel etiquetaPesos = new JLabel("Monto en pesos:");
        this.campoPesos = new JTextField();
        this.botonDolares = new JButton("A dolares");
        this.botonEuros = new JButton("A euros");
        this.etiquetaResultado = new JLabel("Resultado: ...");

        etiquetaPesos.setBounds(20, 20, 120, 25);
        this.campoPesos.setBounds(150, 20, 170, 25);
        this.botonDolares.setBounds(40, 65, 130, 30);     // TODO 6: antes ocupaba el centro
        this.botonEuros.setBounds(190, 65, 130, 30);      // TODO 6
        this.etiquetaResultado.setBounds(20, 115, 300, 25);

        this.ventana.add(etiquetaPesos);
        this.ventana.add(this.campoPesos);
        this.ventana.add(this.botonDolares);
        this.ventana.add(this.botonEuros);                // TODO 6
        this.ventana.add(this.etiquetaResultado);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // La fuente del evento. El controlador la necesita para registrarse.
    public JButton getBotonDolares() {
        return this.botonDolares;
    }

    // TODO 6
    public JButton getBotonEuros() {
        return this.botonEuros;
    }

    public String getTextoPesos() {
        return this.campoPesos.getText();
    }

    public void mostrarResultado(String texto) {
        this.etiquetaResultado.setText("Resultado: " + texto);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
