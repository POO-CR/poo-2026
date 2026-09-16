# Clase 12: el modelo avisa cuando cambia

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

Es el conversor de moneda de la clase pasada, con un historial de
conversiones en el modelo y una segunda ventana que lo muestra en una tabla:

- `src/modelo/Conversor.java`: el modelo, con el historial. TODO 1.
- `src/modelo/Conversion.java`: una conversion hecha.
- `src/vista/VistaConversor.java`: la vista principal. TODO 2.
- `src/vista/VistaHistorial.java`: la ventana con la tabla. TODO 3.
- `src/vista/ModeloTablaConversiones.java`: el TableModel de la tabla. No se modifica.
- `src/controlador/ControladorConversor.java`: el controlador. TODO 4.
- `src/Programa.java`: el programa de prueba. No se modifica, salvo comentar y
  descomentar los bloques que indica cada parte.

Para ejecutar, abrir Programa.java y usar el boton Run que aparece sobre el
metodo main. Se abren dos ventanas; el programa termina al cerrarlas.

Las consignas estan en el enunciado de la actividad.
