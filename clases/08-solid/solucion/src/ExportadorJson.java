// Archivo: ExportadorJson.java (solucion)
// El formato que antes vivia adentro de Publicacion, ahora en su propia clase.
// Si el JSON cambia, se toca este archivo y ningun otro.

public class ExportadorJson implements Exportador {

    @Override
    public String exportar(Publicacion publicacion) {
        return "{\"titulo\": \"" + publicacion.getTitulo()
                + "\", \"autor\": \"" + publicacion.getAutor()
                + "\", \"anio\": " + publicacion.getAnio() + "}";
    }
}
