# Clase 9: el ejemplo de la pre clase, completo

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

    src/
      VentanaCaja.java          la version que no conviene, todo en una clase
      ProgramaTodoJunto.java    la arranca
      modelo/                   Pago, PagoEfectivo, Notificador, ServicioCorreo, Caja
      vista/                    VistaCaja
      controlador/              ControladorCaja
      Programa.java             arranca la version repartida
      PruebaSinPantalla.java    usa el modelo sin ventana

Tres programas, cada uno con su boton Run sobre main:

- `ProgramaTodoJunto.java`: `VentanaCaja` es la ventana, atiende el boton y
  lleva el total. Probar con 1500 y 800, y despues con "hola" para ver la
  excepcion en la consola.
- `Programa.java`: la version repartida en tres paquetes. Misma ventana, mismo
  resultado, y con "hola" aparece un cartel.
- `PruebaSinPantalla.java`: el modelo usado desde main, sin ventana. Es la
  prueba de que `Caja` no sabe que hay una pantalla.

El paquete `modelo` no importa nada de `javax.swing`, ni de `vista`, ni de
`controlador`. Los otros dos importan lo que usan del modelo. El dominio
(`Pago`, `PagoEfectivo`, `Notificador`, `ServicioCorreo`) es el de las pre
clases 5 a 8, reducido a lo que necesita el ejemplo; `ServicioCorreo` imprime
en vez de mandar un correo.
