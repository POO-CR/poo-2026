package modelo;

public abstract class Personaje {
    private Integer id;
    private String nombre;
    private String ubicacionInicial;
    private String ubicacionActual;
    private String colorTropaGratis;
    private String rutaImagen;

    public Personaje(Integer id, String nombre, String ubicacionInicial, String colorTropaGratis) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacionInicial = ubicacionInicial;
        this.ubicacionActual = ubicacionInicial;
        this.colorTropaGratis = colorTropaGratis;
    }

    public boolean puedeReclutar() { return true; }
    public boolean puedeAtacar() { return true; }
    public boolean puedeCapturar() { return true; }
    public Integer getCantidadReclutamiento() { return 1; }
    public Integer getRangoViaje() { return 1; }

    public boolean reclutaGratisEn(String colorRegion) {
        return colorTropaGratis != null && colorTropaGratis.equalsIgnoreCase(colorRegion);
    }

    public String getNombre() { return nombre; }
    public String getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(String u) { this.ubicacionActual = u; }
    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
