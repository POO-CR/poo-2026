import javax.swing.SwingUtilities;

import controlador.PrincipalControlador;

public class App {
    public static void main(String[] args) throws Exception {
    	SwingUtilities.invokeLater(() -> {
    		new PrincipalControlador();
        });
    }
}
