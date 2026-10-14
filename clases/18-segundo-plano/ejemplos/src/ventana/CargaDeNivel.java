// Archivo: CargaDeNivel.java
// El modelo: cargar un nivel tarda, en diez etapas de 300 ms. No sabe nada
// de ventanas ni de hilos.
package ventana;

public class CargaDeNivel {

    private static final String[] ETAPAS = {
        "enemigos", "enemigos", "enemigos", "mapa", "mapa",
        "mapa", "mapa", "sonidos", "sonidos", "sonidos"
    };

    public int getEtapas() {
        return ETAPAS.length;
    }

    // Carga una etapa y devuelve que se cargo.
    public String cargar(int etapa) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return ETAPAS[etapa];
    }
}
