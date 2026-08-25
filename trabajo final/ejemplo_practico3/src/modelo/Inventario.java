package modelo;

import java.util.ArrayList;

public class Inventario implements GestorInventario{
    
    //private HashMap<Integer,Item> inventario = new HashMap<>();
    private ArrayList<Item> items = new ArrayList<>();


    @Override
    public boolean guardar(Item item){
        return this.items.add(item);
    }
    
    @Override
    public Item buscar(String nombre){
        for (Item item : items) {
            if(item.getNombre().equals(nombre)){
                return item;
            }
        }

        return null;
    }

    @Override
    public void drop(Item item){
        this.items.remove(item);
    }
}
