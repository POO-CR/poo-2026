// Archivo: Tienda.java (solucion)
// La linea del Producto generico ya no existe: desde que Producto es
// abstracta, no compilaba.

public class Tienda {

    // Este metodo no menciona a Libro, ni a Alimento, ni a Suscripcion. Le
    // alcanza con el contrato, y por eso no se toca cuando aparezca la proxima
    // clase que se liquide.
    public static void liquidar(Descontable[] items, int porcentaje) {
        for (Descontable item : items) {
            if (item.aplicarDescuento(porcentaje)) {
                System.out.println("Queda en $" + item.getPrecioConDescuento());
            } else {
                System.out.println("Descuento rechazado: el maximo es "
                        + Descontable.DESCUENTO_MAXIMO + "%");
            }
        }
    }

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

        // ----- Parte 3: la liquidacion del mes -----

        Suscripcion club = new Suscripcion("Club de lectura mensual", 6000);

        System.out.println();
        System.out.println("--- Liquidacion del mes: 30% ---");
        Descontable[] enLiquidacion = { libro, yerba, club };
        liquidar(enLiquidacion, 30);
    }
}
