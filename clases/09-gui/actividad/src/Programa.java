// Archivo: Programa.java
// Programa de prueba. No se modifica, salvo comentar y descomentar bloques.

// TODO 4: descomentar los tres import junto con el bloque de la parte 2.
// import controlador.ControladorConversor;
// import modelo.Conversor;
// import vista.VistaConversor;

public class Programa {

    public static void main(String[] args) {

        // ----- Parte 1: el conversor tal como viene -----
        // Cuando esten hechos los TODO 1 a 3, comentar este bloque y
        // descomentar el de la parte 2.

        VentanaConversor ventana = new VentanaConversor();
        ventana.setVisible(true);

        // ----- Parte 2: el conversor repartido en tres clases -----
        // Descomentar el bloque (sacar el /* y el */).
        /*
        Conversor conversor = new Conversor(1450.0);
        VistaConversor vista = new VistaConversor();
        ControladorConversor controlador = new ControladorConversor(vista, conversor);
        vista.mostrar();
        */

        // ----- Parte 3a: el modelo sin pantalla -----
        // Descomentar este bloque con el de la parte 2 comentado. No abre
        // ninguna ventana.
        /*
        Conversor conversor = new Conversor(1450.0);
        System.out.println("1450 pesos son " + conversor.aDolares(1450) + " dolares");
        System.out.println("725 pesos son " + conversor.aDolares(725) + " dolares");
        System.out.println("0 pesos son " + conversor.aDolares(0) + " dolares");
        */

        // ----- Parte 3b: dolares y euros -----
        // Descomentar este bloque con los anteriores comentados.
        /*
        Conversor conversor = new Conversor(1450.0, 1620.0);
        VistaConversor vista = new VistaConversor();
        ControladorConversor controlador = new ControladorConversor(vista, conversor);
        vista.mostrar();
        */
    }
}
