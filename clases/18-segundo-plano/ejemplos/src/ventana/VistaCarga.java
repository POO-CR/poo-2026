// Archivo: VistaCarga.java
// La ventana: un reloj grande, una barra de avance, un mensaje y dos
// botones. Solo arma y muestra; no decide nada.
package ventana;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class VistaCarga {

    private final JFrame ventana = new JFrame("Carga de nivel");
    private final JLabel reloj = new JLabel("0,0 s", JLabel.CENTER);
    private final JLabel mensaje = new JLabel(" ", JLabel.CENTER);
    private final JProgressBar barra = new JProgressBar(0, 100);
    private final JButton botonEnEventos = new JButton("Cargar en el hilo de eventos");
    private final JButton botonConWorker = new JButton("Cargar con SwingWorker");

    public VistaCarga() {
        this.reloj.setFont(this.reloj.getFont().deriveFont(Font.BOLD, 40f));
        this.mensaje.setFont(this.mensaje.getFont().deriveFont(18f));
        this.barra.setStringPainted(true);

        JPanel botones = new JPanel(new GridLayout(1, 2, 10, 0));
        botones.add(this.botonEnEventos);
        botones.add(this.botonConWorker);
        JPanel abajo = new JPanel(new GridLayout(3, 1, 0, 8));
        abajo.add(this.barra);
        abajo.add(this.mensaje);
        abajo.add(botones);
        JPanel contenido = new JPanel(new BorderLayout(0, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contenido.add(this.reloj, BorderLayout.CENTER);
        contenido.add(abajo, BorderLayout.SOUTH);

        this.ventana.setContentPane(contenido);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setSize(560, 300);
        this.ventana.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    public JButton getBotonEnEventos() {
        return this.botonEnEventos;
    }

    public JButton getBotonConWorker() {
        return this.botonConWorker;
    }

    public void mostrarTiempo(int decimas) {
        this.reloj.setText(decimas / 10 + "," + decimas % 10 + " s");
    }

    public void mostrarAvance(int porcentaje) {
        this.barra.setValue(porcentaje);
    }

    public void mostrarMensaje(String texto) {
        this.mensaje.setText(texto.isEmpty() ? " " : texto);
    }

    public void cargando(boolean cargando) {
        this.botonEnEventos.setEnabled(!cargando);
        this.botonConWorker.setEnabled(!cargando);
    }
}
