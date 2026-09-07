// Archivo: VentanaConversor.java
// Primera version del conversor. Una sola clase es la ventana, atiende el
// click y hace la cuenta.
//
// TODO 1: crear la carpeta src/modelo y escribir ahi Conversor.java, el modelo,
//         con "package modelo;" en la primera linea y la clase public. Recibe
//         por constructor los pesos por dolar (mayor que cero, si no
//         IllegalArgumentException) y tiene double aDolares(double pesos), que
//         rechaza los montos negativos con IllegalArgumentException. No importa
//         nada de javax.swing.
// TODO 2: crear la carpeta src/vista y escribir ahi VistaConversor.java, la
//         vista, con "package vista;". Tiene un JFrame como atributo (no hereda
//         de el), arma los mismos componentes que estan aca y ofrece mostrar(),
//         getBotonConvertir(), getTextoPesos(), mostrarResultado(String texto)
//         y mostrarError(String mensaje). No hace ninguna cuenta.
// TODO 3: crear la carpeta src/controlador y escribir ahi
//         ControladorConversor.java, con "package controlador;", que implementa
//         ActionListener e importa modelo.Conversor y vista.VistaConversor.
//         Recibe la vista y el modelo por constructor (los dos obligatorios),
//         se registra en el boton de la vista, y en actionPerformed() lee el
//         texto, lo convierte a double, le pide el resultado al modelo y se lo
//         entrega a la vista. Un texto que no es un numero y un monto negativo
//         terminan los dos en vista.mostrarError().
// TODO 4: en Programa.java, descomentar los tres import de arriba, comentar el
//         bloque de la parte 1 y descomentar el de la parte 2. Esta clase queda
//         sin uso y se puede borrar.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaConversor extends JFrame implements ActionListener {

    private static final double PESOS_POR_DOLAR = 1450.0;

    private final JTextField campoPesos;
    private final JLabel etiquetaResultado;
    private final JButton botonConvertir;

    public VentanaConversor() {
        super("Conversor de moneda");
        this.setSize(360, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // Sin administrador de disposicion: cada componente se ubica con setBounds().
        this.setLayout(null);

        JLabel etiquetaPesos = new JLabel("Monto en pesos:");
        this.campoPesos = new JTextField();
        this.botonConvertir = new JButton("Convertir a dolares");
        this.etiquetaResultado = new JLabel("Resultado: ...");

        // setBounds(x, y, ancho, alto), en pixeles desde la esquina superior izquierda.
        etiquetaPesos.setBounds(20, 20, 120, 25);
        this.campoPesos.setBounds(150, 20, 170, 25);
        this.botonConvertir.setBounds(90, 65, 180, 30);
        this.etiquetaResultado.setBounds(20, 115, 300, 25);

        this.add(etiquetaPesos);
        this.add(this.campoPesos);
        this.add(this.botonConvertir);
        this.add(this.etiquetaResultado);

        // La ventana se registra a si misma como receptora del click.
        this.botonConvertir.addActionListener(this);
    }

    // Lo llama Swing cada vez que se presiona el boton.
    @Override
    public void actionPerformed(ActionEvent evento) {
        String texto = this.campoPesos.getText();
        try {
            double pesos = Double.parseDouble(texto);
            double dolares = pesos / PESOS_POR_DOLAR;
            this.etiquetaResultado.setText(String.format("Resultado: %.2f USD", dolares));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un numero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
