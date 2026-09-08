# Clase 9: interfaz grafica y eventos (solucion)

VentanaConversor quedo repartida en tres paquetes. `modelo/Conversor` no
importa javax.swing ni nada de los otros dos; `vista/VistaConversor` arma la
ventana y sabe mostrar; `controlador/ControladorConversor` implementa
ActionListener, se registra en los botones y traduce entre las otras dos. Los
new de las tres estan en Programa, en la raiz de src. Los euros entraron con un
metodo en el modelo, un boton en la vista y un if en el controlador.
