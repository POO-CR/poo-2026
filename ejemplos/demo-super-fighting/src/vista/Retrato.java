package vista;

import java.awt.image.BufferedImage;

/**
 * Imagen del personaje que aparece a pantalla completa (en su mitad) al lanzar
 * el super, como en Marvel vs. Capcom, junto con lo que hace falta para
 * recortarla y orientarla.
 */
public class Retrato {

    private final BufferedImage imagen;
    private final double focoX;
    private final boolean miraALaDerecha;

    /**
     * @param imagen         imagen del personaje
     * @param focoX          que parte de la imagen hay que dejar a la vista al
     *                       recortarla (0 = borde izquierdo, 0,5 = centro, 1 =
     *                       borde derecho); normalmente, donde esta la cara
     * @param miraALaDerecha true si en la imagen el personaje mira hacia la
     *                       derecha (se espeja cuando va del otro lado, para que
     *                       siempre mire hacia el centro de la pantalla)
     */
    public Retrato(BufferedImage imagen, double focoX, boolean miraALaDerecha) {
        this.imagen = imagen;
        this.focoX = focoX;
        this.miraALaDerecha = miraALaDerecha;
    }

    /**
     * @return la imagen del personaje
     */
    public BufferedImage getImagen() {
        return imagen;
    }

    /**
     * @return la parte de la imagen que hay que dejar a la vista, de 0 a 1
     */
    public double getFocoX() {
        return focoX;
    }

    /**
     * @return true si en la imagen el personaje mira hacia la derecha
     */
    public boolean isMirandoALaDerecha() {
        return miraALaDerecha;
    }
}
