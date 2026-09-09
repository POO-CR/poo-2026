package view;

import controller.MainController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Colisionable;
import model.Obstaculo;
import model.Personaje;

public class MainView extends JPanel {
    private MainController controller;
    private final Map<String, BufferedImage> cacheImagenes = new HashMap<>();

    public MainView(MainController controller, int ancho, int alto) {
        this.controller = controller;
        setPreferredSize(new Dimension(ancho, alto));
        setBackground(new Color(30, 30, 35));
        setFocusable(true);
        JFrame ventana = new JFrame("Troll Colisiones - MVC & SOLID");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.add(this);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        this.requestFocusInWindow();
    }

    private BufferedImage obtenerImagen(String path) {
        if (path == null) return null;
        if (cacheImagenes.containsKey(path)) {
            return cacheImagenes.get(path);
        }

        BufferedImage img = null;

        String[] intentos = {
            path,
            "colisiones/" + path,
            "src/" + path,
            "/" + path
        };

        for (String intento : intentos) {
            try {
                File f = new File(intento);
                if (f.exists() && f.isFile()) {
                    img = ImageIO.read(f);
                    break;
                }
                var res = getClass().getResource(intento.startsWith("/") ? intento : "/" + intento);
                if (res != null) {
                    img = ImageIO.read(res);
                    break;
                }
            } catch (IOException ignored) {}
        }

        if (img != null) {
            cacheImagenes.put(path, img);
        } else {
            System.err.println("[AVISO] No se pudo encontrar el archivo: " + path + ". Se usará renderizado alternativo.");
            cacheImagenes.put(path, null);
        }

        return img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Colisionable> entidades = controller.getEntidades();
        if (entidades == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Colisionable e : entidades) {
            if (e instanceof Personaje p) {
                BufferedImage sprite = obtenerImagen(p.getAssetPath());

                if (sprite != null) {
                    g2.drawImage(sprite, p.getX(), p.getY(), p.getAncho(), p.getAlto(), null);
                } else {
                    g2.setColor(new Color(59, 130, 246));
                    g2.fillRect(p.getX(), p.getY(), p.getAncho(), p.getAlto());
                    g2.setColor(Color.WHITE);
                    g2.drawString("Troll", p.getX() + 10, p.getY() + 35);
                }
            } else if (e instanceof Obstaculo obs) {
                g2.setColor(new Color(239, 68, 68));
                g2.fillRect(obs.getX(), obs.getY(), obs.getAncho(), obs.getAlto());
                g2.setColor(new Color(185, 28, 28));
                g2.drawRect(obs.getX(), obs.getY(), obs.getAncho(), obs.getAlto());
            }
        }
    }
}