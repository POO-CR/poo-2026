// Archivo: ControladorJuego.java
// Registra los botones de la vista y le pide cosas a la partida. Todo lo que
// hace corre en el hilo de eventos, salvo el reloj.
package controlador;

import modelo.Partida;
import vista.VistaJuego;

public class ControladorJuego {

    private final VistaJuego vista;
    private final Partida partida;
    private final Thread reloj;
    private int segundos;
    private boolean enPausa;

    public ControladorJuego(VistaJuego vista, Partida partida) {
        this.vista = vista;
        this.partida = partida;
        this.vista.getBotonSumar().addActionListener(evento -> this.sumar());
        this.vista.getBotonPausa().addActionListener(evento -> this.pausarOSeguir());
        this.vista.getBotonTerminar().addActionListener(evento -> this.terminar());
        this.vista.getBotonRanking().addActionListener(evento -> this.verRanking());
        // TODO 2: el reloj es un hilo con un while y un sleep.
        this.reloj = new Thread(() -> this.contarSegundos(), "Reloj");
        this.reloj.start();
    }

    private void sumar() {
        this.partida.sumarPuntos(100);
        this.vista.mostrarPuntos(this.partida.getPuntos());
    }

    private void contarSegundos() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            this.segundos++;
            this.vista.mostrarTiempo(this.segundos);
        }
    }

    // TODO 2: un hilo terminado no se puede volver a arrancar.
    private void pausarOSeguir() {
        if (this.enPausa) {
            this.reloj.start();
        } else {
            this.reloj.interrupt();
        }
        this.enPausa = !this.enPausa;
        this.vista.mostrarPausa(this.enPausa);
    }

    private void terminar() {
        int puntos = this.partida.getPuntos();
        this.partida.terminar();                     // TODO 4: guardar tarda
        this.vista.mostrarPuntos(0);
        this.vista.mostrarMensaje("Partida terminada: " + puntos + " puntos");
        try {
            Thread.sleep(2000);                      // TODO 3
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.vista.mostrarMensaje("");
    }

    // TODO 1: buscar el ranking tarda, y la ventana se congela.
    private void verRanking() {
        this.vista.mostrarRanking(this.partida.ranking());
    }
}
