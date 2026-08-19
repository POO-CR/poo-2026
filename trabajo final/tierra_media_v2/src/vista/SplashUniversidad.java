package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashUniversidad extends PanelSplash{
	
	private Image logo;

    public SplashUniversidad() {
        super(new Color(22, 24, 32));
        inicializarComponentes();
    }
    
    @Override
    protected void inicializarComponentes() {
        File file = new File("src/assets/universidad.png");
        if (file.exists()) {
            logo = new ImageIcon(file.getAbsolutePath()).getImage();
        }

        JLabel lblUni = crearEtiqueta(
            "UNIVERSIDAD NACIONAL DE LA PATAGONIA",
            new Font("Serif", Font.BOLD, 36),
            Color.WHITE
        );

        JLabel lblSede = crearEtiqueta(
            "SAN JUAN BOSCO",
            new Font("Serif", Font.BOLD, 42),
            new Color(110, 180, 255)
        );

        JPanel panelTextos = new JPanel(new BorderLayout(0, 15));
        panelTextos.setOpaque(false);
        panelTextos.add(lblUni, BorderLayout.NORTH);
        panelTextos.add(lblSede, BorderLayout.CENTER);

        add(panelTextos, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cx = getWidth() / 2;
        int cy = (getHeight() / 2) - 80;

        if (logo != null) {
            int tam = 250;
            g.drawImage(logo, cx - (tam / 2), cy - (tam / 2), tam, tam, this);
        } else {
            int r = 100;
            g.setColor(new Color(40, 55, 75));
            g.fillOval(cx - r, cy - r, r * 2, r * 2);
            g.setColor(new Color(110, 180, 255));
            g.drawOval(cx - r, cy - r, r * 2, r * 2);

            g.setFont(new Font("Serif", Font.BOLD, 32));
            g.setColor(Color.WHITE);
            g.drawString("UNPSJB", cx - 65, cy + 12);
        }
    }

}
