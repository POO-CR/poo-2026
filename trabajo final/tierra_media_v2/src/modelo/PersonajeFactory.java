package modelo;

public class PersonajeFactory {
	public static Personaje crearPersonaje(int id, String nombre, Bando bando, String tipo, 
	            int salud, int ataque, int defensa, int velocidad, int paramExtra) {
		switch (tipo.toUpperCase()) {
			case "GUERRERO":
				return new Guerrero(id, nombre, bando, salud, ataque, defensa, velocidad, paramExtra);
			case "MAGO":
				return new Mago(id, nombre, bando, salud, ataque, defensa, velocidad, paramExtra);
			case "SIGILOSO":
			case "AGIL":
				return new Sigiloso(id, nombre, bando, salud, ataque, defensa, velocidad, paramExtra / 100.0);
		default:
			throw new IllegalArgumentException("Tipo de personaje desconocido: " + tipo);
		}
	}
}
