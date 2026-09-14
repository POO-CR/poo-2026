# Clase 11: el ejemplo de la pre clase, en tres proyectos

La caja de la clase pasada con un boton mas, el de QR, en tres versiones de la
vista. Cada carpeta es un proyecto aparte: abrirla en VSCode y ejecutar
Programa. Conviene agrandar la ventana en cada una.

    1-coordenadas/    setLayout(null) y setBounds(): el QR obligo a correr todo,
                      y al agrandar la ventana nada se mueve
    2-borderlayout/   el BorderLayout del JFrame, sin paneles: un componente por
                      region, el campo estirado, y el QR sin lugar
    3-paneles/        un panel por region, cada uno con su layout, y pack()

Modelo y controlador son los mismos en las tres. Lo unico que cambia es
vista/VistaCaja.java.
