package modelo;

public class Item {

    private String nombre;
    private Double valor;
    private String descripcion;

    public Item(String nombre, Double valor, String descripcion){
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getNombre(){
        return this.nombre;
    }

}
