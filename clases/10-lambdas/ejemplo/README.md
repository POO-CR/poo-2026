# Clase 10: el ejemplo de la pre clase, en cinco proyectos

La misma caja, con dos botones, en cinco versiones del controlador. Cada
carpeta es un proyecto aparte: abrirla en VSCode y ejecutar Programa.

    1-con-if/               un receptor para los dos botones, con un if por getSource()
    2-una-clase-por-boton/  ReceptorEfectivo y ReceptorDebito, clases con nombre
    3-clases-anonimas/      las mismas dos, anonimas, adentro del constructor
    4-lambdas/              una lambda por boton
    5-adaptadores/          lambdas, Enter con KeyAdapter, doble click con MouseAdapter, invokeLater

Modelo y vista son los mismos en las cinco (la 5 agrega limpiarMonto() a la
vista). Lo que cambia es controlador/ControladorCaja.java. La 1 trae ademas
PruebaSinPantalla, el modelo usado sin ventana.
