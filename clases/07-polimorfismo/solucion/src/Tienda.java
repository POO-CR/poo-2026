// Archivo: Tienda.java (solucion)
// Una sola lista, un solo bucle por operacion, y ningun nombre de subclase.

import java.util.ArrayList;

public class Tienda {

    public static void main(String[] args) {

        // Un unico catalogo, con los cuatro tipos de producto mezclados.
        ArrayList<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Libro("LIB-001", "Java How to Program", 50000, "Deitel", "978-0134743356"));
        catalogo.add(new Libro("LIB-002", "El Eternauta", 32000, "Oesterheld", "978-9500742948"));
        catalogo.add(new Electronico("ELE-001", "Notebook 14 pulgadas", 1750000, "Asus", 24));
        catalogo.add(new Alimento("ALI-001", "Yerba organica 1kg", 8500, "2027-03-01"));
        catalogo.add(new Alimento("ALI-002", "Cafe en grano 500g", 15200, "2026-11-15"));
        catalogo.add(new Indumentaria("IND-001", "Buzo de egresados", 62000, "L", "azul"));

        System.out.println("--- Catalogo de la tienda ---");
        mostrarCatalogo(catalogo);

        System.out.println();
        System.out.println("Productos en catalogo: " + contarProductos(catalogo));
        System.out.println("Valor total del stock: $" + valorTotal(catalogo));

        System.out.println();
        System.out.println("--- El mas caro ---");
        masCaro(catalogo).mostrarDetalle();
    }

    // Ni un if, ni un nombre de subclase. La version de mostrarDetalle() que
    // corre la elige cada objeto, no este metodo.
    public static void mostrarCatalogo(ArrayList<Producto> catalogo) {
        for (Producto producto : catalogo) {
            System.out.println();
            producto.mostrarDetalle();
        }
    }

    public static int contarProductos(ArrayList<Producto> catalogo) {
        return catalogo.size();
    }

    public static double valorTotal(ArrayList<Producto> catalogo) {
        double total = 0;
        for (Producto producto : catalogo) {
            total += producto.getPrecioFinal();
        }
        return total;
    }

    // Devuelve un Producto: quien llama no necesita saber de que tipo es para
    // pedirle el detalle.
    public static Producto masCaro(ArrayList<Producto> catalogo) {
        Producto mayor = catalogo.get(0);
        for (Producto producto : catalogo) {
            if (producto.getPrecioFinal() > mayor.getPrecioFinal()) {
                mayor = producto;
            }
        }
        return mayor;
    }
}
