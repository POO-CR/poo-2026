// Archivo: Estudiante.java
// Primer borrador. Compila y corre, pero el sistema de legajos no funciona.

public class Estudiante {

    // El proximo legajo a asignar. Declarado asi, cada estudiante nace con
    // SU PROPIA copia de este contador. Aca esta el error de esta clase,
    // aunque el codigo compile y corra sin protestar.
    private int proximoLegajo = 10001;

    private int legajo;
    private String nombre;
    private String apellido;

    public Estudiante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = proximoLegajo;
        proximoLegajo++;
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

    // El apellido puede cambiar (tramite mediante). Convencion de la catedra:
    // no imprime, rechaza con false y deja el estado como estaba.
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

    // TODO 1: hacer que proximoLegajo sea un miembro de clase, para que exista
    //         UNA sola copia compartida por todos los estudiantes.

    // TODO 2: el constructor tiene que hacer cumplir la promesa de la clase:
    //         nombre y apellido no pueden ser nulos ni vacios. Si no sirven,
    //         cortar con throw new IllegalArgumentException("...") como en la
    //         clase pasada.

    // TODO 3: escribir un metodo getProximoLegajo() que devuelva el proximo
    //         legajo a asignar. Pregunta previa: ¿el metodo es de una instancia
    //         o de la clase? Eso decide como se declara y como se lo invoca.
}
