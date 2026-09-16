package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {
    private final int id;
    private final String nombre;
    private final String region;
    private final boolean esRefugio;
    private boolean esFortaleza;
    
    // Tropas y entidades
    private int tropasSombra;
    private int tropasAliadas;
    private String tipoTropaAliada;
    private int cantidadNazgul;
    
    // Coordenadas en el mapa visual
    private int posX;
    private int posY;

    // Personajes y adyacencias
    private final List<Personaje> personajesPresentes = new ArrayList<>();
    private final List<Ruta> rutasAdyacentes = new ArrayList<>();

    public Ubicacion(int id, String nombre, String region, boolean esRefugio, boolean esFortaleza, 
                    int tropasSombra, int tropasAliadas, String tipoTropaAliada, int posX, int posY) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.esRefugio = esRefugio;
        this.esFortaleza = esFortaleza;
        this.tropasSombra = tropasSombra;
        this.tropasAliadas = tropasAliadas;
        this.tipoTropaAliada = tipoTropaAliada;
        this.posX = posX;
        this.posY = posY;
    }

    public void agregarRuta(Ubicacion destino, TipoRuta tipo, modelo.Simbolo coste) {
        this.rutasAdyacentes.add(new Ruta(destino, tipo, coste));
    }

    public void agregarPersonaje(Personaje p) {
        if (!personajesPresentes.contains(p)) {
            personajesPresentes.add(p);
            p.setUbicacionActual(this.nombre);
        }
    }

    public void removerPersonaje(Personaje p) {
        personajesPresentes.remove(p);
    }

    // Getters y modificadores
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRegion() { return region; }
    public boolean isEsRefugio() { return esRefugio; }
    public boolean isEsFortaleza() { return esFortaleza; }
    public int getTropasSombra() { return tropasSombra; }
    public void setTropasSombra(int t) { this.tropasSombra = Math.max(0, t); }
    public int getTropasAliadas() { return tropasAliadas; }
    public void setTropasAliadas(int t) { this.tropasAliadas = Math.max(0, t); }
    public String getTipoTropaAliada() { return tipoTropaAliada; }
    public int getCantidadNazgul() { return cantidadNazgul; }
    public void setCantidadNazgul(int n) { this.cantidadNazgul = Math.max(0, n); }
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public List<Personaje> getPersonajesPresentes() { return personajesPresentes; }
    public List<Ruta> getRutasAdyacentes() { return rutasAdyacentes; }
}