package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JLabel;

public class SplashTexto extends Splash{

    public SplashTexto(){
        super(new Color(18, 20, 26)); //Azul oscuro
        inicializarComponentes();
    }

    @Override
    protected final void inicializarComponentes(){
        super.inicializarComponentes();
        JLabel lblMateria = this.crearEtiqueta("Programación Orientada a Objetos",
                            new Font("serif", Font.BOLD, 48),
                            new Color(212,175,55));

        JLabel lblTrabajo = this.crearEtiqueta("Proyecto Integrador Final",
                            new Font("SanSerif", Font.PLAIN, 28),
                            Color.lightGray);
        
        getGbc().gridx = 0;
        getGbc().gridy = 0;
        getGbc().insets = new Insets(0, 0, 15, 0);
        getPanel().add(lblMateria, getGbc());

        getGbc().gridy = 1;
        getGbc().insets = new Insets(0, 0, 0, 0);
        getPanel().add(lblTrabajo, getGbc());

        this.setLayout(new BorderLayout());
        this.add(getPanel(), BorderLayout.CENTER);
    }
    
}
