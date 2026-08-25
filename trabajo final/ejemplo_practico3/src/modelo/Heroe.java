package modelo;

public class Heroe extends Personaje implements GestorInventario{

    private Inventario inventario;

    public Heroe(String nombre){
        super(nombre);
        this.inventario = new Inventario();
    }
    
    @Override
    public boolean guardar(Item item){
        return this.inventario.guardar(item);
    }
    
    @Override
    public Item buscar(String nombre){
        return this.inventario.buscar(nombre);
    }

    @Override
    public void drop(Item item){
        this.inventario.drop(item);
    }

    @Override
    public Integer atacar(){
        return super.atacar() * 2;
    }
}
