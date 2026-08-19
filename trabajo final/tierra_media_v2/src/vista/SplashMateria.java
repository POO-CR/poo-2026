package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplashMateria extends PanelSplash{

	public SplashMateria() {
        super(new Color(18, 20, 26));
        inicializarComponentes();
    }
	
	@Override
    protected void inicializarComponentes() {
        JLabel lblMateria = crearEtiqueta(
            "PROGRAMACIÓN ORIENTADA A OBJETOS",
            new Font("Serif", Font.BOLD, 48),
            new Color(212, 175, 55)
        );

        JLabel lblTrabajo = crearEtiqueta(
            "Proyecto Integrador Final",
            new Font("SansSerif", Font.PLAIN, 28),
            Color.LIGHT_GRAY
        );

        JPanel panelCentro = new JPanel(new BorderLayout(0, 20));
        panelCentro.setOpaque(false);
        panelCentro.add(lblMateria, BorderLayout.CENTER);
        panelCentro.add(lblTrabajo, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);
    }
}
