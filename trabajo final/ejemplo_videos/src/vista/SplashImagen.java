package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SplashImagen extends Splash{

    private final String url;

    public SplashImagen(String url){
        super(new Color(22, 24, 32));
        this.url = url;
        this.inicializarComponentes();
    }

    @Override
    protected final void inicializarComponentes() {
        super.inicializarComponentes();
        JLabel lblLogo = new JLabel();
        if(url != null && !url.isEmpty()){
            File logo = new File(this.url);
            if (logo.exists()) {
                ImageIcon iconOriginal = new ImageIcon(logo.getAbsolutePath());
                Image imagenEscalada = iconOriginal.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagenEscalada));
            }
        }

        JLabel lblUniversidad = this.crearEtiqueta("Universidad Nacional de la Patagonia San Juan Bosco",
                            new Font("serif", Font.BOLD, 36),
                            Color.WHITE);

        getGbc().gridx = 0;
        getGbc().gridy = 0;
        getGbc().insets = new Insets(0, 0, 20, 0);
        getPanel().add(lblLogo, getGbc());

        getGbc().gridx = 0;
        getGbc().gridy = 1;
        getGbc().insets = new Insets(0, 0, 15, 0);
        getPanel().add(lblUniversidad, getGbc());

        this.setLayout(new BorderLayout());
        this.add(getPanel(), BorderLayout.CENTER);
    }
    
}
