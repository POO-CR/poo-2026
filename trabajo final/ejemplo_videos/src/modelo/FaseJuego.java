package modelo;

public enum FaseJuego {
    FASE_1_PREPARAR("Fase 1: Preparación"),
    FASE_2_ACCIONES("Fase 2: Acciones de Héroe"),
    FASE_3_SOMBRA("Fase 3: La Sombra se Alza"),
    FASE_4_FIN_RONDA("Fase 4: Fin de Ronda");

    private final String nombre;
    FaseJuego(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
}
