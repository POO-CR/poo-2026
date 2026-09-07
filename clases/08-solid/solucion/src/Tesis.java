// Archivo: Tesis.java (solucion)
// La publicacion nueva de la parte 3. Entro al catalogo, se presto y se
// exporto sin tocar Biblioteca ni ningun exportador.

public class Tesis extends Publicacion implements Prestable {

    private static final double MULTA_POR_DIA = 800;

    private final String universidad;
    private String prestadoA;

    public Tesis(String titulo, String autor, int anio, String universidad) {
        super(titulo, autor, anio);
        if (universidad == null || universidad.isBlank()) {
            throw new IllegalArgumentException("La universidad es obligatoria");
        }
        this.universidad = universidad;
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
        System.out.print("Tesis: ");
        super.mostrarDetalle();
        System.out.println("Universidad: " + this.universidad);
        if (this.prestadoA != null) {
            System.out.println("Prestado a: " + this.prestadoA);
        }
    }
}
