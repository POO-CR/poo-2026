package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.ConfiguracionPartida;
import modelo.GestorAudio;
import modelo.Personaje;
import modelo.PersonajeDAO;
import vista.PanelSeleccionPersonajes;
import vista.TarjetaPersonaje;
import vista.Ventana;

public class ControladorSeleccionPersonaje {
    private final Ventana ventana;
    private final ConfiguracionPartida configuracion;
    private final PanelSeleccionPersonajes vistaSeleccion;
    private final GestorAudio gestorAudio;
    private final PersonajeDAO personajeDAO;

    private final List<Personaje> personajesSeleccionados = new ArrayList<>();
    private final int personajesRequeridos;

    public static final String CARD_SELECCION_HEROES = "SELECCION_HEROES";

    public ControladorSeleccionPersonaje(ConfiguracionPartida configuracion) {
        this.ventana = Ventana.getInstancia();
        this.configuracion = configuracion;
        this.gestorAudio = GestorAudio.getInstancia();
        this.personajeDAO = new PersonajeDAO();

        this.personajesRequeridos = (configuracion.getCantidadParticipantes() == 1)
                ? 4
                : configuracion.getCantidadParticipantes() * 2;

        this.vistaSeleccion = new PanelSeleccionPersonajes(
            gestorAudio::reproducirHover,
            this::comenzarPartida,
            () -> ventana.mostrarSplash("NUEVO_JUEGO")
        );

        cargarPersonajesDesdeBD();
        actualizarEstado();

        this.ventana.getContenedor().add(vistaSeleccion, CARD_SELECCION_HEROES);
        this.ventana.mostrarSplash(CARD_SELECCION_HEROES);
    }

    private void cargarPersonajesDesdeBD() {
        List<Personaje> listaBD = personajeDAO.obtenerTodos();

        for (Personaje p : listaBD) {
            TarjetaPersonaje tarjeta = new TarjetaPersonaje(
                p,
                gestorAudio::reproducirHover,
                () -> toggleSeleccionPersonaje(p)
            );
            vistaSeleccion.agregarTarjeta(tarjeta);
        }
    }

    private void toggleSeleccionPersonaje(Personaje p) {
        if (personajesSeleccionados.contains(p)) {
            personajesSeleccionados.remove(p);
        } else {
            if (personajesSeleccionados.size() < personajesRequeridos) {
                personajesSeleccionados.add(p);
            }
        }

        // Sincronizar estado visual de cada tarjeta
        for (TarjetaPersonaje tarjeta : vistaSeleccion.getTarjetas()) {
            tarjeta.setSeleccionada(personajesSeleccionados.contains(tarjeta.getPersonaje()));
        }

        actualizarEstado();
    }

    private void actualizarEstado() {
        int actuales = personajesSeleccionados.size();
        boolean completo = (actuales == personajesRequeridos);

        String mensaje = String.format("Héroes elegidos: %d / %d (Requiere 2 por participante)", 
                actuales, personajesRequeridos);

        vistaSeleccion.actualizarMensajeEstado(mensaje, completo);
    }

    private void comenzarPartida() {
        System.out.println("Partida lista con los héroes:");
        for (Personaje p : personajesSeleccionados) {
            System.out.println(" - " + p.getNombre() + " en " + p.getUbicacionActual());
        }

        new ControladorTablero(configuracion, personajesSeleccionados);
    }
}
