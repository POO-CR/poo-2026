import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class PanelTablero extends JPanel{

    private final int filas;
    private final int columnas;
    private final int tamanioCelda;
    private List<Personaje> personajes;
    private Personaje personajeSeleccionado;
    
    public PanelTablero(int filas, int columnas, int tamanioCelda, List<Personaje> personajes) {
        this.filas = filas;
        this.columnas = columnas;
        this.tamanioCelda = tamanioCelda;
        this.personajes = personajes;
        this.personajeSeleccionado = null;

        setPreferredSize(new Dimension(columnas * tamanioCelda, filas * tamanioCelda));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                manejarClic(e.getX(), e.getY());
            }
        });
    }

    private void manejarClic(int mouseX, int mouseY) {
        // Me da las coordenadas.
        int col = mouseX / tamanioCelda;
        int fila = mouseY / tamanioCelda;

        Personaje personajeEnCelda = buscarPersonajeEn(col, fila);

        if (personajeSeleccionado != null && personajeEnCelda != null && !personajeEnCelda.isEsHeroe()) {
            int distancia = Math.abs(personajeSeleccionado.getX() - col) + Math.abs(personajeSeleccionado.getY() - fila);

            if (distancia == 1) {
                personajeEnCelda.recibirDanio(personajeSeleccionado.getAtaque());
                
                if (!personajeEnCelda.estaVivo()) {
                    personajes.remove(personajeEnCelda);
                }

                personajeSeleccionado.setSeleccionado(false);
                personajeSeleccionado = null;
            }
        } else if (personajeEnCelda != null && personajeEnCelda.isEsHeroe()) {
            if (personajeSeleccionado != null) {
                personajeSeleccionado.setSeleccionado(false);
            }
            personajeSeleccionado = personajeEnCelda;
            personajeSeleccionado.setSeleccionado(true);
        } else if (personajeSeleccionado != null) {
            personajeSeleccionado.setX(col);
            personajeSeleccionado.setY(fila);
            personajeSeleccionado.setSeleccionado(false);
            personajeSeleccionado = null;
        }

        repaint();
    }

    public void ejecutarTurnoEnemigo() {
        if (personajeSeleccionado != null) {
            personajeSeleccionado.setSeleccionado(false);
            personajeSeleccionado = null;
        }

        // Busca a los enemigos y los héroes vivos
        List<Personaje> copiaPersonajes = new ArrayList<>(personajes);
        for (Personaje p : copiaPersonajes) {
            if (!p.isEsHeroe() && p.estaVivo()) {
                Personaje heroeCercano = buscarHeroeMasCercano(p);
                if (heroeCercano != null) {
                    int dist = Math.abs(p.getX() - heroeCercano.getX()) + Math.abs(p.getY() - heroeCercano.getY());
                    
                    if (dist == 1) {
                        // si lo tiene al lado ataca
                        heroeCercano.recibirDanio(p.getAtaque());
                        if (!heroeCercano.estaVivo()) {
                            personajes.remove(heroeCercano);
                        }
                    } else {
                        // Si está lejos se acerca 1 paso
                        if (p.getX() < heroeCercano.getX()) p.setX(p.getX() + 1);
                        else if (p.getX() > heroeCercano.getX()) p.setX(p.getX() - 1);
                        else if (p.getY() < heroeCercano.getY()) p.setY(p.getY() + 1);
                        else if (p.getY() > heroeCercano.getY()) p.setY(p.getY() - 1);
                    }
                }
            }
        }
        repaint();
    }

    private Personaje buscarHeroeMasCercano(Personaje enemigo) {
        Personaje masCercano = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (Personaje p : personajes) {
            if (p.isEsHeroe() && p.estaVivo()) {
                int d = Math.abs(enemigo.getX() - p.getX()) + Math.abs(enemigo.getY() - p.getY());
                if (d < menorDistancia) {
                    menorDistancia = d;
                    masCercano = p;
                }
            }
        }
        return masCercano;
    }

    private Personaje buscarPersonajeEn(int x, int y) {
        for (Personaje p : personajes) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Renderizado del tablero y personajes
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int xPixel = col * tamanioCelda;
                int yPixel = fila * tamanioCelda;

                if ((fila + col) % 2 == 0) g.setColor(new Color(230, 230, 230));
                else g.setColor(new Color(200, 200, 200));
                
                g.fillRect(xPixel, yPixel, tamanioCelda, tamanioCelda);
                g.setColor(Color.GRAY);
                g.drawRect(xPixel, yPixel, tamanioCelda, tamanioCelda);
            }
        }

        int margen = 10;
        int tamanioPersonaje = tamanioCelda - (margen * 2);

        for (Personaje p : personajes) {
            int xPixel = (p.getX() * tamanioCelda) + margen;
            int yPixel = (p.getY() * tamanioCelda) + margen;

            g.setColor(p.getColor());
            g.fillRect(xPixel, yPixel, tamanioPersonaje, tamanioPersonaje);

            if (p.isSeleccionado()) {
                g.setColor(Color.YELLOW);
                g.drawRect(xPixel - 2, yPixel - 2, tamanioPersonaje + 4, tamanioPersonaje + 4);
            } else {
                g.setColor(Color.BLACK);
                g.drawRect(xPixel, yPixel, tamanioPersonaje, tamanioPersonaje);
            }

            g.setColor(Color.WHITE);
            String iniciales = p.getNombre().length() >= 2 ? p.getNombre().substring(0, 2) : p.getNombre();
            g.drawString(iniciales, xPixel + 12, yPixel + 20);

            // Barra de vida
            int anchoBarra = tamanioPersonaje;
            int altoBarra = 4;
            int vidaAncho = (int) ((double) p.getHp() / p.getHpMax() * anchoBarra);

            g.setColor(Color.RED);
            g.fillRect(xPixel, yPixel - 7, anchoBarra, altoBarra);
            g.setColor(Color.GREEN);
            g.fillRect(xPixel, yPixel - 7, vidaAncho, altoBarra);
            g.setColor(Color.BLACK);
            g.drawRect(xPixel, yPixel - 7, anchoBarra, altoBarra);
        }
    }
}    
