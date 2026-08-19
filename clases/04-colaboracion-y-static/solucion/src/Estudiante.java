// Archivo: Estudiante.java
// Solucion de la actividad de la clase 4.

public class Estudiante {

    // TODO 1 resuelto: static. Una unica copia, compartida por todos los
    // estudiantes, que vive en la clase y no en cada objeto.
    private static int proximoLegajo = 10001;

    // El legajo identifica al estudiante de por vida: final, como el cvu
    // de la billetera de la clase pasada.
    private final int legajo;
    private String nombre;
    private String apellido;

    public Estudiante(String nombre, String apellido) {
        // TODO 2 resuelto: el objeto se niega a nacer invalido.
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacio.");
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = proximoLegajo;
        proximoLegajo++;
    }

    // TODO 3 resuelto: el proximo legajo no es de ningun estudiante, es de la
    // clase, asi que el metodo es static y se invoca Estudiante.getProximoLegajo().
    // Adentro no existe this: no hay ningun objeto sobre el que estar parado.
    public static int getProximoLegajo() {
        return proximoLegajo;
    }

    public int getLegajo() {
        return this.legajo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public boolean cambiarApellido(String nuevoApellido) {
        if (nuevoApellido == null || nuevoApellido.isEmpty()) {
            return false;
        }
        this.apellido = nuevoApellido;
        return true;
    }

    @Override
    public String toString() {
        return "Estudiante [Legajo: " + legajo + ", " + apellido + ", " + nombre + "]";
    }
}
