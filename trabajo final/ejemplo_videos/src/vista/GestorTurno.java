package vista;

import java.util.List;
import modelo.ConfiguracionPartida;
import modelo.FaseJuego;
import modelo.Personaje;
import modelo.Tablero;

public class GestorTurno {

    private final ConfiguracionPartida configuracion;
    private final Tablero tablero;
    private final List<Personaje> personajesEnJuego;

    private int numeroRonda = 1;
    private FaseJuego faseActual = FaseJuego.FASE_1_PREPARAR;
    private int indiceHeroeActivo = 0;
    private int accionesRestantesHeroe = 2; // Por regla general, cada héroe dispone de 2 acciones

    public GestorTurno(ConfiguracionPartida configuracion, Tablero tablero, List<Personaje> personajesEnJuego) {
        this.configuracion = configuracion;
        this.tablero = tablero;
        this.personajesEnJuego = personajesEnJuego;
    }

    public Personaje getHeroeActivo() {
        if (personajesEnJuego.isEmpty()) return null;
        return personajesEnJuego.get(indiceHeroeActivo);
    }

    public void consumirAccion() {
        if (accionesRestantesHeroe > 0) {
            accionesRestantesHeroe--;
        }
    }

    public boolean heroeActivoTieneAcciones() {
        return accionesRestantesHeroe > 0;
    }

    public void pasarAlSiguienteHeroe() {
        indiceHeroeActivo++;
        accionesRestantesHeroe = 2;

        // Si ya actuaron todos los héroes, pasamos a la Fase de la Sombra
        if (indiceHeroeActivo >= personajesEnJuego.size()) {
            indiceHeroeActivo = 0;
            faseActual = FaseJuego.FASE_3_SOMBRA;
        }
    }

    public void avanzarFase() {
        switch (faseActual) {
            case FASE_1_PREPARAR -> faseActual = FaseJuego.FASE_2_ACCIONES;
            case FASE_2_ACCIONES -> faseActual = FaseJuego.FASE_3_SOMBRA;
            case FASE_3_SOMBRA   -> faseActual = FaseJuego.FASE_4_FIN_RONDA;
            case FASE_4_FIN_RONDA -> {
                numeroRonda++;
                faseActual = FaseJuego.FASE_1_PREPARAR;
                indiceHeroeActivo = 0;
                accionesRestantesHeroe = 2;
            }
        }
    }

    // Getters
    public FaseJuego getFaseActual() { return faseActual; }
    public int getNumeroRonda() { return numeroRonda; }
    public int getAccionesRestantesHeroe() { return accionesRestantesHeroe; }
}
