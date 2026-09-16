// Archivo: VistaConversor.java (solucion)
// La ventana reparte en tres regiones con BorderLayout. Arriba, un panel con
// la etiqueta y el campo (FlowLayout). En el centro, un panel con los botones
// (GridLayout de una fila). Abajo, el resultado. Ningun componente tiene
// coordenadas: el tamano sale de pack(), y al agrandar la ventana los layouts
// recalculan. El boton de libras (TODO 5) fue crearlo, un add() y su getter.
// Los metodos que usa el controlador no cambiaron.

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

public class VistaConversor {

    private final JFrame ventana;
    private final JTextField campoPesos;
    private final JLabel etiquetaResultado;
    private final JButton botonDolares;
    private final JButton botonEuros;
    private final JButton botonReales;
    private final JButton botonLibras;   // TODO 5

    public VistaConversor() {
        this.ventana = new JFrame("Conversor de moneda");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLayout(new BorderLayout(10, 10));   // TODO 1

        JLabel etiquetaPesos = new JLabel("Monto en pesos:");
        this.campoPesos = new JTextField(12);   // TODO 1: 12 columnas, el campo pide su ancho
        this.botonDolares = new JButton("A dolares");
        this.botonEuros = new JButton("A euros");
        this.botonReales = new JButton("A reales");
        this.botonLibras = new JButton("A libras");   // TODO 5
        this.etiquetaResultado = new JLabel("Resultado: ...");

        // TODO 1: arriba, la etiqueta y el campo, uno al lado del otro.
        JPanel panelMonto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMonto.add(etiquetaPesos);
        panelMonto.add(this.campoPesos);

        // TODO 3: en el centro, los botones en una fila de celdas iguales.
        // TODO 4: con new FlowLayout() cada boton queda con su tamano preferido,
        // centrado arriba, y no crece con la ventana.
        JPanel panelBotones = new JPanel(new GridLayout(1, 0, 8, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelBotones.add(this.botonDolares);
        panelBotones.add(this.botonEuros);
        panelBotones.add(this.botonReales);
        panelBotones.add(this.botonLibras);   // TODO 5: sin correr a nadie

        // TODO 2: abajo, el resultado.
        this.etiquetaResultado.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        this.ventana.add(panelMonto, BorderLayout.NORTH);
        this.ventana.add(panelBotones, BorderLayout.CENTER);
        this.ventana.add(this.etiquetaResultado, BorderLayout.SOUTH);

        this.ventana.pack();                         // TODO 3: el tamano sale de los componentes
        this.ventana.setLocationRelativeTo(null);    // despues de pack(), para centrar bien
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

    // TODO 5
    public JButton getBotonLibras() {
        return this.botonLibras;
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
