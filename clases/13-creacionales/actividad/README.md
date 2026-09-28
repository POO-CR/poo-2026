# Clase 13: patrones creacionales

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

Es un juego de oleadas en consola: un heroe, un nivel que genera enemigos y
un gestor de audio que simula la placa de sonido.

- `src/modelo/GestorAudio.java`: la placa de sonido, con javax.sound.sampled.
  Cada new pide una linea de audio al sistema. TODO 1.
- `assets/`: los tres sonidos, en WAV: musica, cuerno y espada. Si la maquina
  no tiene audio, el programa sigue en silencio.
- `src/modelo/Heroe.java`: el heroe, con un constructor de ocho parametros. TODO 2.
- `src/modelo/Nivel.java`: el nivel, que decide el enemigo con un switch. TODO 3.
- `src/modelo/Enemigo.java`, `Orco.java`, `Troll.java`, `Arquero.java`: los
  enemigos. No se modifican.
- `src/Programa.java`: el programa de prueba. Se modifica solo donde lo
  indica cada TODO, y el bloque de la parte 3 al final.

Para ejecutar, abrir Programa.java y usar el boton Run que aparece sobre el
metodo main. Suena, imprime en la consola y termina en unos tres segundos.

Las consignas estan en el enunciado de la actividad.
