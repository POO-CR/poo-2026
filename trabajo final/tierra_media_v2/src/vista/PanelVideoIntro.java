package vista;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelVideoIntro extends JPanel{
	
	private final JFXPanel jfxPanel;
    private MediaPlayer mediaPlayer;
    
    public PanelVideoIntro(Runnable alFinalizarVideo) {
        
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        this.jfxPanel = new JFXPanel();
        this.jfxPanel.setFocusable(true);
        add(jfxPanel, BorderLayout.CENTER);
    }

    public void cargarYReproducir(String rutaArchivo, int ancho, int alto, Runnable alSolicitarSalto, Runnable alFinalizar) {
        Platform.runLater(() -> {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                if (alFinalizar != null) alFinalizar.run();
                return;
            }

            Media media = new Media(archivo.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setFitWidth(ancho);
            mediaView.setFitHeight(alto);
            mediaView.setPreserveRatio(true);

            Group root = new Group(mediaView);
            Scene scene = new Scene(root, ancho, alto, javafx.scene.paint.Color.BLACK);

            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER || 
                    event.getCode() == KeyCode.SPACE || 
                    event.getCode() == KeyCode.ESCAPE) {
                    if (alSolicitarSalto != null) alSolicitarSalto.run();
                }
            });

            scene.setOnMouseClicked(event -> {
                if (alSolicitarSalto != null) alSolicitarSalto.run();
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                if (alFinalizar != null) alFinalizar.run();
            });

            jfxPanel.setScene(scene);
            mediaPlayer.play();

            SwingUtilities.invokeLater(() -> jfxPanel.requestFocusInWindow());
        });
    }

    public void detenerYLiberar() {
        if (mediaPlayer != null) {
            Platform.runLater(() -> {
                try {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                } catch (Exception ignored) {}
            });
        }
    }

}
