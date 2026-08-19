package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenuPrincipal extends JPanel{
	private Image imagenFondo;

    public PanelMenuPrincipal(Runnable alNuevoJuego, Runnable alAbrirOpciones) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        File archivoFondo = new File("src/assets/fondo.png");
        if (archivoFondo.exists()) {
            imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
        }

        add(Box.createVerticalStrut(160));

        // Título Principal
        JLabel lblTitulo = new JLabel("TIERRA MEDIA");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 64));
        lblTitulo.setForeground(new Color(212, 175, 55));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);

        // Subtítulo
        JLabel lblSubtitulo = new JLabel("— BATALLA TÁCTICA —");
        lblSubtitulo.setFont(new Font("Serif", Font.PLAIN, 24));
        lblSubtitulo.setForeground(new Color(220, 220, 220));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblSubtitulo);

        add(Box.createVerticalStrut(90));

        // Botones con estilo medieval personalizado
        BotonMenuLOTR btnNuevoJuego = new BotonMenuLOTR("NUEVO JUEGO");
        BotonMenuLOTR btnOpciones = new BotonMenuLOTR("OPCIONES");
        BotonMenuLOTR btnSalir = new BotonMenuLOTR("SALIR");

        // Listeners
        btnNuevoJuego.addActionListener(e -> alNuevoJuego.run());
        btnOpciones.addActionListener(e -> alAbrirOpciones.run());
        btnSalir.addActionListener(e -> System.exit(0));

        // Alineación y espaciado
        btnNuevoJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOpciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(btnNuevoJuego);
        add(Box.createVerticalStrut(25));
        add(btnOpciones);
        add(Box.createVerticalStrut(25));
        add(btnSalir);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenFondo != null) {
            // Dibuja la imagen escalándola al tamaño actual de la ventana
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);

            // Capa semitransparente oscura superpuesta para que los textos resalten siempre
            g.setColor(new Color(10, 12, 18, 140));
            g.fillRect(0, 0, getWidth(), getHeight());
        } else {
            // Fondo degradado alternativo si no existe la imagen
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(18, 20, 28));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
