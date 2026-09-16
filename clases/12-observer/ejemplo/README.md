# Clase 12: el ejemplo de la pre clase, en dos proyectos

La caja de la clase pasada con una segunda ventana, el listado de pagos del
dia en una tabla, con un boton para anular el ultimo. Cada carpeta es un
proyecto aparte: abrirla en VSCode y ejecutar Programa.

    1-sin-observador/   cada controlador actualiza las vistas que conoce: registrar
                        actualiza las dos, anular solo el listado, y el total queda viejo
    2-con-observador/   la Caja avisa a sus observadores despues de cada cambio; los
                        controladores solo le piden cosas al modelo

Lo que cambia entre los dos: modelo/ObservadorCaja.java (nueva), modelo/Caja.java,
las dos vistas (implementan ObservadorCaja), los dos controladores y Programa.
