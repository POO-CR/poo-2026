// Archivo: Curso.java
// Primer borrador. Inscribe a cualquiera, siempre.

import java.util.ArrayList;

public class Curso {

    private String nombreCurso;
    private int cupoMaximo;

    // Un curso guarda referencias a sus estudiantes inscriptos: colaboracion.
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

    // Version borrador: agrega al estudiante sin revisar nada.
    public boolean inscribirEstudiante(Estudiante unEstudiante) {
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

    // TODO 4: inscribirEstudiante tiene que hacer cumplir la promesa del curso.
    //         Rechaza (devolviendo false, sin imprimir nada) cuando:
    //           a) unEstudiante es null;
    //           b) el estudiante ya esta inscripto en este curso
    //              (this.inscriptos.contains(unEstudiante) lo detecta:
    //              compara referencias, es decir, pregunta si la lista ya
    //              guarda a ESE MISMO objeto);
    //           c) no queda cupo disponible.
    //         Si pasa las tres revisiones, lo agrega y devuelve true.
    //         Quien llama es el que decide como avisar por pantalla.
}
