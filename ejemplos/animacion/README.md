# Demo de Animación de Caminata

Demo de animación por sprites (flipbook) con Java Swing.

## Ejecutar

```
javac -d out src/animacion/*.java
java -cp out animacion.App
```

(ejecutar desde la raíz del proyecto, para que se encuentre la carpeta `assets/`)

O en VS Code: abrir esta carpeta como workspace (requiere la extensión "Language Support for Java") y F5.

## Controles

`W A S D` o flechas, en cualquier dirección (incluye diagonales).

## Mecanismo

`assets/caminata/caminata.png` es una hoja de sprites de 4 columnas x 3 filas con los 10 fotogramas de un mismo personaje caminando, uno al lado del otro en orden (las 2 celdas de la última fila quedan vacías).

```
fotograma 0 -> fotograma 1 -> fotograma 2 -> ... -> fotograma 9 -> fotograma 0 -> ...
   (se repite en loop, SOLO mientras hay una tecla de movimiento presionada)
```

El estado de animación (`indiceFotograma`, `temporizadorFotograma`, `mirandoIzquierda`) vive en `Personaje`, no en el panel.

El panel (`PanelCaminata`) tiene un `Timer` que dispara `actualizar()` cada 16 ms (~60 fps):

1. Lee las teclas presionadas -> `dx`, `dy`.
2. Llama a `personaje.mover(dx, dy, ...)`, que mueve `x`, `y` (recortado a los bordes del panel) y actualiza `mirandoIzquierda` según `dx`.
3. Llama a `personaje.actualizarAnimacion(16, moviendose)`:
   - si `moviendose == true`, acumula tiempo y avanza `indiceFotograma` cada 80 ms.
   - si `moviendose == false`, `indiceFotograma` vuelve a 0 y el personaje queda quieto (sin animar).
4. Al pintar el panel, si `mirandoIzquierda` es true el sprite se dibuja espejado.

```
sin tecla presionada        con "D" / flecha derecha presionada
+--------+                  +--------+   +--------+   +--------+
| (o)    |  frame fijo 1/10 | (o)   -->  |  (o)  -->  |   (o)  |  frame avanza c/80ms
+--------+                  +--------+   +--------+   +--------+
```

El avance de `indiceFotograma` está condicionado al input (`moviendose`), no al paso del tiempo solo. Sin movimiento, no hay animación.

## Cantidad de fotogramas

`App.CANTIDAD_FOTOGRAMAS = 10`, `App.COLUMNAS_HOJA = 4` y `App.FILAS_HOJA = 3` están fijos en el código porque hoy la hoja `assets/caminata/caminata.png` tiene esa distribución. El layout de la hoja determina esos números: si se rearma la hoja con otra cantidad de fotogramas o de columnas/filas, hay que actualizar esas tres constantes en `App.java` para que coincidan, si no el recorte de cada fotograma sale mal o la carga falla.

## Archivos

- `assets/caminata/caminata.png`: hoja de sprites (4x3) con los 10 fotogramas del ciclo de caminata.
- `assets/caminata/caminata_000.png` ... `caminata_009.png`: los fotogramas sueltos que se usaron para armar la hoja.
- `src/animacion/Personaje.java`: posición y estado de animación (`indiceFotograma`, `mirandoIzquierda`).
- `src/animacion/PanelCaminata.java`: input, timer, composición y dibujo: le pasa el movimiento al `Personaje` y pinta el panel, incluido el sprite del personaje.
- `src/animacion/App.java`: carga de la hoja de sprites, recorte de los 10 fotogramas y ventana.
