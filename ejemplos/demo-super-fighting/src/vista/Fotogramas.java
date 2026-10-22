package vista;

import java.awt.image.BufferedImage;

/**
 * Los dibujos de una animacion: la imagen de cada fotograma y su ancla. El
 * modelo ({@link modelo.Animacion}) solo sabe cuantos fotogramas hay y cuanto
 * dura cada uno; como se ve cada uno lo sabe esta clase.
 *
 * Como los fotogramas tienen distintos anchos (un golpe con el brazo estirado
 * es mas ancho que la guardia), no alcanza con dibujarlos centrados: cada
 * fotograma guarda su "ancla", la columna donde esta el centro del cuerpo, y el
 * dibujo se alinea con esa columna para que el personaje no salte de lugar.
 */
public class Fotogramas {

    private final BufferedImage[] imagenes;
    private final int[] anclasX;

    /**
     * @param imagenes fotogramas en orden, mirando a la derecha
     */
    public Fotogramas(BufferedImage[] imagenes) {
        this.imagenes = imagenes;
        anclasX = new int[imagenes.length];
        for (int i = 0; i < imagenes.length; i++) {
            anclasX[i] = calcularAnclaX(imagenes[i]);
        }
    }

    /**
     * Calcula el promedio de las columnas de los pixeles visibles (su centro de
     * masa horizontal). Un brazo o una pierna estirados pesan poco comparados
     * con el cuerpo, asi que el resultado queda sobre el torso.
     *
     * @param imagen imagen con fondo transparente
     * @return la columna del centro del cuerpo
     */
    private static int calcularAnclaX(BufferedImage imagen) {
        long sumaX = 0;
        long cantidad = 0;
        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                if ((imagen.getRGB(x, y) >>> 24) != 0) {
                    sumaX += x;
                    cantidad++;
                }
            }
        }
        return cantidad == 0 ? imagen.getWidth() / 2 : (int) (sumaX / cantidad);
    }

    /**
     * @param indice numero de fotograma
     * @return la imagen de ese fotograma
     */
    public BufferedImage getImagen(int indice) {
        return imagenes[indice];
    }

    /**
     * @param indice numero de fotograma
     * @return la columna del centro del cuerpo en ese fotograma
     */
    public int getAnclaX(int indice) {
        return anclasX[indice];
    }

    /**
     * @return la cantidad de fotogramas
     */
    public int getCantidad() {
        return imagenes.length;
    }
}
