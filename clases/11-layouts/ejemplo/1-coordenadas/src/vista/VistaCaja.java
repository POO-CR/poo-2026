// Archivo: VistaCaja.java (etapa 1: coordenadas a mano)
// La vista de la clase pasada con el boton de QR agregado. Como cada componente
// tiene sus coordenadas escritas con setBounds(), el tercer boton obligo a
// ensanchar la ventana y a correr los otros dos. Y sin setResizable(false),
// al agrandar la ventana todo queda en la esquina: nadie recalcula nada.

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
    private final JButton botonEfectivo;
    private final JButton botonDebito;
    private final JButton botonQR;

    public VistaCaja() {
        this.ventana = new JFrame("Caja");
        this.ventana.setSize(480, 200);                  // antes: 360
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setLayout(null);                    // nadie ubica: lo hace la vista, a mano

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField();
        this.botonEfectivo = new JButton("Efectivo");
        this.botonDebito = new JButton("Debito");
        this.botonQR = new JButton("QR");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        etiquetaMonto.setBounds(20, 20, 120, 25);
        this.campoMonto.setBounds(150, 20, 170, 25);
        this.botonEfectivo.setBounds(20, 65, 140, 30);   // antes: (40, 65, 130, 30)
        this.botonDebito.setBounds(170, 65, 140, 30);    // antes: (190, 65, 130, 30)
        this.botonQR.setBounds(320, 65, 140, 30);
        this.etiquetaTotal.setBounds(20, 115, 300, 25);

        this.ventana.add(etiquetaMonto);
        this.ventana.add(this.campoMonto);
        this.ventana.add(this.botonEfectivo);
        this.ventana.add(this.botonDebito);
        this.ventana.add(this.botonQR);
        this.ventana.add(this.etiquetaTotal);
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
