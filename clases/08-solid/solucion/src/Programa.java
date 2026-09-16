// Archivo: Programa.java (solucion)
// El bloque de la parte 2 con las lineas de la parte 3 descomentadas.

public class Programa {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca(new ExportadorCsv());

        Libro libro = new Libro("Java How to Program", "Deitel", 2017, 1200);
        Revista revista = new Revista("Investigacion y Ciencia", "Varios", 2024, 570);
        Tesis tesis = new Tesis("Deteccion de fraude con grafos", "M. Paz", 2023, "UNPSJB");
        biblioteca.agregar(libro);
        biblioteca.agregar(revista);
        biblioteca.agregar(tesis);

        System.out.println("--- Catalogo (" + biblioteca.cantidad() + " publicaciones) ---");
        biblioteca.mostrarCatalogo();

        System.out.println();
        System.out.println("--- Prestamos ---");
        System.out.println("Prestar el libro a Ana: " + libro.prestar("Ana"));
        System.out.println("Multa del libro con 3 dias de retraso: $" + libro.multaPorRetraso(3));
        System.out.println("Prestar la tesis a Ana: " + tesis.prestar("Ana"));
        System.out.println("Multa de la tesis con 2 dias de retraso: $" + tesis.multaPorRetraso(2));
        // revista.prestar("Ana");   // no compila: Revista no es Prestable

        System.out.println();
        System.out.println("--- Exportacion ---");
        System.out.print(biblioteca.exportarCatalogo());
    }
}
