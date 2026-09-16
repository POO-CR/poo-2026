// Archivo: Publicacion.java
// Primera version del catalogo de la biblioteca. Una sola clase representa
// cualquier publicacion, y el atributo tipo dice cual es.
//
// TODO 1: convertir esta clase en abstracta, sin tipo, paginas ni numero.
//         Se queda con titulo, autor y anio, sus getters y un mostrarDetalle()
//         concreto con la parte comun. prestar(), devolver(), multaPorRetraso()
//         y exportarJson() se van de aca.
// TODO 2: escribir Libro.java (con paginas) y Revista.java (con numero), las
//         dos extends Publicacion. Cada una sobrescribe mostrarDetalle()
//         llamando a super.mostrarDetalle() y agregando lo suyo.
// TODO 3: escribir la interfaz Prestable.java con prestar(String socio),
//         devolver() y multaPorRetraso(int diasDeRetraso). La implementa
//         Libro, con el atributo prestadoA adentro. Revista no la implementa.

public class Publicacion {

    private static final double MULTA_LIBRO_POR_DIA = 500;

    private final String tipo;      // "Libro" o "Revista"
    private final String titulo;
    private final String autor;
    private final int anio;
    private final int paginas;      // solo tiene sentido para los libros
    private final int numero;       // solo tiene sentido para las revistas
    private String prestadoA;       // null si esta en la biblioteca

    public Publicacion(String tipo, String titulo, String autor, int anio, int paginas, int numero) {
        if (tipo == null || (!tipo.equals("Libro") && !tipo.equals("Revista"))) {
            throw new IllegalArgumentException("El tipo debe ser Libro o Revista");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo es obligatorio");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor es obligatorio");
        }
        if (anio < 1450) {
            throw new IllegalArgumentException("El anio no es valido");
        }
        this.tipo = tipo;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.paginas = paginas;
        this.numero = numero;
        this.prestadoA = null;
    }

    public String getTipo() {
        return this.tipo;
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

    // Las revistas no salen de la sala de lectura. Se sabe preguntando por el tipo.
    public boolean prestar(String socio) {
        if (this.tipo.equals("Revista")) {
            return false;
        }
        if (socio == null || socio.isBlank() || this.prestadoA != null) {
            return false;
        }
        this.prestadoA = socio;
        return true;
    }

    public boolean devolver() {
        if (this.prestadoA == null) {
            return false;
        }
        this.prestadoA = null;
        return true;
    }

    // La multa depende del tipo. Cada tipo nuevo que se preste agrega un caso;
    // el return 0 del final es lo que cobra un tipo que todavia no tiene el suyo.
    public double multaPorRetraso(int diasDeRetraso) {
        if (diasDeRetraso <= 0) {
            return 0;
        }
        if (this.tipo.equals("Libro")) {
            return diasDeRetraso * MULTA_LIBRO_POR_DIA;
        }
        if (this.tipo.equals("Revista")) {
            return 0;   // nunca se presta, asi que nunca se atrasa
        }
        return 0;
    }

    public void mostrarDetalle() {
        System.out.println(this.tipo + ": " + this.titulo + " (" + this.anio + ")");
        System.out.println("Autor: " + this.autor);
        if (this.tipo.equals("Libro")) {
            System.out.println("Paginas: " + this.paginas);
        }
        if (this.tipo.equals("Revista")) {
            System.out.println("Numero: " + this.numero);
        }
        if (this.prestadoA != null) {
            System.out.println("Prestado a: " + this.prestadoA);
        }
    }

    // El formato en que se exporta el catalogo, escrito adentro de la publicacion.
    public String exportarJson() {
        return "{\"tipo\": \"" + this.tipo + "\", \"titulo\": \"" + this.titulo
                + "\", \"autor\": \"" + this.autor + "\", \"anio\": " + this.anio + "}";
    }
}
