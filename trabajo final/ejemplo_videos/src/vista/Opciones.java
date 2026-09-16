package vista;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class Opciones extends JPanel {
    private Image imagenFondo;
    private final JSlider sliderMusica;
    private final JSlider sliderSFX;
    private BotonMenuLOTR btnVolver;

    public Opciones(Runnable alHacerHover) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        File archivoFondo = new File("src/assets/fondo.png");
        if (archivoFondo.exists()) {
            imagenFondo = new ImageIcon(archivoFondo.getAbsolutePath()).getImage();
        }

        add(Box.createVerticalStrut(140));

        // Título de la sección
        JLabel lblTitulo = new JLabel("CONFIGURACIÓN");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 52));
        lblTitulo.setForeground(new Color(212, 175, 55));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblTitulo);

        add(Box.createVerticalStrut(60));

        // Control de Música
        add(crearEtiquetaSeccion("VOLUMEN DE MÚSICA"));
        add(Box.createVerticalStrut(10));
        
        sliderMusica = crearSliderMedieval();
        add(sliderMusica);

        add(Box.createVerticalStrut(35));

        // Control de Efectos SFX
        add(crearEtiquetaSeccion("VOLUMEN DE EFECTOS (SFX)"));
        add(Box.createVerticalStrut(10));
        sliderSFX = crearSliderMedieval();
        add(sliderSFX);

        add(Box.createVerticalStrut(70));

        btnVolver = new BotonMenuLOTR("VOLVER AL MENÚ",  alHacerHover);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(btnVolver);

    }

    public JSlider getSliderSFX(){
        return this.sliderSFX;
    }

    public JSlider getSliderMusic(){
        return this.sliderMusica;
    }

    public BotonMenuLOTR getBtnVolver(){
        return this.btnVolver;
    }


    private JLabel crearEtiquetaSeccion(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Serif", Font.BOLD, 20));
        lbl.setForeground(new Color(230, 220, 190));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }

    private JSlider crearSliderMedieval() {
        JSlider slider = new JSlider(0, 100, 0);
        slider.setMaximumSize(new Dimension(420, 45));
        slider.setPreferredSize(new Dimension(420, 45));
        slider.setOpaque(false);
        slider.setForeground(new Color(212, 175, 55));
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(25);
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        return slider;
    }

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
