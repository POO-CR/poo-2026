// Archivo: VistaConversor.java (solucion)
// La vista. Arma la ventana y sabe mostrar cosas en ella. No hace cuentas y
// no decide que pasa con ningun evento: expone las fuentes (los botones y el
// campo) para que el controlador se registre.

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
    private final JButton botonEuros;
    private final JButton botonReales;   // TODO 6

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
        this.botonReales = new JButton("A reales");
        this.etiquetaResultado = new JLabel("Resultado: ...");

        etiquetaPesos.setBounds(20, 20, 120, 25);
        this.campoPesos.setBounds(150, 20, 170, 25);
        this.botonDolares.setBounds(20, 65, 100, 30);     // TODO 6: los tres corridos
        this.botonEuros.setBounds(125, 65, 100, 30);      // TODO 6
        this.botonReales.setBounds(230, 65, 100, 30);     // TODO 6
        this.etiquetaResultado.setBounds(20, 115, 300, 25);

        this.ventana.add(etiquetaPesos);
        this.ventana.add(this.campoPesos);
        this.ventana.add(this.botonDolares);
        this.ventana.add(this.botonEuros);
        this.ventana.add(this.botonReales);               // TODO 6
        this.ventana.add(this.etiquetaResultado);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // Las fuentes de eventos. El controlador las necesita para registrarse.
    public JButton getBotonDolares() {
        return this.botonDolares;
    }

    public JButton getBotonEuros() {
        return this.botonEuros;
    }

    // TODO 6
    public JButton getBotonReales() {
        return this.botonReales;
    }

    // TODO 4: el campo tambien es fuente de eventos (teclas y clicks).
    public JTextField getCampoPesos() {
        return this.campoPesos;
    }

    public String getTextoPesos() {
        return this.campoPesos.getText();
    }

    public void mostrarResultado(String texto) {
        this.etiquetaResultado.setText("Resultado: " + texto);
    }

    // TODO 5
    public void limpiar() {
        this.campoPesos.setText("");
        this.etiquetaResultado.setText("Resultado: ...");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this.ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
