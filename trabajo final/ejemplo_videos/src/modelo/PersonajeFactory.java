package modelo;

public class PersonajeFactory {
    
    public static Personaje crear(int id, String nombre, String ubicacion, String colorGratis) {
        return switch (nombre.trim()) {
            case "Frodo y Sam"    -> new FrodoYSam(id);
            case "Gandalf"        -> new Gandalf(id);
            case "Aragorn"        -> new Aragorn(id);
            case "Legolas"        -> new Legolas(id);
            case "Arwen"          -> new Arwen(id);
            case "Merry y Pippin" -> new MarryYPippin(id);
            case "Gollum"         -> new Gollum(id);
            default -> throw new IllegalArgumentException("Personaje no soportado: " + nombre);
        };
    }

}
