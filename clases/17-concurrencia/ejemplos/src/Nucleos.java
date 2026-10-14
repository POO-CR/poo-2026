// Archivo: Nucleos.java
// Cuantos nucleos ve la maquina virtual, y como se llama el hilo del main.

public class Nucleos {
    public static void main(String[] args) {
        System.out.println("Nucleos: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Hilo actual: " + Thread.currentThread().getName());
    }
}
