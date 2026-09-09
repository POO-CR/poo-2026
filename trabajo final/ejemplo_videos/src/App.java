import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//https://www.oracle.com/java/technologies/downloads/javafx/#javafx21-windows

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba Video JavaFX");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setLocationRelativeTo(null);

            JFXPanel jfxPanel = new JFXPanel();
            frame.add(jfxPanel, BorderLayout.CENTER);
            frame.setVisible(true);

            Platform.runLater(() -> {
                try {
                    File archivo = new File("assets/intro_lotr.mp4");
                    
                    if (!archivo.exists()) {
                        System.err.println("No se encontró el archivo en: " + archivo.getAbsolutePath());
                        return;
                    }

                    Media media = new Media(archivo.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(mediaPlayer);

                    mediaView.setFitWidth(1280);
                    mediaView.setFitHeight(720);
                    mediaView.setPreserveRatio(true);

                    Group root = new Group(mediaView);
                    Scene scene = new Scene(root, 1280, 720, Color.BLACK);

                    jfxPanel.setScene(scene);

                    
                    mediaPlayer.play();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
