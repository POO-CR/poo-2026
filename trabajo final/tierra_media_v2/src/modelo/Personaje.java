package modelo;

public abstract class Personaje {
	
	private Integer id;
	private String nombre;
	private Bando bando;
	private Integer saludMax;
	private Integer saludActual;
	private Integer ataqueBase;
	private Integer defensaBase;
	private Integer velocidad;
    
    public Personaje(Integer id, String nombre, Bando bando, Integer saludMax, Integer ataqueBase, Integer defensaBase, Integer velocidad) {
    	this.id = id;
        this.nombre = nombre;
        this.bando = bando;
        this.saludMax = saludMax;
        this.saludActual = saludMax;
        this.ataqueBase = ataqueBase;
        this.defensaBase = defensaBase;
        this.velocidad = velocidad;
    }
    
    //TODO: Falta hacer los set para la salud y la vida.

    public void recibirDanio(int danioBruto) {
        int danioNeto = Math.max(1, danioBruto - (this.defensaBase / 2));
        this.saludActual = Math.max(0, this.saludActual - danioNeto);
    }

    public boolean estaVivo() {
        return this.saludActual > 0;
    }

    public abstract int calcularAtaque();
    public abstract String ejecutarHabilidadEspecial(Personaje objetivo);

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public Bando getBando() { return bando; }
    public Integer getSaludMax() { return saludMax; }
    public Integer getSaludActual() { return saludActual; }
    public Integer getAtaqueBase() { return ataqueBase; }
    public Integer getDefensaBase() { return defensaBase; }
    public Integer getVelocidad() { return velocidad; }
    
}
