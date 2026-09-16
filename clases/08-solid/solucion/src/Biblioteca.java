// Archivo: Biblioteca.java (solucion)
// Depende de Publicacion y de Exportador, los dos abstractos. No nombra a
// Libro, ni a Revista, ni a ningun formato. El exportador concreto se lo
// entrega quien la construye.

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private final List<Publicacion> catalogo;
    private final Exportador exportador;

    public Biblioteca(Exportador exportador) {
        if (exportador == null) {
            throw new IllegalArgumentException("El exportador es obligatorio");
        }
        this.catalogo = new ArrayList<>();
        this.exportador = exportador;
    }

    public boolean agregar(Publicacion publicacion) {
        if (publicacion == null) {
            return false;
        }
        this.catalogo.add(publicacion);
        return true;
    }

    public int cantidad() {
        return this.catalogo.size();
    }

    public void mostrarCatalogo() {
        for (Publicacion publicacion : this.catalogo) {
            System.out.println();
            publicacion.mostrarDetalle();
        }
    }

    // Una linea por publicacion. El formato lo decide el exportador recibido.
    public String exportarCatalogo() {
        String texto = "";
        for (Publicacion publicacion : this.catalogo) {
            texto += this.exportador.exportar(publicacion) + "\n";
        }
        return texto;
    }
}
