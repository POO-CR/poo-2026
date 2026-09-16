package vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import modelo.IFightView;

public class FightSwingView extends JFrame implements IFightView {

	//Reemplazamos el Map de Color por BufferedImage
    //private final Map<String, BufferedImage> spriteAtlas = new HashMap<>();
    private final Map<String, Color> spriteColors = new HashMap<>();

    private String p1Anim = "idle";
    private String p2Anim = "idle";
    private double p1HpPct = 100.0;
    private double p2HpPct = 100.0;
    private String comboMessage = "";

    private final JPanel arenaPanel;

    public FightSwingView() {
        super("Pelea MVC - Demo Sprites Placeholder");
        setupColorMap();

        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        arenaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawScene((Graphics2D) g);
            }
        };
        arenaPanel.setBackground(new Color(30, 30, 30));
        add(arenaPanel, BorderLayout.CENTER);

        setFocusable(true);
        setVisible(true);
    }

    private void setupColorMap() {
        spriteColors.put("idle", new Color(100, 149, 237));                 // Celeste
        spriteColors.put("punch_light", new Color(255, 165, 0));             // Naranja
        spriteColors.put("kick_light", new Color(255, 215, 0));              // Amarillo
        spriteColors.put("combo_punch_finisher", new Color(255, 69, 0));     // Rojo anaranjado
        spriteColors.put("fireball_cast", new Color(186, 85, 211));          // Violeta
        spriteColors.put("hit_light", new Color(178, 34, 34));               // Rojo oscuro
        spriteColors.put("hit_heavy", new Color(139, 0, 0));                 // Granate
        spriteColors.put("knockdown", new Color(80, 80, 80));                // Gris piso
    }

    private void drawScene(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Barras de Vida
        drawHealthBar(g, 50, 40, 250, 20, p1HpPct, "Jugador 1");
        drawHealthBar(g, 380, 40, 250, 20, p2HpPct, "Jugador 2");

        // 2. Personajes
        drawFighterSquare(g, 120, 180, p1Anim, "P1");
        drawFighterSquare(g, 460, 180, p2Anim, "P2");

        // 3. Popup Combo
        if (!comboMessage.isEmpty()) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("COMBO: " + comboMessage, 260, 130);
        }

        // Instrucciones
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Controles: [J] Golpe | [K] Patada | [L] Especial", 220, 360);
        g.drawString("Combos: [J + J] (Doble) | [J + K + L] (Dragon Fury)", 200, 385);
    }

    private void drawFighterSquare(Graphics2D g, int x, int y, String animKey, String label) {
        Color color = spriteColors.getOrDefault(animKey, Color.WHITE);
        g.setColor(color);
        g.fillRect(x, y, 100, 120);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        g.drawRect(x, y, 100, 120);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(label, x + 10, y + 25);
        g.drawString("[" + animKey + "]", x + 10, y + 60);
    }
    
    //Se remplazaria el metodo de dibujado de cuadrados por uno de sprites
    /*private void drawFighterSprite(Graphics2D g, int x, int y, String fighterPrefix, String animKey) {
        // Buscamos el sprite correspondiente (ej: "p1_punch_light")
        String lookupKey = fighterPrefix.toLowerCase() + "_" + animKey;
        BufferedImage sprite = spriteAtlas.get(lookupKey);

        // Fallback: si falta la animación, mostramos su 'idle'
        if (sprite == null) {
            sprite = spriteAtlas.get(fighterPrefix.toLowerCase() + "_idle");
        }

        if (sprite != null) {
            // Dibuja la imagen escalada al tamaño deseado (ancho: 120, alto: 140)
            g.drawImage(sprite, x, y, 120, 140, null);
        } else {
            // Red de seguridad si no cargó la imagen: dibuja un cuadro de error
            g.setColor(Color.MAGENTA);
            g.fillRect(x, y, 120, 140);
        }
    }*/

    private void drawHealthBar(Graphics2D g, int x, int y, int w, int h, double pct, String title) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(title, x, y - 5);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);

        int fillWidth = (int) (w * (pct / 100.0));
        g.setColor(pct > 30 ? Color.GREEN : Color.RED);
        g.fillRect(x, y, fillWidth, h);

        g.setColor(Color.WHITE);
        g.drawRect(x, y, w, h);
    }


    @Override
    public void addInputListener(KeyListener listener) {
        this.addKeyListener(listener);
    }

    @Override
    public void updateFighterState(String fighterId, String animationKey) {
        if ("P1".equals(fighterId)) {
            this.p1Anim = animationKey;
        } else {
            this.p2Anim = animationKey;
        }
        arenaPanel.repaint();
    }

    @Override
    public void updateHealthBar(String fighterId, double healthPercentage) {
        if ("P1".equals(fighterId)) {
            this.p1HpPct = healthPercentage;
        } else {
            this.p2HpPct = healthPercentage;
        }
        arenaPanel.repaint();
    }

    @Override
    public void showComboText(String message) {
        this.comboMessage = message;
        arenaPanel.repaint();
        new Timer(800, e -> {
            comboMessage = "";
            arenaPanel.repaint();
            ((Timer) e.getSource()).stop();
        }).start();
    }
    
    /*private void loadSprite(String key, String resourcePath) {
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is != null) {
                spriteAtlas.put(key, ImageIO.read(is));
            } else {
                System.err.println("No se encontró el recurso: " + resourcePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    /*private void setupSpriteAtlas() {
    // Claves abstractas del modelo -> Archivo PNG real
	    loadSprite("p1_idle", "/sprites/p1_idle.png");
	    loadSprite("p1_punch_light", "/sprites/p1_punch.png");
	    loadSprite("p1_kick_light", "/sprites/p1_kick.png");
	    loadSprite("p1_combo_punch_finisher", "/sprites/p1_combo.png");
	    loadSprite("p1_fireball_cast", "/sprites/p1_fireball.png");
	
	    loadSprite("p2_idle", "/sprites/p2_idle.png");
	    loadSprite("p2_hit_light", "/sprites/p2_hit.png");
	    loadSprite("p2_hit_heavy", "/sprites/p2_heavy_hit.png");
	    loadSprite("p2_knockdown", "/sprites/p2_knockdown.png");
	}*/
}