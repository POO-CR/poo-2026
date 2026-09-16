// Archivo: Libro.java (solucion)
// Es una Publicacion y se presta.

public class Libro extends Publicacion implements Prestable {

    private static final double MULTA_POR_DIA = 500;

    private final int paginas;
    private String prestadoA;   // null si esta en la biblioteca

    public Libro(String titulo, String autor, int anio, int paginas) {
        super(titulo, autor, anio);
        if (paginas <= 0) {
            throw new IllegalArgumentException("Las paginas deben ser mas de cero");
        }
        this.paginas = paginas;
        this.prestadoA = null;
    }

    @Override
    public boolean prestar(String socio) {
        if (socio == null || socio.isBlank() || this.prestadoA != null) {
            return false;
        }
        this.prestadoA = socio;
        return true;
    }

    @Override
    public boolean devolver() {
        if (this.prestadoA == null) {
            return false;
        }
        this.prestadoA = null;
        return true;
    }

    @Override
    public double multaPorRetraso(int diasDeRetraso) {
        if (diasDeRetraso <= 0) {
            return 0;
        }
        return diasDeRetraso * MULTA_POR_DIA;
    }

    @Override
    public void mostrarDetalle() {
        System.out.print("Libro: ");
        super.mostrarDetalle();
        System.out.println("Paginas: " + this.paginas);
        if (this.prestadoA != null) {
            System.out.println("Prestado a: " + this.prestadoA);
        }
    }
}
