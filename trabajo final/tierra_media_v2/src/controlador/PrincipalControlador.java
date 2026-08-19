package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.SwingUtilities;

import modelo.GestorAudio;
import vista.VentanaApp;

public class PrincipalControlador implements ActionListener{
	
	
	private VentanaApp ventana;
	private final GestorAudio gestorAudio;
	private final int tiempoMateriaMs;
    private final int tiempoUniMs;
    private final AtomicBoolean videoTerminado;
	
	public PrincipalControlador() {
		this.ventana = new VentanaApp();
		this.gestorAudio = new GestorAudio();
		this.tiempoMateriaMs = 3000;
		this.tiempoUniMs = 3000;
		this.videoTerminado = new AtomicBoolean(false);
        this.iniciarSecuencia();
	}
	
	public void iniciarSecuencia() {
        ventana.mostrarTarjeta(VentanaApp.CARD_SPLASH_MATERIA);
        ventana.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(tiempoMateriaMs);
                SwingUtilities.invokeLater(() -> {
                    ventana.mostrarTarjeta(VentanaApp.CARD_SPLASH_UNI);
                });
                
                Thread.sleep(tiempoUniMs);
                SwingUtilities.invokeLater(this::iniciarVideoIntro);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
	
	private void iniciarVideoIntro() {
        ventana.mostrarTarjeta(VentanaApp.CARD_VIDEO);
        
        ventana.getPanelVideo().cargarYReproducir(
            "src/assets/intro_lotr.mp4",
            VentanaApp.ANCHO_PANTALLA,
            VentanaApp.ALTO_PANTALLA,
            this::finalizarVideoYMostrarMenu, 
            this::finalizarVideoYMostrarMenu  
        );
    }
	
	private void finalizarVideoYMostrarMenu() {
        if (videoTerminado.compareAndSet(false, true)) {
            ventana.getPanelVideo().detenerYLiberar();

            SwingUtilities.invokeLater(() -> {
                ventana.mostrarTarjeta(VentanaApp.CARD_MENU);
                gestorAudio.reproducirMusica("src/assets/menu principal.mp3", 0.6, true);
            });
        }
    }
	
	public void cerrarJuego() {
        gestorAudio.detenerMusica();
        System.exit(0);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

        switch (comando) {
            case "NUEVO_JUEGO":
                // Lógica para inicializar el modelo y pasar al tablero
                break;
            case "OPCIONES":
                // ventana.mostrarTarjeta(VentanaApp.CARD_OPCIONES);
                break;
            case "SALIR":
                System.exit(0);
                break;
        }
		
	}

}
