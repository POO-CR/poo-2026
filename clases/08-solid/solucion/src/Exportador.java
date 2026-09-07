// Archivo: Exportador.java (solucion)
// La interfaz la define lo que Biblioteca necesita: una linea de texto por
// publicacion. Que formato tiene esa linea es asunto de quien lo implementa.

public interface Exportador {

    String exportar(Publicacion publicacion);
}
