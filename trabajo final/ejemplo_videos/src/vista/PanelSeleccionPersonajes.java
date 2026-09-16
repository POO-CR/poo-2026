package vista;

import modelo.Personaje;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PanelSeleccionPersonajes extends JPanel {

    private Image imagenFondo;
    private final JPanel gridTarjetas;
    private final JLabel lblEstadoSeleccion;
    private final BotonMenuLOTR btnIniciarPartida;
    private final BotonMenuLOTR btnVolver;
    private final List<TarjetaPersonaje> tarjetas = new ArrayList<>();

    public PanelSeleccionPersonajes(Runnable alHacerHover, Runnable alIniciarPartida, Runnable alVolver) {
        setLayout(new BorderLayout());

        File archivoFondo = new File("src/assets/fondo.png");
        if (archivoFondo.exists()) {
            imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
        }

        // --- Panel Superior (Títulos) ---
        JPanel panelNorte = new JPanel();
        panelNorte.setOpaque(false);
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));

        panelNorte.add(Box.createVerticalStrut(30));
        JLabel lblTitulo = new JLabel("ELIGE A TUS HÉROES");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 46));
        lblTitulo.setForeground(new Color(212, 175, 55));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelNorte.add(lblTitulo);

        lblEstadoSeleccion = new JLabel("Selecciona tus héroes para comenzar");
        lblEstadoSeleccion.setFont(new Font("Serif", Font.ITALIC, 18));
        lblEstadoSeleccion.setForeground(new Color(220, 220, 200));
        lblEstadoSeleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelNorte.add(lblEstadoSeleccion);
        panelNorte.add(Box.createVerticalStrut(20));

        add(panelNorte, BorderLayout.NORTH);

        // --- Panel Central (Grilla de Tarjetas con Scroll) ---
        gridTarjetas = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 25));
        gridTarjetas.setOpaque(false);

        JScrollPane scroll = new JScrollPane(gridTarjetas);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);

        // --- Panel Inferior (Botones) ---
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 25));
        panelSur.setOpaque(false);

        btnVolver = new BotonMenuLOTR("VOLVER", alHacerHover);
        btnVolver.addActionListener(e -> { if (alVolver != null) alVolver.run(); });

        btnIniciarPartida = new BotonMenuLOTR("COMENZAR VIAJE", alHacerHover);
        btnIniciarPartida.setEnabled(false);
        btnIniciarPartida.addActionListener(e -> { if (alIniciarPartida != null) alIniciarPartida.run(); });

        panelSur.add(btnVolver);
        panelSur.add(btnIniciarPartida);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void agregarTarjeta(TarjetaPersonaje tarjeta) {
        tarjetas.add(tarjeta);
        gridTarjetas.add(tarjeta);
        gridTarjetas.revalidate();
        gridTarjetas.repaint();
    }

    public void actualizarMensajeEstado(String texto, boolean listoParaComenzar) {
        lblEstadoSeleccion.setText(texto);
        btnIniciarPartida.setEnabled(listoParaComenzar);
    }

    public List<TarjetaPersonaje> getTarjetas() { return tarjetas; }
    public BotonMenuLOTR getBtnIniciarPartida() { return btnIniciarPartida; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            g.setColor(new Color(10, 12, 18, 180));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            g.setColor(new Color(18, 20, 28));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}