// Archivo: Bitacora.java
// Parte del subsistema de combate. El registro de lo que paso en la batalla.

package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bitacora {

    private final List<String> lineas = new ArrayList<>();

    public boolean anotar(String linea) {
        if (linea == null || linea.isBlank()) {
            return false;
        }
        this.lineas.add(linea);
        return true;
    }

    public List<String> getLineas() {
        return Collections.unmodifiableList(this.lineas);
    }
}
