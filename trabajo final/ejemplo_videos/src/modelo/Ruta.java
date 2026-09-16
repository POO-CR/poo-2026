package modelo;

public class Ruta {
    private final Ubicacion destino;
    private final TipoRuta tipoRuta;
    private final Simbolo costeEspecial;

    public Ruta(Ubicacion destino, TipoRuta tipoRuta, Simbolo costeEspecial) {
        this.destino = destino;
        this.tipoRuta = tipoRuta;
        this.costeEspecial = costeEspecial;
    }

    public Ubicacion getDestino() { return destino; }
    public TipoRuta getTipoRuta() { return tipoRuta; }
    public Simbolo getCosteEspecial() { return costeEspecial; }
}
