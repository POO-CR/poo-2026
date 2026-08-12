import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tablero extends JFrame{
    private final int FILAS = 8;
    private final int COLUMNAS = 8;
    private final int TAMANIO_CELDA = 60;

    public Tablero() {
        setTitle("Tierra Media - Prototipo Visual Táctico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        List<Personaje> personajes = new ArrayList<>();
        personajes.add(new Personaje("Aragorn", 1, 1, Color.BLUE, 100, 25, true));
        personajes.add(new Personaje("Legolas", 1, 2, Color.GREEN, 80, 20, true));
        personajes.add(new Personaje("Orco", 6, 5, Color.RED, 50, 15, false));

        PanelTablero panelTablero = new PanelTablero(FILAS, COLUMNAS, TAMANIO_CELDA, personajes);
        add(panelTablero, BorderLayout.CENTER);

        // controles de juego
        JPanel panelControl = new JPanel();
        JButton btnTerminarTurno = new JButton("Terminar Turno");

        btnTerminarTurno.addActionListener(e -> {
            panelTablero.ejecutarTurnoEnemigo();
        });

        panelControl.add(btnTerminarTurno);
        add(panelControl, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

}
