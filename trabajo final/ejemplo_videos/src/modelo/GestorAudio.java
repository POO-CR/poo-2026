package modelo;

import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GestorAudio {

    private static GestorAudio instancia;
    
    private MediaPlayer musicaFondo;
    private AudioClip sonidoHover;

    private double volumenMusica = 0.5;
    private float volumenSFX = 0.8f;

    private GestorAudio() {
        new JFXPanel();
        precargarSFXHover("src/assets/sonido_hover.mp3");
    }

    public static synchronized GestorAudio getInstancia() {
        if (instancia == null) {
            instancia = new GestorAudio();
        }
        return instancia;
    }

    private void precargarSFXHover(String ruta) {
        Platform.runLater(() -> {
            try {
                File archivo = new File(ruta);
                if (!archivo.exists()) {
                    archivo = new File("assets/sonido_hover.mp3");
                }
                if (archivo.exists()) {
                    sonidoHover = new AudioClip(archivo.toURI().toString());
                    sonidoHover.setVolume(volumenSFX);
                    System.out.println("AudioClip MP3 cargado exitosamente.");
                } else {
                    System.err.println("No se encontró el archivo SFX: " + archivo.getAbsolutePath());
                }
            } catch (Exception e) {
                System.err.println("Error al cargar AudioClip: " + e.getMessage());
            }
        });
    }

    public void reproducirHover() {
        if (sonidoHover == null || volumenSFX <= 0.01f) return;
        Platform.runLater(() -> sonidoHover.play());
    }

    public void reproducirMusica(String rutaArchivo, double volumen, boolean enBucle) {
        Platform.runLater(() -> {
            try {
                detenerMusica();

                File archivo = new File(rutaArchivo);
                if (!archivo.exists()) {
                    archivo = new File("assets/" + new File(rutaArchivo).getName());
                }

                if (!archivo.exists()) {
                    System.out.println("Audio no encontrado en: " + rutaArchivo);
                    return;
                }

                Media media = new Media(archivo.toURI().toString());
                musicaFondo = new MediaPlayer(media);
                musicaFondo.setVolume(volumen);

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

    public void setVolumenMusica(double vol) {
        this.volumenMusica = Math.max(0.0, Math.min(1.0, vol));
        if (musicaFondo != null) {
            Platform.runLater(() -> musicaFondo.setVolume(this.volumenMusica));
        }
    }

    public void setVolumenSFX(float vol) {
        this.volumenSFX = Math.max(0.0f, Math.min(1.0f, vol));
        if (sonidoHover != null) {
            Platform.runLater(() -> sonidoHover.setVolume(this.volumenSFX));
        }
    }

    public double getVolumenMusica() { return volumenMusica; }
    public float getVolumenSFX() { return volumenSFX; }
}