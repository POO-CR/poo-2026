package modelo;

public enum Dificultad {
    INTRODUCTORIA("Introductoria", 4, 4),
    ESTANDAR("Estándar", 5, 4),
    HEROICA("Heroica", 5, 5),
    EPICA("Épica", 6, 5),
    LEGENDARIA("Legendaria", 6, 6);

    private final String nombre;
    private final int cartasCieloOscurece;
    private final int cantidadObjetivos;

    Dificultad(String nombre, int cartasCieloOscurece, int cantidadObjetivos) {
        this.nombre = nombre;
        this.cartasCieloOscurece = cartasCieloOscurece;
        this.cantidadObjetivos = cantidadObjetivos;
    }

    public String getNombre() { return nombre; }
    public int getCartasCieloOscurece() { return cartasCieloOscurece; }
    public int getCantidadObjetivos() { return cantidadObjetivos; }

    @Override
    public String toString() { return nombre; }
}
