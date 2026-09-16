package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import modelo.Dificultad;

public class PanelNuevoJuego extends JPanel {

    private Image imagenFondo;
    private final JComboBox<Integer> comboJugadores;
    private final JComboBox<Dificultad> comboDificultad;
    private final JLabel lblDetalleDificultad;
    private final BotonMenuLOTR btnContinuar;
    private final BotonMenuLOTR btnVolver;

    public PanelNuevoJuego(Runnable alHacerHover, Runnable alContinuar, Runnable alVolver) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        File archivoFondo = new File("src/assets/fondo.png");
        if (archivoFondo.exists()) {
            imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
        }

        add(Box.createVerticalStrut(100));

        // Título Principal
        JLabel lblTitulo = new JLabel("NUEVA PARTIDA");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 52));
        lblTitulo.setForeground(new Color(212, 175, 55));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);

        add(Box.createVerticalStrut(40));

        // Selector de Participantes (1 a 5)
        add(crearEtiquetaSeccion("CANTIDAD DE PARTICIPANTES"));
        add(Box.createVerticalStrut(10));
        Integer[] opcionesJugadores = {1, 2, 3, 4, 5};
        comboJugadores = new JComboBox<>(opcionesJugadores);
        estilizarComboBox(comboJugadores);
        comboJugadores.setSelectedItem(2);
        add(comboJugadores);

        add(Box.createVerticalStrut(30));

        // Selector de Nivel de Dificultad
        add(crearEtiquetaSeccion("NIVEL DE DIFICULTAD"));
        add(Box.createVerticalStrut(10));
        comboDificultad = new JComboBox<>(Dificultad.values());
        estilizarComboBox(comboDificultad);
        comboDificultad.setSelectedItem(Dificultad.ESTANDAR);
        add(comboDificultad);

        add(Box.createVerticalStrut(15));

        // Texto informativo del nivel de dificultad
        lblDetalleDificultad = new JLabel();
        lblDetalleDificultad.setFont(new Font("Serif", Font.ITALIC, 18));
        lblDetalleDificultad.setForeground(new Color(210, 205, 180));
        lblDetalleDificultad.setAlignmentX(Component.CENTER_ALIGNMENT);
        actualizarTextoDificultad();
        add(lblDetalleDificultad);

        comboDificultad.addActionListener(e -> actualizarTextoDificultad());

        add(Box.createVerticalStrut(50));

        // Botones de Navegación
        btnContinuar = new BotonMenuLOTR("SELECCIONAR HÉROES", alHacerHover);
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.addActionListener(e -> {
            if (alContinuar != null) alContinuar.run();
        });
        add(btnContinuar);

        add(Box.createVerticalStrut(20));

        btnVolver = new BotonMenuLOTR("VOLVER AL MENÚ", alHacerHover);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> {
            if (alVolver != null) alVolver.run();
        });
        add(btnVolver);
    }

    private void actualizarTextoDificultad() {
        Dificultad d = (Dificultad) comboDificultad.getSelectedItem();
        if (d != null) {
            lblDetalleDificultad.setText(String.format("Cartas 'El Cielo se Oscurece': %d  |  Objetivos requeridos: %d",
                    d.getCartasCieloOscurece(), d.getCantidadObjetivos()));
        }
    }

    private JLabel crearEtiquetaSeccion(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Serif", Font.BOLD, 20));
        lbl.setForeground(new Color(230, 220, 190));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    private <T> void estilizarComboBox(JComboBox<T> combo) {
        combo.setMaximumSize(new Dimension(340, 42));
        combo.setPreferredSize(new Dimension(340, 42));
        combo.setFont(new Font("Serif", Font.BOLD, 18));
        combo.setBackground(new Color(30, 32, 40));
        combo.setForeground(new Color(255, 235, 130));
        combo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ((JLabel) combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    // Getters para que el controlador extraiga los datos elegidos
    public int getParticipantesSeleccionados() {
        return (Integer) comboJugadores.getSelectedItem();
    }

    public Dificultad getDificultadSeleccionada() {
        return (Dificultad) comboDificultad.getSelectedItem();
    }

    public BotonMenuLOTR getBtnContinuar() { return btnContinuar; }
    public BotonMenuLOTR getBtnVolver() { return btnVolver; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            g.setColor(new Color(10, 12, 18, 170));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            g.setColor(new Color(18, 20, 28));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}