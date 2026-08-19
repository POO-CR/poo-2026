// Archivo: GestionAcademica.java
// Este archivo no se modifica, salvo para comentar la parte 1 y descomentar la parte 3.

public class GestionAcademica {

    // Recibe una COPIA del valor de numero. Lo que haga aca adentro
    // no puede afectar a la variable del main.
    private static void intentarModificar(int numero) {
        numero = numero * 2;
        System.out.println("Dentro del metodo, numero vale: " + numero);
    }

    public static void main(String[] args) {

        System.out.println("== Sistema de Gestion Academica ==");

        // ------------------------- PARTE 1: el problema -------------------------
        // Ejecutar esto ANTES de tocar Estudiante y Curso, y mirar la salida.

        /*
        Estudiante e1 = new Estudiante("Bernardo", "Houssay");
        Estudiante e2 = new Estudiante("Luis Federico", "Leloir");
        Estudiante e3 = new Estudiante("Cesar", "Milstein");
        Estudiante e4 = new Estudiante("Cecilia", "Grierson");

        System.out.println("Creado: " + e1);
        System.out.println("Creado: " + e2);
        System.out.println("Creado: " + e3);
        System.out.println("Creado: " + e4);

        Curso poo = new Curso("Programacion Orientada a Objetos", 2);

        poo.inscribirEstudiante(e1);
        poo.inscribirEstudiante(e2);
        poo.inscribirEstudiante(e3);
        poo.inscribirEstudiante(e4);

        poo.mostrarInscriptos();
        */

        // ------------------------- PARTE 3: el resultado ------------------------
        // Descomentar despues de arreglar Estudiante y Curso, comentando la parte 1.

        Estudiante e1 = new Estudiante("Bernardo", "Houssay");
        Estudiante e2 = new Estudiante("Luis Federico", "Leloir");
        Estudiante e3 = new Estudiante("Cesar", "Milstein");

        System.out.println("Creado: " + e1);
        System.out.println("Creado: " + e2);
        System.out.println("Creado: " + e3);

        Curso poo = new Curso("Programacion Orientada a Objetos", 2);

        if (poo.inscribirEstudiante(e1)) {
            System.out.println("Inscripcion de " + e1.getApellido() + " ACEPTADA.");
        } else {
            System.out.println("Inscripcion de " + e1.getApellido() + " RECHAZADA.");
        }

        if (poo.inscribirEstudiante(e2)) {
            System.out.println("Inscripcion de " + e2.getApellido() + " ACEPTADA.");
        } else {
            System.out.println("Inscripcion de " + e2.getApellido() + " RECHAZADA.");
        }

        // No queda cupo: esta tiene que ser rechazada.
        if (!poo.inscribirEstudiante(e3)) {
            System.out.println("Inscripcion de " + e3.getApellido() + " RECHAZADA.");
        }

        // Houssay ya esta inscripto: inscribirlo de nuevo tambien tiene que fallar.
        if (!poo.inscribirEstudiante(e1)) {
            System.out.println("Reinscripcion de " + e1.getApellido() + " RECHAZADA.");
        }

        poo.mostrarInscriptos();

        // --- Paso por referencia: el curso guarda a ESE MISMO objeto ---
        // Cambiamos el apellido usando la variable e1 del main...
        e1.cambiarApellido("Houssay (Nobel 1947)");
        // ...y el cambio aparece en el listado del curso, sin tocar el curso.
        poo.mostrarInscriptos();

        // --- Paso por valor: con un primitivo esto no pasa ---
        int cupoLocal = 2;
        intentarModificar(cupoLocal);
        System.out.println("Despues del metodo, cupoLocal vale: " + cupoLocal);

        // --- El contador es de la clase, y se consulta por la clase ---
        System.out.println("Proximo legajo a asignar: " + Estudiante.getProximoLegajo());
        Estudiante e5 = new Estudiante("Rene", "Favaloro");
        System.out.println("Creado: " + e5);

        // Y esta creacion tiene que cortar el programa con un mensaje claro.
        Estudiante roto = new Estudiante("", "Sin Nombre");
        System.out.println("Esta linea no se tendria que ver: " + roto);

    }
}
