// Archivo: ControladorConversor.java
// El controlador convierte y despues actualiza las vistas a mano. Tiene que
// conocer a las dos, y acordarse de las dos en cada metodo.
//
// TODO 4: el controlador solo le pide cosas al modelo; no actualiza vistas.

package controlador;

import modelo.Conversor;
import vista.VistaConversor;
import vista.VistaHistorial;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorConversor {

    private final VistaConversor vista;
    private final VistaHistorial historial;
    private final Conversor conversor;

    public ControladorConversor(VistaConversor vista, VistaHistorial historial, Conversor conversor) {
        if (vista == null || historial == null || conversor == null) {
            throw new IllegalArgumentException("La vista y el conversor son obligatorios");
        }
        this.vista = vista;
        this.historial = historial;
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
            this.vista.mostrarResultado(String.format("%.2f USD", this.conversor.aDolares(pesos)));
            this.historial.actualizar();
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
            // historial.actualizar() no esta: la tabla no se entera de los euros
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
            this.historial.actualizar();
        } catch (NumberFormatException ex) {
            this.vista.mostrarError("Ingrese un numero");
        } catch (IllegalArgumentException ex) {
            this.vista.mostrarError(ex.getMessage());
        }
    }
}
