# Clase 15: patrones de comportamiento (solucion)

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

- State: `Partida` delega en su `Fase`, con `TurnoJugador`, `TurnoEnemigo` y
  `Terminada`.
- Strategy: `Enemigo` delega en su `Tactica`, con `TacticaAgresiva`,
  `TacticaDefensiva` y `TacticaCobarde`.
- Observer: `Unidad` avisa a sus `ObservadorUnidad`, y `PantallaConsola` es uno.
  `Partida` ya no conoce a la vista.
