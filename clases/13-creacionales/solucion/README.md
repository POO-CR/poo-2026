# Clase 13: patrones creacionales (solucion)

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

- `GestorAudio` es Singleton, y `Programa` lo obtiene una sola vez y lo pasa
  por constructor a `Heroe` y a `Nivel`.
- `Heroe` se construye con `Heroe.Builder`, que valida todo en `construir()`.
- `Nivel` es abstracta y deja el new en `crearEnemigo()`. `NivelBosque`,
  `NivelFortaleza` y `NivelPantano` lo implementan.
