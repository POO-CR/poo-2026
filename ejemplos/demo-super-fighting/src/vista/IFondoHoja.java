package vista;

import java.awt.image.BufferedImage;

/**
 * Fondo de una hoja de sprites (patron Strategy): cada implementacion sabe
 * volver transparente su tipo de fondo en un fotograma recien recortado.
 * {@link HojaSprites} le delega esa tarea al fondo que recibe.
 */
public interface IFondoHoja {

    /**
     * Vuelve transparente el fondo del fotograma.
     *
     * @param fotograma imagen ARGB a modificar
     */
    void quitar(BufferedImage fotograma);
}
