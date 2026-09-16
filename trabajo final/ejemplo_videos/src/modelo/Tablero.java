package modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {

    private final Map<Integer, Ubicacion> mapaUbicaciones = new HashMap<>();
    private int nivelEsperanza = 15; // Medidor de Esperanza inicial
    private int nivelAmenaza = 1;    // Medidor de Amenaza inicial
    private String regionOjoSauron = "Eriador"; // Empieza en Eriador según manual

    public void agregarUbicacion(Ubicacion u) {
        mapaUbicaciones.put(u.getId(), u);
    }

    public Ubicacion getUbicacionPorId(int id) {
        return mapaUbicaciones.get(id);
    }

    public Ubicacion getUbicacionPorNombre(String nombre) {
        return mapaUbicaciones.values().stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre.trim()))
                .findFirst()
                .orElse(null);
    }

    public void colocarPersonaje(Personaje p, String nombreUbicacion) {
        Ubicacion u = getUbicacionPorNombre(nombreUbicacion);
        if (u != null) {
            u.agregarPersonaje(p);
        }
    }

    // Modificadores de medidores
    public void modificarEsperanza(int delta) {
        this.nivelEsperanza = Math.max(0, Math.min(15, this.nivelEsperanza + delta));
    }

    public boolean esFinDelJuego() {
        return this.nivelEsperanza <= 0;
    }

    public int getNivelEsperanza() { return nivelEsperanza; }
    public int getNivelAmenaza() { return nivelAmenaza; }
    public String getRegionOjoSauron() { return regionOjoSauron; }
    public void setRegionOjoSauron(String region) { this.regionOjoSauron = region; }
    public Map<Integer, Ubicacion> getMapaUbicaciones() { return mapaUbicaciones; }
}
