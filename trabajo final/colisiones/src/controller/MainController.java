package controller;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;
import model.Colisionable;
import model.Obstaculo;
import model.Personaje;
import model.Troll;
import view.MainView;

public class MainController {
    private final Troll jugador;
    private final List<Obstaculo> obstaculos;
    private final MainView vista;
    private final Set<Integer> teclasPulsadas = new HashSet<>();
    private final int limiteAncho;
    private final int limiteAlto;
    private Timer gameLoop;
    private final List<Colisionable> listaEntidades;

    public MainController(Troll jugador, List<Obstaculo> obstaculos, int limiteAncho, int limiteAlto) {
        this.jugador = jugador;
        this.obstaculos = obstaculos;
        this.listaEntidades = new ArrayList<>();
        this.listaEntidades.addAll(obstaculos);
        this.listaEntidades.add(jugador);
        this.vista = new MainView(this, limiteAncho, limiteAlto);
        this.limiteAncho = limiteAncho;
        this.limiteAlto = limiteAlto;
        configurar();
    }

    public List<Colisionable> getEntidades() {
        return listaEntidades;
    }

    private void configurar() {
        vista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                teclasPulsadas.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                teclasPulsadas.remove(e.getKeyCode());
            }
        });

        // Loop a ~60 FPS
        this.gameLoop = new Timer(16, e -> tick());
    }

    public void iniciar() {
        this.gameLoop.start();
    }

    private void tick() {
        int dirX = 0;
        int dirY = 0;

        if (teclasPulsadas.contains(KeyEvent.VK_W) || teclasPulsadas.contains(KeyEvent.VK_UP)) dirY -= 1;
        if (teclasPulsadas.contains(KeyEvent.VK_S) || teclasPulsadas.contains(KeyEvent.VK_DOWN)) dirY += 1;
        if (teclasPulsadas.contains(KeyEvent.VK_A) || teclasPulsadas.contains(KeyEvent.VK_LEFT)) dirX -= 1;
        if (teclasPulsadas.contains(KeyEvent.VK_D) || teclasPulsadas.contains(KeyEvent.VK_RIGHT)) dirX += 1;

        if (dirX != 0) intentarMover(jugador, dirX * jugador.getVelocidad(), 0);
        if (dirY != 0) intentarMover(jugador, 0, dirY * jugador.getVelocidad());

        vista.repaint();
    }

    private void intentarMover(Personaje p, int dx, int dy) {
        int proximoX = p.getX() + dx;
        int proximoY = p.getY() + dy;

        if (proximoX < 0 || proximoX + p.getAncho() > limiteAncho) return;
        if (proximoY < 0 || proximoY + p.getAlto() > limiteAlto) return;

        Rectangle cajaSiguiente = new Rectangle(proximoX, proximoY, p.getAncho(), p.getAlto());

        for (Obstaculo obs : obstaculos) {
            if (cajaSiguiente.intersects(obs.getLimites())) {
                return; 
            }
        }

        p.setPosicion(proximoX, proximoY);
    }
}