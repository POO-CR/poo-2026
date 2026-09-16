package controlador;

import modelo.GestorAudio;
import vista.MenuPrincipal;
import vista.Ventana;

public class ControladorMenu {

    private Ventana ventana;
    private MenuPrincipal menuPrincipal;

    public ControladorMenu(){
            this.ventana = Ventana.getInstancia();
            GestorAudio.getInstancia().reproducirMusica("src/assets/menu principal.mp3", 0.6, true);
            this.menuPrincipal = new MenuPrincipal(GestorAudio.getInstancia()::reproducirHover);
            this.menuPrincipal.getBtnOpciones().addActionListener((e) -> {new ControladorOpciones();});
            this.menuPrincipal.getBtnNuevoJuego().addActionListener((e)->{new ControladorJuego();});
            this.ventana.getContenedor().add(this.menuPrincipal,Ventana.CARD_MENU);
            this.ventana.mostrarSplash(Ventana.CARD_MENU);
    }
    
}
