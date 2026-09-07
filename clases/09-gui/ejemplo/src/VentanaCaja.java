// Archivo: VentanaCaja.java
// LA VERSION QUE NO CONVIENE. Una sola clase es la ventana, atiende el boton
// y lleva la cuenta. Funciona. El problema se ve cuando hay que cambiarla.
// Se ejecuta con ProgramaTodoJunto.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaCaja extends JFrame implements ActionListener {

    private final JTextField campoMonto;
    private final JLabel etiquetaTotal;
    private final JButton botonRegistrar;
    private double total;                     // el negocio, adentro de una ventana

    public VentanaCaja() {
        super("Caja");
        this.setSize(360, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        JLabel etiquetaMonto = new JLabel("Monto:");
        this.campoMonto = new JTextField();
        this.botonRegistrar = new JButton("Registrar");
        this.etiquetaTotal = new JLabel("Total del dia: $0.00");

        etiquetaMonto.setBounds(20, 20, 120, 25);
        this.campoMonto.setBounds(150, 20, 170, 25);
        this.botonRegistrar.setBounds(90, 65, 180, 30);
        this.etiquetaTotal.setBounds(20, 115, 300, 25);

        this.add(etiquetaMonto);
        this.add(this.campoMonto);
        this.add(this.botonRegistrar);
        this.add(this.etiquetaTotal);

        // La ventana se registra a si misma como receptora del click.
        this.botonRegistrar.addActionListener(this);
    }

    // Lo llama Swing con cada click. Lee, suma y muestra, todo aca.
    @Override
    public void actionPerformed(ActionEvent evento) {
        double monto = Double.parseDouble(this.campoMonto.getText());
        this.total += monto;
        this.etiquetaTotal.setText(String.format("Total del dia: $%.2f", this.total));
    }
}
