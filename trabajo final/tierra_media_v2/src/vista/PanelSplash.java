package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class PanelSplash extends JPanel{
	
	public PanelSplash(Color colorFondo) {
		this.setLayout(new BorderLayout());
        this.setBackground(colorFondo);
	}

	protected abstract void inicializarComponentes();
	
	protected JLabel crearEtiqueta(String texto, Font fuente, Color color) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }
	
}
