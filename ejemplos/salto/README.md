# Demo de Salto

Demo de gravedad e impulso de salto con Java Swing.

## Ejecutar

```
javac -d out src/salto/*.java
java -cp out salto.App
```

(ejecutar desde la raíz del proyecto, para que se encuentre la carpeta `assets/`)

O en VS Code: abrir esta carpeta como workspace (requiere la extensión "Language Support for Java") y F5.

## Controles

- `A D` o flechas izquierda/derecha: mover
- `ESPACIO` (o `W` / flecha arriba): saltar (solo si está en el suelo)
- Checkbox "Salto variable": alterna entre los dos comportamientos de salto (ver abajo)

## Mecanismo

El sprite es fijo y lo que cambia es su posición Y según la física.

Estado del personaje (vive en `Personaje`, no en el panel): `y` (posición vertical), `velocidadY`, `enSuelo`.

El panel (`PanelSalto`) tiene un `Timer` que dispara `actualizar()` cada 16 ms (~60 fps); ahí solo lee el input y se lo pasa a `personaje.actualizarFisica(...)`, que hace:

1. Si se pide saltar (`ESPACIO`/`W`/`↑`) y `enSuelo == true` -> `velocidadY = IMPULSO_SALTO` (negativo, hacia arriba) y `enSuelo = false`.
2. Si se suelta la tecla de salto en pleno ascenso y el checkbox está tildado -> `velocidadY *= FACTOR_CORTE_SALTO` (recorta el impulso: salto corto).
3. Siempre se le suma la gravedad a la velocidad: `velocidadY += GRAVEDAD`.
4. Se aplica la velocidad a la posición: `y += velocidadY`.
5. Si `y` llega al piso (`y >= alturaSuelo`) -> se clava en el piso, `velocidadY = 0`, `enSuelo = true`.

## Salto fijo vs salto variable

El checkbox llama a `panel.setSaltoVariable(boolean)` y decide si el paso 2 se aplica:

```
Salto FIJO (checkbox destildado)         Salto VARIABLE (checkbox tildado)
siempre el mismo arco,                   toque corto -> salto corto
sin importar cuanto se mantenga ESPACIO  mantener apretado -> salto completo

     . .                                      . .              . . . .
   .     .              tap                .     .    hold  .         .
  .       .             ESPACIO           .       .  ESPACIO.           .
 .         .                             .         .        .           .
.___________.                           .           .      .             .
```

La altura del salto depende de cuánto tiempo se sostiene la tecla, no solo de si se la presionó.

```
   ESPACIO           velocidadY negativa       gravedad la va frenando       aterriza
   (impulso)         (sube)                    y despues cae                (enSuelo=true)

     ^ y                . .                          . .
     |               .       .                    .       .
     |             .           .                .           .
suelo|___________.               .            .               .____________
     +--------+                    .        .                  +--------+
     | verde  |                      .    .                     | verde  |
     +--------+                       .  .   naranja mientras    +--------+
                                        ''    esta en el aire
```

Es el patrón clásico de física simple: velocidad acumulando aceleración (gravedad) y posición acumulando velocidad, con un choque contra un límite (el suelo) que resetea el estado.

## Color del borde según la altura

El color del borde comunica el estado: verde en el suelo; en el aire, amarillo para un salto bajo, naranja para uno medio y rojo para uno que se acerca a la altura máxima que puede alcanzar un salto completo.

`Personaje.getProgresoAlturaMaxima()` calcula esa cercanía como `(alturaSuelo - y) / ALTURA_MAXIMA_SALTO`, recortado a `[0, 1]`. `ALTURA_MAXIMA_SALTO` es la altura de un salto sin cortar, según la física de caída libre: `IMPULSO_SALTO^2 / (2 * GRAVEDAD)`.

`PanelSalto.getColorBorde()` divide ese progreso en tres bandas:

```
progreso        [0, 1/3)         [1/3, 2/3)         [2/3, 1]
color           amarillo         naranja            rojo
                (salto bajo)     (salto medio)      (salto alto)
```

```
en el suelo         despegue           cerca del pico            cayendo             aterriza
progreso=0           progreso≈0.2        progreso≈0.9              progreso≈0.3        progreso=0
+--------+           +--------+          +--------+                +--------+          +--------+
| verde  |           |amarillo|          |  rojo  |                |amarillo|          | verde  |
+--------+           +--------+          +--------+                +--------+          +--------+
```

Con salto variable (checkbox tildado) y un toque corto, el personaje nunca llega cerca de `ALTURA_MAXIMA_SALTO`, así que el borde se queda amarillo y no llega a naranja ni rojo: el color refleja la altura alcanzable, no solo la altura real de ese salto.

Mientras el personaje esta en el aire, `PanelSalto.dibujarPersonaje(...)` ademas rellena la caja con ese mismo color en forma translucida (`ALPHA_RELLENO_AIRE = 70` de transparencia), asi el aviso de altura no depende solo del borde. En el suelo no hay relleno, solo el borde verde.

## Archivos

- `src/salto/Personaje.java` — posición, física (gravedad/salto/corte de salto), colisión con el suelo.
- `src/salto/PanelSalto.java` — input, timer, composición y dibujo: le pasa el movimiento/salto al `Personaje` y pinta el panel, incluido el sprite y la caja de estado del personaje.
- `src/salto/App.java` — carga del sprite, ventana y checkbox de salto variable.
- `assets/PNG/Animation/Troll1/Idle_000.png` — único sprite usado.
- `assets/License.txt` — licencia de ese sprite.
