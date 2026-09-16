package modelo;

public class ConfiguracionPartida {
    private Integer cantidadParticipantes; // 1 a 5
    private Dificultad dificultad;
    private Integer cartasEvento;
    private Integer manoInicial;

    public ConfiguracionPartida(Integer cantidadParticipantes, Dificultad dificultad) {
        this.cantidadParticipantes = cantidadParticipantes;
        this.dificultad = dificultad;
        calcularParametrosIniciales();
    }

    private void calcularParametrosIniciales() {
        switch (cantidadParticipantes) {
            case 5 -> { cartasEvento = 9; manoInicial = 2; }
            case 4 -> { cartasEvento = 7; manoInicial = 2; }
            case 3 -> { cartasEvento = 6; manoInicial = 3; }
            case 2 -> { cartasEvento = 6; manoInicial = 4; }
            default -> { cartasEvento = 5; manoInicial = 4; }
        }
    }

    public Integer getCantidadParticipantes() { return cantidadParticipantes; }
    public Dificultad getDificultad() { return dificultad; }
    public Integer getCartasEvento() { return cartasEvento; }
    public Integer getManoInicial() { return manoInicial; }
}
