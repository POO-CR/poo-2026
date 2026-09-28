// Archivo: ControladorConversor.java (solucion)
// El controlador solo le pide cosas al modelo. No actualiza ninguna vista:
// el Conversor avisa a las que esten observando.

package controlador;

import modelo.Conversor;
import vista.VistaConversor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorConversor {

    private final VistaConversor vista;
    private final Conversor conversor;

    public ControladorConversor(VistaConversor vista, Conversor conversor) {
        if (vista == null || conversor == null) {
            throw new IllegalArgumentException("La vista y el conversor son obligatorios");
        }
        this.vista = vista;
        this.conversor = conversor;

        this.vista.getBotonDolares().addActionListener(evento -> this.convertirADolares());
        this.vista.getBotonEuros().addActionListener(evento -> this.convertirAEuros());
        this.vista.getBotonReales().addActionListener(evento -> this.convertirAReales());

        this.vista.getCampoPesos().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    convertirADolares();
                }
            }
        });

        this.vista.getCampoPesos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evento) {
                if (evento.getClickCount() == 2) {
                    vista.limpiar();
                }
            }
        });
    }

    private void convertirADolares() {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            this.conversor.aDolares(pesos);
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void convertirAEuros() {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            this.conversor.aEuros(pesos);
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void convertirAReales() {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            this.conversor.aReales(pesos);
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
