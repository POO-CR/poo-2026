// Archivo: Programa.java
// Programa de prueba. No se modifica, salvo comentar y descomentar bloques.

public class Programa {

    public static void main(String[] args) {

        // ----- Parte 1: el catalogo tal como viene -----
        // Cuando esten hechos los TODO 1 a 4, comentar este bloque entero y
        // descomentar el de la parte 2.

        Biblioteca biblioteca = new Biblioteca();

        Publicacion libro = new Publicacion("Libro", "Java How to Program", "Deitel", 2017, 1200, 0);
        Publicacion revista = new Publicacion("Revista", "Investigacion y Ciencia", "Varios", 2024, 0, 570);
        biblioteca.agregar(libro);
        biblioteca.agregar(revista);

        System.out.println("--- Catalogo (" + biblioteca.cantidad() + " publicaciones) ---");
        biblioteca.mostrarCatalogo();

        System.out.println();
        System.out.println("--- Prestamos ---");
        System.out.println("Prestar el libro a Ana: " + libro.prestar("Ana"));
        System.out.println("Prestar la revista a Ana: " + revista.prestar("Ana"));
        System.out.println("Multa del libro con 3 dias de retraso: $" + libro.multaPorRetraso(3));
        System.out.println("Multa de la revista con 3 dias de retraso: $" + revista.multaPorRetraso(3));

        System.out.println();
        System.out.println("--- Exportacion ---");
        System.out.print(biblioteca.exportarCatalogo());

        // ----- Parte 2: el catalogo refactorizado -----
        // Descomentar el bloque (sacar el /* y el */). Las lineas que empiezan
        // con // adentro del bloque quedan comentadas hasta la parte 3.
        /*
        Biblioteca biblioteca = new Biblioteca(new ExportadorJson());
        // TODO 6: cambiar la linea de arriba por esta
        // Biblioteca biblioteca = new Biblioteca(new ExportadorCsv());

        Libro libro = new Libro("Java How to Program", "Deitel", 2017, 1200);
        Revista revista = new Revista("Investigacion y Ciencia", "Varios", 2024, 570);
        biblioteca.agregar(libro);
        biblioteca.agregar(revista);
        // TODO 5: descomentar las dos lineas siguientes
        // Tesis tesis = new Tesis("Deteccion de fraude con grafos", "M. Paz", 2023, "UNPSJB");
        // biblioteca.agregar(tesis);

        System.out.println("--- Catalogo (" + biblioteca.cantidad() + " publicaciones) ---");
        biblioteca.mostrarCatalogo();

        System.out.println();
        System.out.println("--- Prestamos ---");
        System.out.println("Prestar el libro a Ana: " + libro.prestar("Ana"));
        System.out.println("Multa del libro con 3 dias de retraso: $" + libro.multaPorRetraso(3));
        // TODO 5: descomentar las dos lineas siguientes
        // System.out.println("Prestar la tesis a Ana: " + tesis.prestar("Ana"));
        // System.out.println("Multa de la tesis con 2 dias de retraso: $" + tesis.multaPorRetraso(2));
        // revista.prestar("Ana");   // ya no compila: Revista no es Prestable

        System.out.println();
        System.out.println("--- Exportacion ---");
        System.out.print(biblioteca.exportarCatalogo());
        */
    }
}
