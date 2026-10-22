package modelo;

/**
 * Caja de colision: un rectangulo alineado con los ejes, en coordenadas de la
 * arena. La pelea la usa para saber si un rayo toca a un luchador o a otro
 * rayo.
 */
public class CajaColision {

    private final double izquierda;
    private final double arriba;
    private final double ancho;
    private final double alto;

    /**
     * @param izquierda borde izquierdo
     * @param arriba    borde superior
     * @param ancho     ancho
     * @param alto      alto
     */
    public CajaColision(double izquierda, double arriba, double ancho, double alto) {
        this.izquierda = izquierda;
        this.arriba = arriba;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Dos cajas se tocan cuando las dos tienen area y sus interiores se
     * superponen.
     *
     * @param otra la otra caja
     * @return true si las dos cajas se tocan
     */
    public boolean seSuperponeCon(CajaColision otra) {
        return tieneArea() && otra.tieneArea()
                && izquierda < otra.izquierda + otra.ancho && otra.izquierda < izquierda + ancho
                && arriba < otra.arriba + otra.alto && otra.arriba < arriba + alto;
    }

    /**
     * @return true si el ancho y el alto son positivos
     */
    private boolean tieneArea() {
        return ancho > 0 && alto > 0;
    }
}
