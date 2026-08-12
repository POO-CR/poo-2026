// Archivo: SistemaGestionAlumnos.java
// Solucion de la actividad de la clase 1.

public class SistemaGestionAlumnos {

    public static void main(String[] args) {

        // --- CREACION DE OBJETOS ---
        // La clase Estudiante es el molde; cada new crea una instancia
        // concreta, con su propio estado.

        Estudiante estudiante1 = new Estudiante();
        estudiante1.nombreCompleto = "Juana Azurduy";
        estudiante1.numeroLegajo = 12345;
        estudiante1.carrera = "Licenciatura en Informatica";
        estudiante1.anioIngreso = 2026;

        Estudiante estudiante2 = new Estudiante();
        estudiante2.nombreCompleto = "Manuel Belgrano";
        estudiante2.numeroLegajo = 67890;
        estudiante2.carrera = "Ingenieria Civil";
        estudiante2.anioIngreso = 2025;

        // --- USO DE OBJETOS ---
        // Los dos objetos comparten el comportamiento definido en la clase,
        // pero cada invocacion trabaja con el estado del objeto que la recibe.

        estudiante1.mostrarInformacion();
        estudiante2.mostrarInformacion();

        estudiante1.inscribirseACursada("Programacion Orientada a Objetos");
        estudiante2.rendirFinal("Analisis Matematico I");
    }
}
