// Archivo: Estudiante.java
// Solucion de la actividad de la clase 1.
//
// NOTA AL PIE (importante): los atributos quedan expuestos y cualquier parte
// del programa puede escribir cualquier cosa en ellos. En esta clase todavia
// no tenemos herramientas para impedirlo: este codigo esta "mal" a proposito,
// y lo retomamos al principio de la unidad 2.

public class Estudiante {

    // --- ATRIBUTOS ---
    // Caracteristicas que el sistema necesita conocer de un estudiante.
    // El modelo se recorta al contexto del problema: no guardamos color de
    // ojos ni comida favorita, porque este sistema no los necesita.
    public String nombreCompleto;
    public int numeroLegajo;
    public String carrera;
    public int anioIngreso;

    // --- METODOS ---
    // Comportamientos: lo que un estudiante sabe hacer dentro del sistema.
    // Por ahora solo imprimen un mensaje; la implementacion real llega despues.

    public void mostrarInformacion() {
        System.out.println("Estudiante: " + nombreCompleto +
                " - Legajo: " + numeroLegajo +
                " - " + carrera + " (ingreso " + anioIngreso + ")");
    }

    public void inscribirseACursada(String materia) {
        System.out.println(nombreCompleto + " se inscribio a la cursada de " + materia + ".");
    }

    public void rendirFinal(String materia) {
        System.out.println(nombreCompleto + " rindio el final de " + materia + ".");
    }
}
