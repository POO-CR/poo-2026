package modelo;

public interface GestorInventario {

    boolean guardar(Item item);
    void drop(Item item);
    Item buscar(String nombre);

}
