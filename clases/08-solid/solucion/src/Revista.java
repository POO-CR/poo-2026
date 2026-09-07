// Archivo: Revista.java (solucion)
// Es una Publicacion y nada mas. No implementa Prestable, asi que
// revista.prestar(...) no compila: la regla la verifica el compilador, no un if.

public class Revista extends Publicacion {

    private final int numero;

    public Revista(String titulo, String autor, int anio, int numero) {
        super(titulo, autor, anio);
        if (numero <= 0) {
            throw new IllegalArgumentException("El numero debe ser mayor a cero");
        }
        this.numero = numero;
    }

    @Override
    public void mostrarDetalle() {
        System.out.print("Revista: ");
        super.mostrarDetalle();
        System.out.println("Numero: " + this.numero);
    }
}
