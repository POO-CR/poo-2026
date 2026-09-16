// Archivo: ControladorConversor.java
// El controlador tal como quedo al final de la clase pasada: un receptor por
// evento, escrito donde se registra. Hoy no se toca hasta el TODO 5, y ahi
// solo se agrega: una lambda y un metodo para las libras.

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

        // Un receptor por boton.
        this.vista.getBotonDolares().addActionListener(evento -> this.convertirADolares());
        this.vista.getBotonEuros().addActionListener(evento -> this.convertirAEuros());
        this.vista.getBotonReales().addActionListener(evento -> this.convertirAReales());

        // Enter en el campo convierte a dolares.
        this.vista.getCampoPesos().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    convertirADolares();
                }
            }
        });

        // Doble click en el campo vacia el campo y el resultado.
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
            this.vista.mostrarResultado(String.format("%.2f USD", this.conversor.aDolares(pesos)));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void convertirAEuros() {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            this.vista.mostrarResultado(String.format("%.2f EUR", this.conversor.aEuros(pesos)));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }

    private void convertirAReales() {
        try {
            double pesos = Double.parseDouble(this.vista.getTextoPesos());
            this.vista.mostrarResultado(String.format("%.2f BRL", this.conversor.aReales(pesos)));
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
