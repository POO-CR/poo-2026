package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class Splash extends JPanel{

    private JPanel panel;
    private GridBagConstraints gbc;
    private Color colorFondo;


    public Splash(Color colorFondo){
        this.colorFondo = colorFondo;
    }

    protected void inicializarComponentes(){
        this.setLayout(new BorderLayout());
        this.setBackground(this.colorFondo);

        this.panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        this.gbc = new GridBagConstraints();
    }

    protected JLabel crearEtiqueta(String texto, Font fuente, Color color){
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(fuente);
        label.setForeground(color);
        return label;
    }

    public JPanel getPanel(){return this.panel;}
    public GridBagConstraints getGbc(){return this.gbc;}

    
}
