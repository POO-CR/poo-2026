# Clase 15: patrones de comportamiento

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

Es una pelea por turnos en consola entre el jugador y un enemigo, con cuatro
problemas de comportamiento marcados en el codigo como A, B, C y D.

- `src/modelo/Partida.java`: los turnos, con un switch por operacion
  (problema A) y llamadas a la pantalla (problema D). TODO 2.
- `src/modelo/Enemigo.java`: la tactica como texto con un switch (problema B).
  TODO 3.
- `src/controlador/Control.java`: las teclas del jugador en un switch
  (problema C). TODO 4.
- `src/modelo/Fase.java`, `src/modelo/Tactica.java` y
  `src/controlador/Comando.java`: las interfaces para los TODO 1, 3 y 4. No se
  modifican.
- `src/modelo/Unidad.java`, `src/modelo/Accion.java`: la unidad y las acciones
  del enemigo.
- `src/vista/PantallaConsola.java`: la vista.
- `src/Programa.java`: una pelea entre Aragorn y un troll.

Para ejecutar, abrir Programa.java y usar el boton Run que aparece sobre el
metodo main. Imprime la pelea ronda por ronda y el registro de combate, y
termina.

Las consignas estan en el enunciado de la actividad.
