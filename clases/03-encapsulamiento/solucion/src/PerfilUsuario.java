// Archivo: PerfilUsuario.java
// Solucion de la actividad de la clase 3.

public class PerfilUsuario {

    // TODO 1 resuelto: los atributos van private. Nadie los escribe desde afuera.
    // El nombre de usuario ademas es final: se fija al crear el perfil y no
    // cambia nunca, asi que tampoco existe un setNombreUsuario.
    private final String nombreUsuario;
    private String nombreCompleto;
    private String email;
    private int anioNacimiento;

    // TODO 2 resuelto: el objeto se niega a nacer invalido. El constructor no
    // puede devolver false, asi que corta con una excepcion y un mensaje claro.
    public PerfilUsuario(String nombreUsuario, String email) {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacio.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El email tiene que contener un arroba.");
        }
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    // TODO 3 resuelto: getters. Acceso de solo lectura al estado.
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public String getNombreCompleto() {
        return this.nombreCompleto;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAnioNacimiento() {
        return this.anioNacimiento;
    }

    // TODO 4 resuelto: solo los setters que tienen sentido. Ninguno imprime:
    // rechazan con false, dejan el estado como estaba y quien llama avisa.

    public boolean setEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        this.email = email;
        return true;
    }

    public boolean setAnioNacimiento(int anioNacimiento) {
        // El anio actual se consulta en lugar de escribirlo fijo, para que
        // "futuro" siga siendo futuro el anio que viene.
        int anioActual = java.time.LocalDate.now().getYear();
        if (anioNacimiento < 1900 || anioNacimiento > anioActual) {
            return false;
        }
        this.anioNacimiento = anioNacimiento;
        return true;
    }

    public boolean setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.isEmpty()) {
            return false;
        }
        this.nombreCompleto = nombreCompleto;
        return true;
    }

    // TODO 5 resuelto: mostrarPerfil() se borro. Validar los datos es del
    // perfil; decidir que mostrar y como es de quien lo usa.
}
