// Archivo: Jugador.java
// Un jugador recorre la oleada y golpea a cada enemigo varias veces. Cada
// golpe suma un punto, y si el golpe mata al enemigo, anota la baja.
package modelo;

import java.util.List;

public class Jugador implements Runnable {

    private final String nombre;
    private final List<Enemigo> oleada;
    private final int golpesPorEnemigo;
    private final Marcador marcador;
    private final Bitacora bitacora;

    public Jugador(String nombre, List<Enemigo> oleada, int golpesPorEnemigo, Marcador marcador, Bitacora bitacora) {
        this.nombre = nombre;
        this.oleada = oleada;
        this.golpesPorEnemigo = golpesPorEnemigo;
        this.marcador = marcador;
        this.bitacora = bitacora;
    }

    @Override
    public void run() {
        int danio = Configuracion.getInstancia().getDanio();
        for (Enemigo enemigo : this.oleada) {
            for (int i = 0; i < this.golpesPorEnemigo; i++) {
                this.marcador.sumar(1);
                if (enemigo.recibirDanio(danio)) {
                    this.bitacora.registrarBaja(this.nombre, enemigo.getNombre());
                }
            }
        }
    }
}
