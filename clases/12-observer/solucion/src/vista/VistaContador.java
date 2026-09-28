// Archivo: VistaContador.java (solucion)
// La tercera vista, del TODO 5: cuantas conversiones se hicieron y cuantos
// pesos se convirtieron. Entra sin tocar el controlador: se registra como
// observadora en Programa.

package vista;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.Conversion;
import modelo.Conversor;
import modelo.ObservadorConversor;

public class VistaContador implements ObservadorConversor {

    private final JFrame ventana;
    private final JLabel etiqueta;

    public VistaContador() {
        this.ventana = new JFrame("Hoy");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.etiqueta = new JLabel("0 conversiones, $0.00");
        this.etiqueta.setFont(this.etiqueta.getFont().deriveFont(Font.BOLD, 18f));
        this.etiqueta.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        this.ventana.add(this.etiqueta);
        this.ventana.pack();
        this.ventana.setLocation(80, 320);
    }

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    @Override
    public void conversorCambio(Conversor conversor) {
        double pesos = 0;
        for (Conversion conversion : conversor.getHistorial()) {
            pesos += conversion.getPesos();
        }
        this.etiqueta.setText(String.format("%d conversiones, $%.2f", conversor.getHistorial().size(), pesos));
    }
}
