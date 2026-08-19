package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class BotonMenuLOTR extends JButton{
	
	private boolean hover;
    private static final Color COLOR_FONDO_NORMAL = new Color(20, 22, 28, 200); // Oscuro semitransparente
    private static final Color COLOR_FONDO_HOVER = new Color(50, 45, 30, 230);   // Tono dorado tenue al pasar el mouse
    private static final Color COLOR_BORDE = new Color(212, 175, 55);            // Dorado
    private static final Color COLOR_TEXTO_NORMAL = new Color(230, 220, 190);
    private static final Color COLOR_TEXTO_HOVER = new Color(255, 235, 130);
    
    public BotonMenuLOTR(String texto) {
    	super(texto);
        this.hover = false;

        setFont(new Font("Serif", Font.BOLD, 22));
        setForeground(COLOR_TEXTO_NORMAL);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(380, 60));
        setMaximumSize(new Dimension(380, 60));
        
        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                setForeground(COLOR_TEXTO_HOVER);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                setForeground(COLOR_TEXTO_NORMAL);
                repaint();
            }
        });
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        // 1. Fondo del botón
        g2.setColor(hover ? COLOR_FONDO_HOVER : COLOR_FONDO_NORMAL);
        g2.fillRoundRect(2, 2, ancho - 4, alto - 4, 12, 12);

        // 2. Borde exterior dorado
        g2.setColor(COLOR_BORDE);
        g2.setStroke(new BasicStroke(hover ? 2.5f : 1.5f));
        g2.drawRoundRect(2, 2, ancho - 4, alto - 4, 12, 12);

        // 3. Detalle ornamental interior (marco fino)
        if (hover) {
            g2.setColor(new Color(255, 235, 130, 120));
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawRoundRect(6, 6, ancho - 12, alto - 12, 8, 8);
        }

        g2.dispose();
        super.paintComponent(g);
    }

}
