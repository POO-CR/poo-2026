package controlador;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import modelo.ConfiguracionPartida;
import modelo.FaseJuego;
import modelo.Personaje;
import modelo.Tablero;
import modelo.TableroDAO;
import modelo.Ubicacion;
import vista.GestorTurno;
import vista.PanelAccionesHUD;
import vista.PanelTablero;
import vista.Ventana;

public class ControladorTablero {

    private final Ventana ventana;
    private final ConfiguracionPartida configuracion;
    private final List<Personaje> personajesElegidos;

    private final Tablero tablero;
    private final GestorTurno gestorTurno;

    private final PanelTablero panelTablero;
    private final PanelAccionesHUD panelHUD;
    private final JPanel panelContenedorJuego;

    private boolean modoViajeActivo = false;

    public static final String CARD_TABLERO = "TABLERO_JUEGO";

    public ControladorTablero(ConfiguracionPartida configuracion, List<Personaje> personajesElegidos) {
        this.ventana = Ventana.getInstancia();
        this.configuracion = configuracion;
        this.personajesElegidos = personajesElegidos;

        // 1. Cargar el grafo y nodos desde SQLite
        TableroDAO dao = new TableroDAO();
        this.tablero = dao.cargarTableroCompleto();

        // 2. Colocar a cada héroe en su casilla inicial
        for (Personaje p : personajesElegidos) {
            tablero.colocarPersonaje(p, p.getUbicacionActual());
        }

        // 3. Instanciar el GestorTurno con las reglas de la partida
        this.gestorTurno = new GestorTurno(configuracion, tablero, personajesElegidos);

        // 4. Instanciar las vistas conectando sus callbacks
        this.panelTablero = new PanelTablero(this::alSeleccionarCasilla);
        this.panelTablero.setTablero(this.tablero);

        this.panelHUD = new PanelAccionesHUD(
                this::activarModoViaje,
                this::ejecutarReclutar,
                this::ejecutarAtacar,
                this::pasarTurnoHeroe
        );

        // 5. Ensamblar en un contenedor con BorderLayout
        this.panelContenedorJuego = new JPanel(new BorderLayout());
        this.panelContenedorJuego.add(panelTablero, BorderLayout.CENTER);
        this.panelContenedorJuego.add(panelHUD, BorderLayout.EAST);

        // 6. Pasar al inicio de la fase de acciones y actualizar HUD
        gestorTurno.avanzarFase(); // De FASE_1_PREPARAR a FASE_2_ACCIONES
        panelHUD.actualizarHUD(gestorTurno);

        // 7. Desplegar en la ventana principal
        this.ventana.getContenedor().add(panelContenedorJuego, CARD_TABLERO);
        this.ventana.mostrarSplash(CARD_TABLERO);
    }

    // --- Control de Acciones ---

    private void activarModoViaje() {
        if (!gestorTurno.heroeActivoTieneAcciones()) {
            System.out.println("El héroe activo ya no dispone de acciones este turno.");
            return;
        }
        modoViajeActivo = true;
        Personaje activo = gestorTurno.getHeroeActivo();
        System.out.printf("Modo Viaje Activado: Elige una casilla adyacente a %s.%n", activo.getUbicacionActual());
    }

    private void alSeleccionarCasilla(Ubicacion destino) {
        panelTablero.setUbicacionSeleccionada(destino);

        if (modoViajeActivo) {
            ejecutarViaje(destino);
        }
    }

    private void ejecutarViaje(Ubicacion destino) {
        Personaje heroe = gestorTurno.getHeroeActivo();
        Ubicacion origen = tablero.getUbicacionPorNombre(heroe.getUbicacionActual());

        if (origen == null || destino == null) {
            modoViajeActivo = false;
            return;
        }

        // Comprobación en el grafo de adyacencias
        boolean conectado = origen.getRutasAdyacentes().stream()
                .anyMatch(ruta -> ruta.getDestino().getId() == destino.getId());

        if (conectado) {
            origen.removerPersonaje(heroe);
            destino.agregarPersonaje(heroe);
            gestorTurno.consumirAccion();

            System.out.printf("%s ha viajado a %s. Acciones restantes: %d%n",
                    heroe.getNombre(), destino.getNombre(), gestorTurno.getAccionesRestantesHeroe());

            modoViajeActivo = false;
            panelTablero.repaint();
            panelHUD.actualizarHUD(gestorTurno);
        } else {
            System.err.printf("Ruta no válida: %s no está directamente conectada con %s.%n",
                    origen.getNombre(), destino.getNombre());
        }
    }

    private void ejecutarReclutar() {
        if (!gestorTurno.heroeActivoTieneAcciones()) return;

        Personaje heroe = gestorTurno.getHeroeActivo();
        Ubicacion u = tablero.getUbicacionPorNombre(heroe.getUbicacionActual());

        if (u != null) {
            int tropasASumar = heroe.getCantidadReclutamiento(); // Gandalf suma 2, el resto 1
            u.setTropasAliadas(u.getTropasAliadas() + tropasASumar);
            gestorTurno.consumirAccion();

            System.out.printf("%s ha reclutado %d tropas en %s.%n", heroe.getNombre(), tropasASumar, u.getNombre());
            panelTablero.repaint();
            panelHUD.actualizarHUD(gestorTurno);
        }
    }

    private void ejecutarAtacar() {
        if (!gestorTurno.heroeActivoTieneAcciones()) return;

        Personaje heroe = gestorTurno.getHeroeActivo();
        Ubicacion u = tablero.getUbicacionPorNombre(heroe.getUbicacionActual());

        if (u != null && u.getTropasSombra() > 0) {
            // Ataque básico de prueba: elimina 1 tropa enemiga
            u.setTropasSombra(u.getTropasSombra() - 1);
            gestorTurno.consumirAccion();

            System.out.printf("%s ataca en %s. Tropas enemigas restantes: %d%n",
                    heroe.getNombre(), u.getNombre(), u.getTropasSombra());

            panelTablero.repaint();
            panelHUD.actualizarHUD(gestorTurno);
        } else {
            System.out.println("No hay tropas de la Sombra presentes en esta casilla para atacar.");
        }
    }

    private void pasarTurnoHeroe() {
        gestorTurno.pasarAlSiguienteHeroe();
        modoViajeActivo = false;

        if (gestorTurno.getFaseActual() == FaseJuego.FASE_3_SOMBRA) {
            System.out.println("--- Todos los héroes han actuado. Comienza la Fase 3: La Sombra se Alza ---");
            // Aquí se disparará el avance de las tropas de la Sombra
        }

        panelHUD.actualizarHUD(gestorTurno);
        panelTablero.repaint();
    }
}