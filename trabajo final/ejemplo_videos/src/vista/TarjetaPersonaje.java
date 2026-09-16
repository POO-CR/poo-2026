package vista;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import modelo.Personaje;

public class TarjetaPersonaje extends JPanel {

    private final Personaje personaje;
    private boolean seleccionada;
    private Image imagenRetrato;

    private static final int ANCHO = 220;
    private static final int ALTO = 330;
    private static final int ANCHO_IMG = 190;
    private static final int ALTO_IMG = 180;

    private static final Color COLOR_FONDO = new Color(20, 22, 28, 235);
    private static final Color COLOR_BORDE_NORMAL = new Color(130, 115, 80);
    private static final Color COLOR_BORDE_SELECCIONADO = new Color(255, 215, 0);
    private static final Color COLOR_FONDO_SELECCIONADO = new Color(55, 45, 18, 245);

    public TarjetaPersonaje(Personaje personaje, Runnable alHacerHover, Runnable alHacerClic) {
        this.personaje = personaje;
        this.seleccionada = false;

        cargarImagen(personaje.getRutaImagen());

        setPreferredSize(new Dimension(ANCHO, ALTO));
        setMaximumSize(new Dimension(ANCHO, ALTO));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(ALTO_IMG + 20)); // Espacio superior reservado para el retrato

        // Nombre del héroe
        JLabel lblNombre = new JLabel(personaje.getNombre().toUpperCase());
        lblNombre.setFont(new Font("Serif", Font.BOLD, 17));
        lblNombre.setForeground(new Color(240, 230, 200));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblNombre);

        add(Box.createVerticalStrut(6));

        // Ubicación de inicio
        JLabel lblUbicacion = new JLabel("Inicio: " + personaje.getUbicacionActual());
        lblUbicacion.setFont(new Font("Serif", Font.ITALIC, 13));
        lblUbicacion.setForeground(new Color(180, 175, 150));
        lblUbicacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblUbicacion);

        // Listeners de interacción
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (alHacerHover != null && !seleccionada) {
                    alHacerHover.run();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (alHacerClic != null) {
                    alHacerClic.run();
                }
            }
        });
    }

    private void cargarImagen(String ruta) {
        if (ruta == null) return;
        File f = new File(ruta);
        if (f.exists()) {
            ImageIcon icon = new ImageIcon(f.getAbsolutePath());
            imagenRetrato = icon.getImage().getScaledInstance(ANCHO_IMG, ALTO_IMG, Image.SCALE_SMOOTH);
        } else {
            System.err.println("Imagen no encontrada: " + f.getAbsolutePath());
        }
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
        repaint();
    }

    public boolean isSeleccionada() { return seleccionada; }
    public Personaje getPersonaje() { return personaje; }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int w = getWidth();
        int h = getHeight();

        // Fondo de la tarjeta
        g2.setColor(seleccionada ? COLOR_FONDO_SELECCIONADO : COLOR_FONDO);
        g2.fillRoundRect(4, 4, w - 8, h - 8, 14, 14);

        // Marco y dibujo de la ilustración
        int xImg = (w - ANCHO_IMG) / 2;
        int yImg = 14;

        if (imagenRetrato != null) {
            g2.drawImage(imagenRetrato, xImg, yImg, this);
        } else {
            // Fondo grisáceo si aún no existe el archivo PNG
            g2.setColor(new Color(40, 42, 50));
            g2.fillRect(xImg, yImg, ANCHO_IMG, ALTO_IMG);
        }

        // Borde interno de la ilustración
        g2.setColor(new Color(90, 80, 60));
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawRect(xImg, yImg, ANCHO_IMG, ALTO_IMG);

        // Borde principal de la tarjeta
        g2.setColor(seleccionada ? COLOR_BORDE_SELECCIONADO : COLOR_BORDE_NORMAL);
        g2.setStroke(new BasicStroke(seleccionada ? 3.0f : 1.5f));
        g2.drawRoundRect(4, 4, w - 8, h - 8, 14, 14);

        // Resplandor si está seleccionada
        if (seleccionada) {
            g2.setColor(new Color(255, 235, 130, 90));
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawRoundRect(8, 8, w - 16, h - 16, 10, 10);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}