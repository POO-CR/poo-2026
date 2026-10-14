// Archivo: VistaJuego.java
// La ventana del juego. Muestra lo que le pide el controlador y expone los
// botones para que se registre. No se modifica.
package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import modelo.Puntaje;

public class VistaJuego {

    private final JFrame ventana = new JFrame("Arena");
    private final JLabel puntos = new JLabel("Puntos: 0");
    private final JLabel tiempo = new JLabel("Tiempo: 0 s");
    private final JLabel mensaje = new JLabel(" ");
    private final JTextArea ranking = new JTextArea(7, 24);
    private final JButton botonSumar = new JButton("+100 puntos");
    private final JButton botonPausa = new JButton("Pausa");
    private final JButton botonTerminar = new JButton("Terminar partida");
    private final JButton botonRanking = new JButton("Ver ranking");

    public VistaJuego(String jugador) {
        Font grande = this.puntos.getFont().deriveFont(18f);
        JLabel nombre = new JLabel("Jugador: " + jugador);
        JPanel arriba = new JPanel(new GridLayout(1, 3, 10, 0));
        for (JLabel etiqueta : new JLabel[] {nombre, this.puntos, this.tiempo}) {
            etiqueta.setFont(grande);
            arriba.add(etiqueta);
        }
        this.ranking.setEditable(false);
        this.ranking.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        this.ranking.setBorder(BorderFactory.createTitledBorder("Ranking"));
        this.mensaje.setFont(grande);
        JPanel botones = new JPanel(new FlowLayout());
        botones.add(this.botonSumar);
        botones.add(this.botonPausa);
        botones.add(this.botonTerminar);
        botones.add(this.botonRanking);
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.add(this.mensaje, BorderLayout.NORTH);
        abajo.add(botones, BorderLayout.SOUTH);

        JPanel contenido = new JPanel(new BorderLayout(0, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contenido.add(arriba, BorderLayout.NORTH);
        contenido.add(this.ranking, BorderLayout.CENTER);
        contenido.add(abajo, BorderLayout.SOUTH);
        this.ventana.setContentPane(contenido);
        this.ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ventana.pack();
        this.ventana.setLocationRelativeTo(null);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    // Al cerrar la ventana se ejecuta la accion, y cuando termina, termina
    // el programa.
    public void alCerrar(Runnable accion) {
        this.ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evento) {
                accion.run();
                System.exit(0);
            }
        });
    }

    public JButton getBotonSumar() {
        return this.botonSumar;
    }

    public JButton getBotonPausa() {
        return this.botonPausa;
    }

    public JButton getBotonTerminar() {
        return this.botonTerminar;
    }

    public JButton getBotonRanking() {
        return this.botonRanking;
    }

    public void mostrarPuntos(int cantidad) {
        this.puntos.setText("Puntos: " + cantidad);
    }

    public void mostrarTiempo(int segundos) {
        this.tiempo.setText("Tiempo: " + segundos + " s");
    }

    public void mostrarPausa(boolean enPausa) {
        this.botonPausa.setText(enPausa ? "Seguir" : "Pausa");
    }

    public void mostrarMensaje(String texto) {
        this.mensaje.setText(texto.isEmpty() ? " " : texto);
    }

    public void mostrarRanking(List<Puntaje> mejores) {
        StringBuilder texto = new StringBuilder();
        int posicion = 1;
        for (Puntaje puntaje : mejores) {
            texto.append(String.format("%d. %-8s %6d%n", posicion, puntaje.getJugador(), puntaje.getPuntos()));
            posicion++;
        }
        this.ranking.setText(texto.toString());
    }

    // Con true deshabilita el boton del ranking y avisa que esta cargando.
    public void cargando(boolean cargando) {
        this.botonRanking.setEnabled(!cargando);
        if (cargando) {
            this.ranking.setText("Cargando...");
        }
    }

    public void mostrarError(String texto) {
        this.ranking.setText(texto);
    }
}
