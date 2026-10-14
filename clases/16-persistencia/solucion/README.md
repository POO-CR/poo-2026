# Clase 16: persistencia con DAO y Singleton (solucion)

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).
Necesita sqlite-jdbc-3.50.3.0.jar en lib/.

- `PuntajeDAO` es la interfaz, en el paquete modelo.
- `PuntajeDAOSQLite` y `PuntajeDAOMemoria` la implementan, en persistencia.
- `Conexion` es Singleton; Programa la obtiene una vez y la pasa al DAO.
- `PruebaPartida` usa el DAO en memoria y no toca la base.
