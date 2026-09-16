package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class BotonMenuLOTR extends JButton {

    private boolean activo;
    private final Runnable alHacerHover;

    private static final Color COLOR_FONDO_NORMAL = new Color(20, 22, 28, 200);
    private static final Color COLOR_FONDO_HOVER  = new Color(50, 45, 30, 230);
    private static final Color COLOR_BORDE        = new Color(212, 175, 55);
    private static final Color COLOR_TEXTO_NORMAL = new Color(230, 220, 190);
    private static final Color COLOR_TEXTO_HOVER  = new Color(255, 235, 130);

    public BotonMenuLOTR(String texto, Runnable alHacerHover) {
        super(texto);
        this.alHacerHover = alHacerHover;
        this.activo = false;

        setFont(new Font("Serif", Font.BOLD, 22));
        setForeground(COLOR_TEXTO_NORMAL);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setFocusable(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(380, 60));
        setMaximumSize(new Dimension(380, 60));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                activarBoton();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                desactivarBoton();
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activarBoton();
            }

            @Override
            public void focusLost(FocusEvent e) {
                desactivarBoton();
            }
        });
    }

    private void activarBoton() {
        if (!activo) {
            this.activo = true;
            setForeground(COLOR_TEXTO_HOVER);
            repaint();
            if (alHacerHover != null) {
                alHacerHover.run();
            } else {
                System.out.println("ADVERTENCIA: alHacerHover es NULL en el botón " + getText());
            }
        }
    }

    private void desactivarBoton() {
        if (activo) {
            this.activo = false;
            setForeground(COLOR_TEXTO_NORMAL);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        g2.setColor(activo ? COLOR_FONDO_HOVER : COLOR_FONDO_NORMAL);
        g2.fillRoundRect(2, 2, ancho - 4, alto - 4, 12, 12);

        g2.setColor(COLOR_BORDE);
        g2.setStroke(new BasicStroke(activo ? 2.5f : 1.5f));
        g2.drawRoundRect(2, 2, ancho - 4, alto - 4, 12, 12);

        if (activo) {
            g2.setColor(new Color(255, 235, 130, 130));
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawRoundRect(6, 6, ancho - 12, alto - 12, 8, 8);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}