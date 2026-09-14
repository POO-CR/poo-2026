# Clase 10: clases anonimas, lambdas y adaptadores (solucion)

El controlador dejo de implementar ActionListener. En su constructor registra
un receptor por evento, escrito ahi mismo: una lambda por boton, un KeyAdapter
anonimo para Enter y un MouseAdapter anonimo para el doble click. Cada lambda
llama a un metodo privado del controlador, uno por moneda. Los reales entraron
con un metodo en el modelo, un boton en la vista, y una lambda mas un metodo en
el controlador, sin tocar nada de lo que ya estaba. Programa arranca la ventana
con invokeLater().
