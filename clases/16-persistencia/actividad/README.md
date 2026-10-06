# Clase 16: persistencia con DAO y Singleton

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

Necesita el driver de SQLite en lib/. En el zip del classroom ya viene. Si el
proyecto viene del repositorio, descargar sqlite-jdbc-3.50.3.0.jar de
https://github.com/xerial/sqlite-jdbc/releases y copiarlo a lib/.

- `src/modelo/Partida.java`: suma puntos y guarda el puntaje, con el SQL
  adentro. TODO 1, 3 y 4.
- `src/modelo/Puntaje.java`: un puntaje. No se modifica.
- `src/persistencia/Conexion.java`: vacia, para el TODO 1.
- `src/Programa.java`: dos partidas y la tabla de honor.
- `src/PruebaPartida.java`: una prueba de Partida que escribe en la base real.

Para ejecutar, abrir Programa.java o PruebaPartida.java y usar el boton Run
que aparece sobre el metodo main. La base se crea en juego.db, en esta
carpeta. Con Java 22 o mas nuevo aparecen avisos sobre acceso nativo al
cargar el driver: son avisos, no errores.

Las consignas estan en el enunciado de la actividad.
