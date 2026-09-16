// Archivo: Biblioteca.java
// El catalogo. Guarda publicaciones, las lista y las exporta.
//
// TODO 4: escribir la interfaz Exportador.java, con un unico metodo
//         String exportar(Publicacion publicacion), y ExportadorJson.java que
//         la implementa con el formato que hoy esta en Publicacion.exportarJson().
//         Biblioteca pasa a recibir un Exportador por constructor (obligatorio,
//         guardado en un atributo final) y exportarCatalogo() lo usa.

import java.util.ArrayList;

public class Biblioteca {

    private final ArrayList<Publicacion> catalogo;

    public Biblioteca() {
        this.catalogo = new ArrayList<>();
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

    // Una linea por publicacion, en el formato que sabe armar cada una.
    public String exportarCatalogo() {
        String texto = "";
        for (Publicacion publicacion : this.catalogo) {
            texto += publicacion.exportarJson() + "\n";
        }
        return texto;
    }
}
