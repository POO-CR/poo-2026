// Archivo: Publicacion.java (solucion)
// Abstracta: lo comun a toda publicacion, sin atributo tipo. Que es cada una
// lo dice su clase, no un String.

public abstract class Publicacion {

    private final String titulo;
    private final String autor;
    private final int anio;

    public Publicacion(String titulo, String autor, int anio) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (anio < 1450) {
            throw new IllegalArgumentException("El anio no es valido");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAnio() {
        return this.anio;
    }

    // Concreto: la parte del detalle que es igual para todas. Cada subclase
    // lo sobrescribe y agrega lo suyo.
    public void mostrarDetalle() {
        System.out.println(this.titulo + " (" + this.anio + ")");
        System.out.println("Autor: " + this.autor);
    }
}
