package vista;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Fondo negro, como el de la hoja de Goku: se borra el negro conectado con el
 * borde y las zonas negras encerradas grandes.
 */
public class FondoNegro implements IFondoHoja {

    /**
     * En hojas de fondo negro, una zona negra encerrada de al menos esta cantidad
     * de pixeles se considera fondo (las mas chicas son detalles del personaje).
     */
    private static final int HUECO_MINIMO_PX = 100;

    /**
     * Vuelve transparente el fondo negro de un fotograma con un "relleno por
     * inundacion" (flood fill) que arranca en los bordes: solo se borran los
     * pixeles negros conectados con el borde. El negro encerrado dentro del
     * personaje (por ejemplo en el pelo de Goku) queda intacto.
     *
     * <pre>
     * borde -> . . . . . .      . = negro de fondo (se borra)
     *          . . # # . .      # = personaje
     *          . # o # # .      o = negro encerrado (se queda)
     *          . . # # . .
     * </pre>
     *
     * @param fotograma imagen a modificar
     */
    @Override
    public void quitar(BufferedImage fotograma) {
        int ancho = fotograma.getWidth();
        int alto = fotograma.getHeight();
        boolean[] visitado = new boolean[ancho * alto];
        Deque<Integer> pendientes = new ArrayDeque<>();

        for (int x = 0; x < ancho; x++) {
            pendientes.add(x);
            pendientes.add((alto - 1) * ancho + x);
        }
        for (int y = 0; y < alto; y++) {
            pendientes.add(y * ancho);
            pendientes.add(y * ancho + ancho - 1);
        }

        while (!pendientes.isEmpty()) {
            int indice = pendientes.poll();
            if (visitado[indice]) {
                continue;
            }
            visitado[indice] = true;

            int x = indice % ancho;
            int y = indice / ancho;
            if (!esNegro(fotograma.getRGB(x, y))) {
                continue; /* no es negro: es parte del personaje */
            }
            fotograma.setRGB(x, y, 0);

            if (x > 0) pendientes.add(indice - 1);
            if (x < ancho - 1) pendientes.add(indice + 1);
            if (y > 0) pendientes.add(indice - ancho);
            if (y < alto - 1) pendientes.add(indice + ancho);
        }

        quitarHuecosGrandes(fotograma, visitado);
    }

    /**
     * Despues del relleno desde el borde, pueden quedar zonas de negro puro
     * encerradas. Las chicas son detalles del personaje (pelo, dedos) y se
     * quedan; las grandes son fondo encerrado, por ejemplo entre un brazo en alto
     * y el cuerpo, y se borran.
     *
     * @param fotograma imagen a modificar
     * @param visitado  pixeles que ya reviso el relleno desde el borde
     */
    private static void quitarHuecosGrandes(BufferedImage fotograma, boolean[] visitado) {
        int ancho = fotograma.getWidth();
        int alto = fotograma.getHeight();
        for (int inicio = 0; inicio < ancho * alto; inicio++) {
            if (visitado[inicio] || !esNegro(fotograma.getRGB(inicio % ancho, inicio / ancho))) {
                continue;
            }
            /* Junta toda la zona negra que empieza en este pixel */
            List<Integer> zona = new ArrayList<>();
            Deque<Integer> pendientes = new ArrayDeque<>();
            pendientes.add(inicio);
            visitado[inicio] = true;
            while (!pendientes.isEmpty()) {
                int indice = pendientes.poll();
                zona.add(indice);
                int x = indice % ancho;
                int y = indice / ancho;
                int[] vecinos = { x > 0 ? indice - 1 : -1, x < ancho - 1 ? indice + 1 : -1,
                        y > 0 ? indice - ancho : -1, y < alto - 1 ? indice + ancho : -1 };
                for (int v : vecinos) {
                    if (v >= 0 && !visitado[v] && esNegro(fotograma.getRGB(v % ancho, v / ancho))) {
                        visitado[v] = true;
                        pendientes.add(v);
                    }
                }
            }
            if (zona.size() >= HUECO_MINIMO_PX) {
                for (int indice : zona) {
                    fotograma.setRGB(indice % ancho, indice / ancho, 0);
                }
            }
        }
    }

    /**
     * @param argb color de un pixel
     * @return true si el pixel es negro puro (sin mirar la transparencia)
     */
    private static boolean esNegro(int argb) {
        return (argb & 0x00FFFFFF) == 0;
    }
}
