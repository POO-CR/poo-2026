package modelo;

import java.util.Random;

public class SistemaDados {
    public enum ResultadoBatalla { RECHAZAR, TABLAS, ARROLLADOS, NAZGUL }
    public enum ResultadoBusqueda { ESCABULLE, FATIGA, AL_DESCUBIERTO, CONVOCACION }

    private static final Random random = new Random();

    public static ResultadoBatalla tirarDadoBatalla() {
        ResultadoBatalla[] caras = ResultadoBatalla.values();
        return caras[random.nextInt(caras.length)];
    }

    public static ResultadoBusqueda tirarDadoBusqueda() {
        ResultadoBusqueda[] caras = ResultadoBusqueda.values();
        return caras[random.nextInt(caras.length)];
    }
}
