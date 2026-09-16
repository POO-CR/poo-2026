// Archivo: ServicioCorreo.java
// El detalle. Aca no se conecta a ningun servidor: imprime lo que mandaria.

package modelo;

public class ServicioCorreo implements Notificador {

    private final String servidor;
    private final int puerto;

    public ServicioCorreo(String servidor, int puerto) {
        this.servidor = servidor;
        this.puerto = puerto;
    }

    @Override
    public boolean enviar(String mensaje) {
        System.out.println("[correo via " + this.servidor + ":" + this.puerto + "] " + mensaje);
        return true;
    }
}
