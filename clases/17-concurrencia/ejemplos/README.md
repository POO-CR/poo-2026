# Clase 17: ejemplos de la clase

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src). Cada
archivo es un programa con su main: se ejecuta con el boton Run.

- `Nucleos.java`: cuantos nucleos ve la maquina virtual y el hilo del main.
- `Carrera.java`: dos hilos que no avanzan a la vez. ¿Que esta mal?
- `CicloDeVida.java`: los estados de un hilo y start() dos veces.
- `RelojInterrumpido.java`: detener un hilo con interrupt(). Borrando la
  linea del catch, no se detiene nunca (Stop para cortar).
- `Puntos.java`: la condicion de carrera del marcador, con synchronized y
  con AtomicInteger.
- `MuereDosVeces.java`: comprobar y despues actuar, sin proteger y con un
  bloque synchronized.
- `ListaCompartida.java`: ArrayList recorrida mientras otro hilo agrega.
  Despues, con CopyOnWriteArrayList.
- `Deadlock.java`: dos transferencias que se traban: el programa no termina
  (Stop para cortar).
- `SingletonConHilos.java`: getInstancia() con y sin synchronized.
