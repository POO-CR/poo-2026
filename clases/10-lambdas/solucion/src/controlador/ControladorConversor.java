// Archivo: ControladorConversor.java (solucion)
// El controlador. Ya no implementa ActionListener ni tiene actionPerformed():
// tiene un receptor por evento, escrito donde se registra, y un metodo
// privado por cosa que puede pasar. Agregar una moneda es una lambda y un
// metodo (TODO 6); nada de lo que ya estaba se modifica.

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

        // TODO 3: una lambda por boton. ActionListener tiene un solo metodo,
        // asi que el compilador sabe cual esta implementando cada una.
        this.vista.getBotonDolares().addActionListener(evento -> this.convertirADolares());
        this.vista.getBotonEuros().addActionListener(evento -> this.convertirAEuros());
        this.vista.getBotonReales().addActionListener(evento -> this.convertirAReales());   // TODO 6

        // TODO 4: KeyListener tiene tres metodos; no puede ser una lambda.
        // Clase anonima que hereda de KeyAdapter y sobrescribe solo uno.
        this.vista.getCampoPesos().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
                    convertirADolares();   // sin this: aca this es el KeyAdapter
                }
            }
        });

        // TODO 5: MouseListener tiene cinco metodos. Mismo esquema.
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

    // TODO 6
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
