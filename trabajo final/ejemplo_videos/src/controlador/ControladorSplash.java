package controlador;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.SwingUtilities;
import vista.Ventana;

public class ControladorSplash {

    private final Ventana ventana;
    private final int tiempoMateriaMs = 3000;
    private final int tiempoUniMs = 3000;
    private final AtomicBoolean videoTerminado;

    public ControladorSplash(){
        this.ventana = Ventana.getInstancia();
        this.videoTerminado = new AtomicBoolean(false);
        this.iniciarSecuencia();
    }

    public final void iniciarSecuencia(){
        ventana.mostrarSplash(Ventana.CARD_SPLASH_MATERIA);
        ventana.setVisible(true);

        new Thread(()->{
            try {
                Thread.sleep(tiempoMateriaMs);
                SwingUtilities.invokeLater(() -> {
                    ventana.mostrarSplash(Ventana.CARD_SPLASH_UNI);
                });

                Thread.sleep(tiempoUniMs);
                SwingUtilities.invokeLater(this::iniciarVideoIntro);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private void iniciarVideoIntro() {
        ventana.mostrarSplash(Ventana.CARD_VIDEO);
        
        ventana.getPanelVideo().cargarYReproducir(
            "src/assets/intro_lotr.mp4",
            Ventana.ANCHO_PANTALLA,
            Ventana.ALTO_PANTALLA,
            this::finalizarVideoYMostrarMenu,
            this::finalizarVideoYMostrarMenu
        );
    }
	
	private void finalizarVideoYMostrarMenu() {
        if (videoTerminado.compareAndSet(false, true)) {
            ventana.getPanelVideo().detenerYLiberar();

            SwingUtilities.invokeLater(() -> {
                new ControladorMenu();
            });
        }
    }
    
    
}
