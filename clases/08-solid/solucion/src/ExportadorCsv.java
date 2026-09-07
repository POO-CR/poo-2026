// Archivo: ExportadorCsv.java (solucion)
// El formato nuevo de la parte 3. Biblioteca no se entero de que existe.

public class ExportadorCsv implements Exportador {

    @Override
    public String exportar(Publicacion publicacion) {
        return publicacion.getTitulo() + ";" + publicacion.getAutor() + ";" + publicacion.getAnio();
    }
}
