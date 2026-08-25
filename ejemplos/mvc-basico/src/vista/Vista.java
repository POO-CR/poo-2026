package vista;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vista extends JFrame {

    private JButton button;
    private JLabel label;

    public Vista() {
        JPanel mainPanel = new JPanel();
        this.button = new JButton("Accion");
        this.label = new JLabel("Inicial");

        mainPanel.add(this.button);
        mainPanel.add(this.label);

        mainPanel.setPreferredSize(new Dimension(400, 300));

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        mainPanel.requestFocusInWindow();
    }

    public JButton getButton() {
        return this.button;
    }

    public void setLabel(String labelText) {
        this.label.setText(labelText);
    }

}