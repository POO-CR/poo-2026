# Clase 18: ejemplos de la clase

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src). Cada
ejemplo es un paquete; se ejecuta abriendo la clase con el main y usando el
boton Run.

- `ventana`: cargar un nivel en el hilo de eventos (el reloj se para) y con
  un SwingWorker con barra de avance. Modelo (CargaDeNivel), vista
  (VistaCarga) y controlador (ControladorCarga); el reloj y el mensaje que
  se borra solo son javax.swing.Timer. Se ejecuta Programa.
- `pool`: ocho tareas en un pool de tres hilos, con Callable y Future.
  Borrando el shutdown(), el programa no termina (Stop para cortar).
- `productorconsumidor`: una BlockingQueue con lugar para tres; el juego
  (Programa) espera cuando se llena, y el Guardador consume. Se ejecuta
  Programa.
- `speedup`: speed-up y eficiencia en esta maquina, con un tramo por hilo y
  con cien tramos. Se ejecuta Speedup.
