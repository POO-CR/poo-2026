// Archivo: ObservadorCaja.java
// Lo que la Caja necesita de quien quiera enterarse de sus cambios: un solo
// metodo, que la Caja llama despues de cada cambio. Esta en el paquete modelo
// porque la define el modelo; las vistas la implementan.

package modelo;

public interface ObservadorCaja {
    void cajaCambio(Caja caja);
}
