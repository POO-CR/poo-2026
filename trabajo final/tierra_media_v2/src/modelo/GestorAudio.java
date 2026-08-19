package modelo;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GestorAudio {
	
	private MediaPlayer musicaFondo;
	
	public void reproducirMusica(String rutaArchivo, double volumen, boolean enBucle) {
        Platform.runLater(() -> {
            try {
                detenerMusica();

                File archivo = new File(rutaArchivo);
                if (!archivo.exists()) {
                    System.out.println("Audio no encontrado en: " + rutaArchivo);
                    return;
                }

                Media media = new Media(archivo.toURI().toString());
                musicaFondo = new MediaPlayer(media);
                musicaFondo.setVolume(volumen); // Rango de 0.0 (mudo) a 1.0 (máximo)

                if (enBucle) {
                    musicaFondo.setCycleCount(MediaPlayer.INDEFINITE);
                }

                musicaFondo.play();
            } catch (Exception e) {
                System.err.println("Error al reproducir audio: " + e.getMessage());
            }
        });
    }

    public void detenerMusica() {
        if (musicaFondo != null) {
            Platform.runLater(() -> {
                try {
                    musicaFondo.stop();
                    musicaFondo.dispose();
                    musicaFondo = null;
                } catch (Exception ignored) {}
            });
        }
    }

    public void pausarMusica() {
        if (musicaFondo != null) {
            Platform.runLater(() -> musicaFondo.pause());
        }
    }

    public void reanudarMusica() {
        if (musicaFondo != null) {
            Platform.runLater(() -> musicaFondo.play());
        }
    }

    public void setVolumen(double volumen) {
        if (musicaFondo != null) {
            Platform.runLater(() -> musicaFondo.setVolume(volumen));
        }
    }

}
