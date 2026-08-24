// Archivo: Tienda.java
// El programa que muestra el catalogo.

public class Tienda {

    // TODO 11: escribir el metodo que liquida. Recibe un arreglo de Descontable
    //          y un porcentaje, le aplica el descuento a cada uno e imprime el
    //          precio que queda. No sabe, ni le importa, si adentro hay libros,
    //          alimentos o suscripciones. Firma sugerida:
    //
    //          public static void liquidar(Descontable[] items, int porcentaje)

    public static void main(String[] args) {
        System.out.println("--- Catalogo de la tienda ---");

        Libro libro = new Libro("LIB-001", "Java How to Program", 50000, "Deitel", "978-0134743356");
        Electronico notebook = new Electronico("ELE-001", "Notebook 14 pulgadas", 1750000, "Asus", 24);
        Alimento yerba = new Alimento("ALI-001", "Yerba organica 1kg", 8500, "2027-03-01");

        System.out.println();
        libro.mostrarDetalle();

        System.out.println();
        notebook.mostrarDetalle();

        System.out.println();
        yerba.mostrarDetalle();

        // TODO 6: esta linea es el primero de los dos problemas de la parte 1.
        //         Borrarla cuando Producto pase a ser abstracta.
        Producto varios = new Producto("VAR-001", "Articulo de bazar", 1200);

        System.out.println();
        varios.mostrarDetalle();

        // ----- Parte 3: la liquidacion del mes -----
        // Descomentar cuando Descontable.java exista y las clases lo implementen.

        /*
        Suscripcion club = new Suscripcion("Club de lectura mensual", 6000);

        System.out.println();
        System.out.println("--- Liquidacion del mes: 30% ---");
        Descontable[] enLiquidacion = { libro, yerba, club };
        liquidar(enLiquidacion, 30);
        */
    }
}
