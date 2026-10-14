# Clase 17: hilos y recursos compartidos

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src).

Son dos programas de consola con errores de concurrencia marcados en el
codigo como TODO 1 a TODO 6.

- `src/Arena.java`: ocho jugadores contra una oleada de enemigos, cada uno en
  su hilo. Al final muestra un resumen con lo que tendria que haber pasado.
- `src/Intercambio.java`: dos jugadores que se pasan oro, uno en cada
  direccion. Parte 3.
- `src/modelo/Marcador.java`: TODO 1.
- `src/modelo/Configuracion.java`: TODO 2.
- `src/modelo/Bitacora.java`: TODO 3.
- `src/modelo/Enemigo.java`: TODO 4.
- `src/modelo/Reloj.java`: TODO 5.
- `src/modelo/Cofre.java`: TODO 6.
- `src/modelo/Jugador.java`, `src/modelo/Narrador.java`: los hilos de la
  arena. No se modifican.
- `src/Vigia.java`: detecta hilos trabados en Intercambio. No se modifica.

Para ejecutar, abrir Arena.java o Intercambio.java y usar el boton Run que
aparece sobre el metodo main. Mientras tenga errores, Arena no termina sola:
se detiene con el boton Stop (el cuadrado rojo) de VSCode.

Las consignas estan en el enunciado de la actividad.
