// Archivo: Curso.java
// Solucion de la actividad de la clase 4.

import java.util.ArrayList;

public class Curso {

    private String nombreCurso;
    private int cupoMaximo;
    private ArrayList<Estudiante> inscriptos;

    public Curso(String nombreCurso, int cupoMaximo) {
        if (nombreCurso == null || nombreCurso.isEmpty()) {
            throw new IllegalArgumentException("El curso necesita un nombre.");
        }
        if (cupoMaximo <= 0) {
            throw new IllegalArgumentException("El cupo maximo debe ser mayor a cero.");
        }
        this.nombreCurso = nombreCurso;
        this.cupoMaximo = cupoMaximo;
        this.inscriptos = new ArrayList<Estudiante>();
    }

    // TODO 4 resuelto: el curso hace cumplir su propia promesa.
    // No imprime nada: rechaza con false y quien llama decide como avisar.
    public boolean inscribirEstudiante(Estudiante unEstudiante) {
        if (unEstudiante == null) {
            return false;
        }
        // contains compara referencias: pregunta si la lista ya guarda
        // a ESE MISMO objeto, no a uno parecido.
        if (this.inscriptos.contains(unEstudiante)) {
            return false;
        }
        if (this.inscriptos.size() >= this.cupoMaximo) {
            return false;
        }
        this.inscriptos.add(unEstudiante);
        return true;
    }

    public void mostrarInscriptos() {
        System.out.println("-----------------------------------------");
        System.out.println("Curso: " + this.nombreCurso);
        System.out.println("Cupo: " + this.inscriptos.size() + "/" + this.cupoMaximo);
        System.out.println("-----------------------------------------");
        for (Estudiante e : this.inscriptos) {
            System.out.println(e.toString());
        }
        System.out.println("-----------------------------------------");
    }
}
