package controlador;

import modelo.GestorAudio;
import vista.Opciones;
import vista.Ventana;

public class ControladorOpciones {

    private Ventana ventana;
    private Opciones panelOpciones;

    public ControladorOpciones(){
        this.ventana = Ventana.getInstancia();
        this.panelOpciones = new Opciones(GestorAudio.getInstancia()::reproducirHover);
        this.inicializar();
        this.ventana.getContenedor().add(panelOpciones, Ventana.CARD_OPCIONES);
        this.ventana.mostrarSplash(Ventana.CARD_OPCIONES);
    }

    private void inicializar(){
        int valorMusica = (int) (GestorAudio.getInstancia().getVolumenMusica() * 100);
        this.panelOpciones.getSliderMusic().setValue(valorMusica);
        
        this.panelOpciones.getSliderMusic().addChangeListener(e ->
            GestorAudio.getInstancia().setVolumenMusica(this.panelOpciones.getSliderMusic().getValue() / 100.0)
        );

        int valorSFX = (int) (GestorAudio.getInstancia().getVolumenSFX() * 100);
        this.panelOpciones.getSliderSFX().setValue(valorSFX);
        
        this.panelOpciones.getSliderSFX().addChangeListener(e -> {
            GestorAudio.getInstancia().setVolumenSFX(this.panelOpciones.getSliderSFX().getValue() / 100.0f);
            if (!this.panelOpciones.getSliderSFX().getValueIsAdjusting()) {
                GestorAudio.getInstancia().reproducirHover();
            }
        });
        
        this.panelOpciones.getBtnVolver().addActionListener(e -> {
            this.ventana.mostrarSplash(Ventana.CARD_MENU);
        });
    }


    
}
