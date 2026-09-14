// Archivo: VistaConversor.java
// La vista del conversor tal como quedo al final de la clase pasada, con tres
// botones. Cada componente tiene sus coordenadas escritas a mano con
// setBounds(), y la ventana ya no es de tamano fijo: se puede agrandar.
//
// TODO 1: la ventana usa BorderLayout; la etiqueta y el campo van en un panel, al norte.
// TODO 2: el resultado va al sur.
// TODO 3: los tres botones van en un panel con GridLayout de una fila, en el centro; se borran los setBounds() y el setSize(), y la ventana se arma con pack().
// TODO 4: el panel de botones con FlowLayout en vez de GridLayout; se deja el que quede mejor.
// TODO 5: el boton "A libras", sin coordenadas.
// Los pasos estan en el enunciado.

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
    private final JButton botonReales;

    public VistaConversor() {
        this.ventana = new JFrame("Conversor de moneda");
        this.ventana.setSize(360, 200);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setLayout(null);

        JLabel etiquetaPesos = new JLabel("Monto en pesos:");
        this.campoPesos = new JTextField();
        this.botonDolares = new JButton("A dolares");
        this.botonEuros = new JButton("A euros");
        this.botonReales = new JButton("A reales");
        this.etiquetaResultado = new JLabel("Resultado: ...");

        etiquetaPesos.setBounds(20, 20, 120, 25);
        this.campoPesos.setBounds(150, 20, 170, 25);
        this.botonDolares.setBounds(20, 65, 100, 30);
        this.botonEuros.setBounds(125, 65, 100, 30);
        this.botonReales.setBounds(230, 65, 100, 30);
        this.etiquetaResultado.setBounds(20, 115, 300, 25);

        this.ventana.add(etiquetaPesos);
        this.ventana.add(this.campoPesos);
        this.ventana.add(this.botonDolares);
        this.ventana.add(this.botonEuros);
        this.ventana.add(this.botonReales);
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
