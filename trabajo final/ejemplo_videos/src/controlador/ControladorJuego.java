package controlador;

import modelo.ConfiguracionPartida;
import modelo.GestorAudio;
import vista.PanelNuevoJuego;
import vista.Ventana;

public class ControladorJuego {
    
    private final Ventana ventana;
    private final PanelNuevoJuego panelNuevoJuego;
    private final GestorAudio gestorAudio;

    public ControladorJuego() {
        this.ventana = Ventana.getInstancia();
        this.gestorAudio = GestorAudio.getInstancia();
        this.panelNuevoJuego = new PanelNuevoJuego(
            gestorAudio::reproducirHover,
            this::avanzarASeleccionHeroes,
            () -> ventana.mostrarSplash(Ventana.CARD_MENU)
        );

        this.ventana.getContenedor().add(panelNuevoJuego, "NUEVO_JUEGO");
        this.ventana.mostrarSplash("NUEVO_JUEGO");
    }

    private void avanzarASeleccionHeroes() {
        ConfiguracionPartida config = new ConfiguracionPartida(
            panelNuevoJuego.getParticipantesSeleccionados(),
            panelNuevoJuego.getDificultadSeleccionada()
        );

        System.out.printf("Partida configurada -> Jugadores: %d | Dificultad: %s | Eventos: %d | Mano inicial: %d%n",
            config.getCantidadParticipantes(), config.getDificultad().getNombre(),
            config.getCartasEvento(), config.getManoInicial());

        new ControladorSeleccionPersonaje(config);
    }

}
