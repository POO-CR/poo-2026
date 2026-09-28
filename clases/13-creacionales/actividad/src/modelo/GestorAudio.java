// Archivo: GestorAudio.java
// Reproduce los sonidos del juego. Usa javax.sound.sampled, que viene con
// Java, asi que no hace falta instalar nada.
//
// Al crearse, le pide al sistema operativo una linea de audio para cada
// sonido (una linea es un canal abierto hacia la placa de sonido) y carga en
// ella el archivo WAV correspondiente de la carpeta assets. Abrir esas lineas
// es lento y ocupa recursos del sistema. Si el programa crea tres GestorAudio,
// abre todo tres veces: ese es el problema del TODO 1.

package modelo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GestorAudio {

    private static int dispositivosAbiertos = 0;

    private final Map<String, Clip> sonidos = new HashMap<>();
    private final Map<String, Long> duraciones = new HashMap<>();

    // TODO 1: convertir esta clase en un Singleton. Constructor privado,
    // atributo estatico con la unica instancia y getInstancia().
    public GestorAudio() {
        dispositivosAbiertos++;
        this.cargar("musica");
        this.cargar("cuerno");
        this.cargar("espada");
    }

    // Abre una linea de audio y le carga assets/<nombre>.wav. Si la maquina no
    // tiene placa de sonido o el archivo no existe, ese sonido no se carga y
    // reproducir() va a devolver false: el juego sigue, en silencio.
    private void cargar(String nombre) {
        File archivo = new File("assets", nombre + ".wav");
        try (AudioInputStream flujo = AudioSystem.getAudioInputStream(archivo)) {
            long milisegundos = (long) (1000 * flujo.getFrameLength() / flujo.getFormat().getFrameRate());
            Clip clip = AudioSystem.getClip();
            clip.open(flujo);
            this.sonidos.put(nombre, clip);
            this.duraciones.put(nombre, milisegundos);
        } catch (Exception e) {
            // sin audio: el mapa queda sin ese sonido
        }
    }

    // Reproduce el sonido desde el principio y espera a que termine.
    // Devuelve false si ese sonido no se pudo cargar.
    public boolean reproducir(String sonido) {
        Clip clip = this.sonidos.get(sonido);
        if (clip == null) {
            return false;
        }
        try {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
            Thread.sleep(this.duraciones.get(sonido));
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static int getDispositivosAbiertos() {
        return dispositivosAbiertos;
    }
}
