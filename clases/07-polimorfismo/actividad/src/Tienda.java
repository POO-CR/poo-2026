// Archivo: Tienda.java
// El programa del local. Es el unico archivo que se modifica en las partes 2 y
// 3; las clases del catalogo llegan intactas desde la clase pasada.

import java.util.ArrayList;

public class Tienda {

    public static void main(String[] args) {

        // El catalogo, guardado en una lista por cada tipo de producto.
        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(new Libro("LIB-001", "Java How to Program", 50000, "Deitel", "978-0134743356"));
        libros.add(new Libro("LIB-002", "El Eternauta", 32000, "Oesterheld", "978-9500742948"));

        ArrayList<Electronico> electronicos = new ArrayList<>();
        electronicos.add(new Electronico("ELE-001", "Notebook 14 pulgadas", 1750000, "Asus", 24));

        ArrayList<Alimento> alimentos = new ArrayList<>();
        alimentos.add(new Alimento("ALI-001", "Yerba organica 1kg", 8500, "2027-03-01"));
        alimentos.add(new Alimento("ALI-002", "Cafe en grano 500g", 15200, "2026-11-15"));

        System.out.println("--- Catalogo de la tienda ---");
        mostrarCatalogo(libros, electronicos, alimentos);

        System.out.println();
        System.out.println("Productos en catalogo: " + contarProductos(libros, electronicos, alimentos));
        System.out.println("Valor total del stock: $" + valorTotal(libros, electronicos, alimentos));

        // TODO 4: imprimir el detalle del producto mas caro, usando masCaro().
    }

    // TODO 1: reemplazar los tres parametros por un unico
    //         ArrayList<Producto> catalogo, y los tres bucles por uno solo.
    //         El metodo mostrarDetalle() lo declara Producto, asi que se le
    //         puede pedir a cualquiera.
    public static void mostrarCatalogo(ArrayList<Libro> libros, ArrayList<Electronico> electronicos, ArrayList<Alimento> alimentos) {
        for (Libro producto : libros) {
            System.out.println();
            producto.mostrarDetalle();
        }
        for (Electronico producto : electronicos) {
            System.out.println();
            producto.mostrarDetalle();
        }
        for (Alimento producto : alimentos) {
            System.out.println();
            producto.mostrarDetalle();
        }
    }

    // TODO 2: lo mismo. Con una sola lista, este metodo es una linea.
    public static int contarProductos(ArrayList<Libro> libros, ArrayList<Electronico> electronicos, ArrayList<Alimento> alimentos) {
        return libros.size() + electronicos.size() + alimentos.size();
    }

    // TODO 3: lo mismo, con un solo bucle que acumule getPrecioFinal().
    public static double valorTotal(ArrayList<Libro> libros, ArrayList<Electronico> electronicos, ArrayList<Alimento> alimentos) {
        double total = 0;
        for (Libro producto : libros) {
            total += producto.getPrecioFinal();
        }
        for (Electronico producto : electronicos) {
            total += producto.getPrecioFinal();
        }
        for (Alimento producto : alimentos) {
            total += producto.getPrecioFinal();
        }
        return total;
    }

    // TODO 4: escribir masCaro(ArrayList<Producto> catalogo), que devuelve el
    //         Producto de mayor precio final. Devuelve un Producto, no un
    //         Libro ni un Electronico: quien llama no necesita saber cual es.
}
