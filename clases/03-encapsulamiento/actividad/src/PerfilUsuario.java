// Archivo: PerfilUsuario.java
// Primer borrador de la clase. Funciona, pero no protege nada.

public class PerfilUsuario {

    // Atributos publicos: cualquier parte del programa puede escribir lo que quiera.
    public String nombreUsuario;
    public String nombreCompleto;
    public String email;
    public int anioNacimiento;

    public void mostrarPerfil() {
        System.out.println("--- Perfil ---");
        System.out.println("Usuario: " + this.nombreUsuario);
        System.out.println("Nombre completo: " + this.nombreCompleto);
        System.out.println("Email: " + this.email);
        System.out.println("Anio de nacimiento: " + this.anioNacimiento);
    }

    // TODO 1: pasar los cuatro atributos a private.

    // TODO 2: escribir un constructor que reciba nombreUsuario y email.
    //         Si alguno de los dos es invalido, cortar con
    //         throw new IllegalArgumentException("...");

    // TODO 3: escribir los getters que hagan falta.

    // TODO 4: escribir solo los setters que tengan sentido, devolviendo boolean.
    //         Ningun setter imprime nada: rechaza el valor y avisa con false.

    // TODO 5: borrar mostrarPerfil(). No es que un metodo no pueda imprimir: es
    //         que en este programa el que decide que mostrar es PruebaPerfil.
    //         Validar los datos es del perfil, mostrarlos es de quien lo usa.

    // Reglas que la clase tiene que hacer cumplir:
    //   a) nombreUsuario no puede ser nulo ni estar vacio, y no cambia nunca.
    //   b) email tiene que contener un '@'.
    //   c) anioNacimiento no puede ser futuro ni anterior a 1900.
    //   d) nombreCompleto no puede ser nulo ni estar vacio.
}
