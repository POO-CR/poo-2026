// Archivo: ControladorCarga.java
// El reloj es un javax.swing.Timer. Cargar el nivel se puede hacer de dos
// formas: en el hilo de eventos, que congela la ventana (el reloj se para),
// o con un SwingWorker. El mensaje final se borra solo con otro Timer.
package ventana;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class ControladorCarga {

    private final VistaCarga vista;
    private final CargaDeNivel carga;
    private final Timer borrarMensaje;
    private int decimas;

    public ControladorCarga(VistaCarga vista, CargaDeNivel carga) {
        this.vista = vista;
        this.carga = carga;
        this.vista.getBotonEnEventos().addActionListener(evento -> this.cargarEnEventos());
        this.vista.getBotonConWorker().addActionListener(evento -> this.cargarConWorker());

        Timer reloj = new Timer(100, evento -> this.contarDecima());
        reloj.start();
        this.borrarMensaje = new Timer(2000, evento -> this.vista.mostrarMensaje(""));
        this.borrarMensaje.setRepeats(false);
    }

    private void contarDecima() {
        this.decimas++;
        this.vista.mostrarTiempo(this.decimas);
    }

    // Todo en el hilo de eventos: mientras dura, la ventana no se redibuja.
    private void cargarEnEventos() {
        this.vista.mostrarMensaje("Cargando...");             // no se llega a ver
        for (int etapa = 0; etapa < this.carga.getEtapas(); etapa++) {
            this.carga.cargar(etapa);
            this.vista.mostrarAvance((etapa + 1) * 10);      // tampoco
        }
        this.terminar("Listo, en el hilo de eventos");
    }

    // La carga en otro hilo; el avance y el final, en el hilo de eventos.
    private void cargarConWorker() {
        this.vista.cargando(true);
        SwingWorker<String, String> worker = new SwingWorker<>() {

            @Override
            protected String doInBackground() {               // en otro hilo
                for (int etapa = 0; etapa < carga.getEtapas(); etapa++) {
                    String cargado = carga.cargar(etapa);
                    setProgress((etapa + 1) * 10);
                    publish("Cargando " + cargado + "...");
                }
                return "Listo, con SwingWorker";
            }

            @Override
            protected void process(List<String> mensajes) {   // en el hilo de eventos
                vista.mostrarMensaje(mensajes.get(mensajes.size() - 1));
            }

            @Override
            protected void done() {                           // en el hilo de eventos
                try {
                    terminar(get());
                } catch (InterruptedException | ExecutionException ex) {
                    terminar("No se pudo cargar");
                }
            }
        };
        worker.addPropertyChangeListener(evento -> {
            if (evento.getPropertyName().equals("progress")) {
                this.vista.mostrarAvance((Integer) evento.getNewValue());
            }
        });
        worker.execute();
    }

    private void terminar(String mensaje) {
        this.vista.mostrarMensaje(mensaje);
        this.vista.cargando(false);
        this.borrarMensaje.restart();
    }
}
