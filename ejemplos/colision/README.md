# Demo de Colisión

Demo de colisión entre dos hitboxes (cajas de colisiones) con Java Swing.

## Ejecutar

```
javac -d out src/colision/*.java
java -cp out colision.App
```

(ejecutar desde la raíz del proyecto, para que se encuentre la carpeta `assets/`)

O en VS Code: abrir esta carpeta como workspace (requiere la extensión "Language Support for Java") y F5.

## Controles

- P1: `W A S D`
- P2: flechas

## Mecanismo

Cada personaje (`Personaje`) tiene un rectángulo (`java.awt.Rectangle`) que es su hitbox, es decir, su caja de colisiones. `Personaje` solo guarda estado (posición, hitbox, sprite, si está golpeado); el dibujo lo hace `PanelArena`:

```
Personaje {
  x, y, ancho, alto  -> hitbox (caja de colisiones)
  sprite             -> imagen del personaje
}
```

En cada tick del `Timer` (60 fps, `PanelArena.actualizar()`):

1. Se mueven los rectángulos según las teclas presionadas.
2. Se pregunta si se solapan: `jugador1.getHitbox().intersects(jugador2.getHitbox())`.
3. Si se solapan, ambos pasan a estado `golpeado = true` -> el borde cambia al 3er color (rojo) y se rellena semi-transparente.
4. Si no se solapan, cada uno vuelve a su color de reposo (azul / verde).

```
 no colisión                 colisión
+--------+                  +--------++--------+
| P1     |    +--------+    |P1    __||__    P2|   <- borde rojo (COLOR_GOLPE)
| azul   |    | P2     |    |     |__||__|     |
+--------+    | verde  |    +--------++--------+
              +--------+
```

## Archivos

- `src/colision/Personaje.java`: posición, hitbox y estado de colisión del personaje.
- `src/colision/PanelArena.java`: input, movimiento, detección de colisión, loop de render y dibujo de ambos personajes.
- `src/colision/App.java`: carga de sprites y ventana.
- `assets/troll_001.png`, `assets/troll_002.png`: sprites de los dos jugadores, del pack [2D Game Troll Free Character Sprites](https://craftpix.net/freebies/2d-game-troll-free-character-sprites/).
