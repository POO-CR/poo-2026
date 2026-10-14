// Archivo: Programa.java
// Arma la carga de nivel: modelo, vista y controlador.
package ventana;

import javax.swing.SwingUtilities;

public class Programa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaCarga vista = new VistaCarga();
            new ControladorCarga(vista, new CargaDeNivel());
            vista.mostrar();
        });
    }
}
