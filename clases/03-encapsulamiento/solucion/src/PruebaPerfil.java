// Archivo: PruebaPerfil.java
// Version de la solucion: parte 1 comentada, parte 2 activa.

public class PruebaPerfil {

    public static void main(String[] args) {

        // ------------------------- PARTE 1: el problema -------------------------
        // Ya no compila, y eso es la prueba de que la refactorizacion funciono:
        // el programa perdio la posibilidad de escribir los atributos a mano.

        /*
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.nombreUsuario = "";
        perfil.email = "esto-no-es-un-email";
        perfil.anioNacimiento = 2050;
        perfil.mostrarPerfil();
        */

        // ------------------------- PARTE 2: el resultado ------------------------

        PerfilUsuario valido = new PerfilUsuario("jperez", "jperez@dominio.com");
        System.out.println("Perfil creado: " + valido.getNombreUsuario());

        if (!valido.setEmail("sin-arroba")) {
            System.out.println("Email rechazado, sigue siendo " + valido.getEmail());
        }

        if (valido.setEmail("juan.perez@dominio.com")) {
            System.out.println("Email actualizado a " + valido.getEmail());
        }

        if (!valido.setAnioNacimiento(2050)) {
            System.out.println("Anio de nacimiento rechazado.");
        }

        valido.setAnioNacimiento(1998);
        valido.setNombreCompleto("Juan Perez");
        System.out.println(valido.getNombreCompleto() + ", " + valido.getAnioNacimiento());

        // Esta linea no tiene que compilar: el nombre de usuario no cambia nunca.
        // valido.setNombreUsuario("otro");

        // Y esta creacion tiene que cortar el programa con un mensaje claro.
        PerfilUsuario roto = new PerfilUsuario("", "a@b.com");
        System.out.println("Esta linea no se tendria que ver: " + roto.getNombreUsuario());
    }
}
