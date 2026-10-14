# Clase 18: segundo plano y paralelismo

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src).

Son dos programas. Programa abre el juego con ventana; medicion.Medicion
mide tiempos en la consola. Los cambios estan marcados en el codigo como
TODO 1 a TODO 5.

- `src/Programa.java`: arma el juego. TODO 4.
- `src/controlador/ControladorJuego.java`: los botones y el reloj. TODO 1,
  2, 3 y 4.
- `src/vista/VistaJuego.java`: la ventana. No se modifica.
- `src/modelo/Partida.java`, `Puntaje.java`, `PuntajeDAO.java`: los de la
  clase 16. No se modifican.
- `src/persistencia/PuntajeDAOMemoria.java`: el DAO en memoria. No se
  modifica.
- `src/persistencia/PuntajeDAOLento.java`: un Decorator que simula una base
  remota: cada operacion tarda dos segundos. No se modifica.
- `src/medicion/Primos.java` y `Medicion.java`: contar primos en paralelo y
  medir. TODO 5.

Para ejecutar, abrir Programa.java o medicion/Medicion.java y usar el boton
Run que aparece sobre el metodo main. La consola muestra cada operacion de
la base remota y en que hilo corre.

Las consignas estan en el enunciado de la actividad.
