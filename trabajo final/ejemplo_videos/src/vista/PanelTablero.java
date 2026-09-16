package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.function.Consumer;
import javax.swing.*;
import modelo.Tablero;
import modelo.Ubicacion;

public class PanelTablero extends JPanel {

    private Image imagenMapa;
    private int anchoOriginalImg = 1;
    private int altoOriginalImg = 1;

    // Factores de transformación para mantener la posición fija
    private double escala = 1.0;
    private int offsetX = 0;
    private int offsetY = 0;

    private Tablero tablero;
    private Ubicacion ubicacionSeleccionada;
    private Ubicacion ubicacionHover;
    private final Consumer<Ubicacion> alSeleccionarUbicacion;

    private static final int RADIO_HITBOX = 20;

    public PanelTablero(Consumer<Ubicacion> alSeleccionarUbicacion) {
        this.alSeleccionarUbicacion = alSeleccionarUbicacion;
        setBackground(new Color(15, 16, 20));
        cargarFondo();

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Calibrador en consola: convierte coordenadas de pantalla a coordenadas reales de la imagen
                int realX = (int) Math.round((e.getX() - offsetX) / escala);
                int realY = (int) Math.round((e.getY() - offsetY) / escala);
                System.out.printf("--- CALIBRADOR: pos_x = %d, pos_y = %d ---%n", realX, realY);

                manejarClic(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                manejarHover(e.getX(), e.getY());
            }
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    private void cargarFondo() {
        File f = new File("src/assets/mapa.png");
        if (!f.exists()) f = new File("src/assets/fondo.png");
        if (f.exists()) {
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            imagenMapa = icon.getImage();
            anchoOriginalImg = icon.getIconWidth();
            altoOriginalImg = icon.getIconHeight();
        }
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        repaint();
    }

    public void setUbicacionSeleccionada(Ubicacion u) {
        this.ubicacionSeleccionada = u;
        repaint();
    }

    private void calcularTransformacion() {
        int wPanel = getWidth();
        int hPanel = getHeight();

        if (wPanel == 0 || hPanel == 0 || anchoOriginalImg == 0 || altoOriginalImg == 0) return;

        // Mantener Aspect Ratio (ajuste letterbox)
        double escalaX = (double) wPanel / anchoOriginalImg;
        double escalaY = (double) hPanel / altoOriginalImg;
        escala = Math.min(escalaX, escalaY);

        int anchoRender = (int) (anchoOriginalImg * escala);
        int altoRender = (int) (altoOriginalImg * escala);

        offsetX = (wPanel - anchoRender) / 2;
        offsetY = (hPanel - altoRender) / 2;
    }

    // Convierte las coordenadas fijas del mapa a coordenadas de la pantalla
    private Point convertirCoordenada(int xOriginal, int yOriginal) {
        int xP = (int) Math.round(offsetX + (xOriginal * escala));
        int yP = (int) Math.round(offsetY + (yOriginal * escala));
        return new Point(xP, yP);
    }

    private Ubicacion buscarUbicacionEn(int x, int y) {
        if (tablero == null) return null;
        for (Ubicacion u : tablero.getMapaUbicaciones().values()) {
            Point p = convertirCoordenada(u.getPosX(), u.getPosY());
            if (Math.hypot(x - p.x, y - p.y) <= (RADIO_HITBOX * escala + 5)) {
                return u;
            }
        }
        return null;
    }

    private void manejarHover(int x, int y) {
        Ubicacion ant = ubicacionHover;
        ubicacionHover = buscarUbicacionEn(x, y);
        if (ant != ubicacionHover) {
            setCursor(new Cursor(ubicacionHover != null ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
            repaint();
        }
    }

    private void manejarClic(int x, int y) {
        Ubicacion u = buscarUbicacionEn(x, y);
        if (u != null && alSeleccionarUbicacion != null) {
            alSeleccionarUbicacion.accept(u);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        calcularTransformacion();

        // 1. Dibujar imagen del mapa escalada y centrada
        if (imagenMapa != null) {
            int anchoRender = (int) (anchoOriginalImg * escala);
            int altoRender = (int) (altoOriginalImg * escala);
            g2.drawImage(imagenMapa, offsetX, offsetY, anchoRender, altoRender, this);
        }

        if (tablero == null) {
            g2.dispose();
            return;
        }

        // 2. Renderizar Nodos ajustados
        int radio = Math.max(14, (int) (RADIO_HITBOX * escala));

        for (Ubicacion u : tablero.getMapaUbicaciones().values()) {
            Point p = convertirCoordenada(u.getPosX(), u.getPosY());

            boolean sel = (ubicacionSeleccionada != null && ubicacionSeleccionada.getId() == u.getId());
            boolean hov = (ubicacionHover != null && ubicacionHover.getId() == u.getId());

            // Anillos interactivos
            if (sel) {
                g2.setColor(new Color(0, 240, 255, 220));
                g2.setStroke(new BasicStroke(3.5f));
                g2.drawOval(p.x - radio, p.y - radio, radio * 2, radio * 2);
            } else if (hov) {
                g2.setColor(new Color(255, 215, 0, 190));
                g2.setStroke(new BasicStroke(2.5f));
                g2.drawOval(p.x - radio, p.y - radio, radio * 2, radio * 2);
            }

            // Badges de Tropas de la Sombra (esquina sup-der)
            if (u.getTropasSombra() > 0) {
                dibujarBadge(g2, p.x + (radio / 2), p.y - radio, String.valueOf(u.getTropasSombra()), new Color(180, 25, 25, 230));
            }

            // Badges de Tropas Aliadas (esquina sup-izq)
            if (u.getTropasAliadas() > 0) {
                dibujarBadge(g2, p.x - radio - 10, p.y - radio, String.valueOf(u.getTropasAliadas()), new Color(30, 100, 180, 230));
            }

            // Marcador de Héroes presentes (centro)
            if (!u.getPersonajesPresentes().isEmpty()) {
                g2.setColor(new Color(255, 215, 0));
                g2.fillOval(p.x - 8, p.y - 8, 16, 16);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("SansSerif", Font.BOLD, 10));
                g2.drawString(String.valueOf(u.getPersonajesPresentes().size()), p.x - 3, p.y + 4);
            }
        }

        g2.dispose();
    }

    private void dibujarBadge(Graphics2D g2, int x, int y, String texto, Color fondo) {
        g2.setColor(fondo);
        g2.fillRoundRect(x, y, 16, 16, 5, 5);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawRoundRect(x, y, 16, 16, 5, 5);
        g2.setFont(new Font("SansSerif", Font.BOLD, 10));
        g2.drawString(texto, x + 4, y + 12);
    }
}