# Demo de Super Ataque

Demo en Java Swing del super ataque de un juego de pelea Ken vs Goku. Al pedir el super, la pelea se congela y aparece la imagen del personaje a media pantalla; después el luchador hace la pose de lanzamiento por fotogramas y dispara un rayo armado con piezas, que golpea al rival o choca contra el rayo del otro.

El proyecto está organizado en MVC (paquetes `modelo`, `vista` y `controlador`) y usa los patrones Observer (la pelea avisa a la vista), State (estados del luchador), Visitor (dibujos de cada estado) y Strategy (quitado del fondo de las hojas de sprites).

## Ejecutar

```
javac -d out src/*.java src/modelo/*.java src/vista/*.java src/controlador/*.java
java -cp out App
```

(ejecutar desde la raíz del proyecto, para que se encuentre la carpeta `assets/`)

O en VS Code: abrir esta carpeta como workspace (requiere la extensión "Language Support for Java") y F5.

## Controles

- `T`: super de Ken (Shinku Hadoken)
- `I` (o `4` del teclado numérico): super de Goku (Kamehameha)
- `ESC`: salir

## Mecanismo

`ControladorPelea` tiene un `Timer` de 16 ms. En cada tick lee las teclas, avanza `Pelea` en pasos fijos de 16 ms (los que hagan falta para alcanzar el tiempo real). Al final de cada paso, `Pelea` avisa a sus observadores y `PanelPelea` se redibuja (ver [Patrón Observer](#patrón-observer-la-pelea-avisa)).

```
 T / I
   |
   v
+------------------------+   +-----------------------------+   +--------+
| imagen del super       |   | pose de lanzamiento + rayo  |   | QUIETO |
| (todo congelado, 1 s)  |-->| (el rayo crece y dura 1,3 s)|-->|        |
+------------------------+   +-----------------------------+   +--------+
```

1. **Inicio** (`EstadoLibre`): con el comando de super, el luchador pasa a `EstadoSuper` (fotograma 0 de la animación del super).
2. **Imagen del super** (`Pelea`): `mostrarRetratoSiEmpiezaSuper` le pregunta al luchador `isRetratoPendiente()`; `EstadoSuper` responde `true` hasta que la pelea llama a `marcarRetratoMostrado()`, y la pelea queda congelada 1000 ms. Un super pedido durante el congelado queda pendiente y se aplica al terminar. `DibujanteRetratos.dibujar` muestra la imagen del personaje en su mitad, entrando deslizada en 150 ms y desvaneciéndose en los últimos 100 ms.
3. **Lanzamiento**: al terminar la imagen, `saltarALanzamientoDelSuper()` lleva la animación al fotograma de lanzamiento (3 en Ken, 4 en Goku). En ese fotograma `EstadoSuper.actualizar` llama a `Luchador.lanzarRayo()`, que crea el `Rayo` una sola vez desde las manos, a la altura que da cada luchador con `getAlturaRayo()`.
4. **Vuelta a QUIETO**: `EstadoSuper.actualizar` espera a que la animación termine y el rayo deje de estar activo.

### Patrón Observer: la pelea avisa

La pelea tiene una lista de observadores y los llama después de cada cambio. El controlador escribe en la pelea; la vista lee de la pelea cuando la pelea avisa.

```java
// modelo/Pelea.java
public void agregarObservador(IObservadorPelea observador) {
    observadores.add(observador);
}

public void quitarObservador(IObservadorPelea observador) {
    observadores.remove(observador);
}

private void notificar() {
    for (IObservadorPelea observador : observadores) {
        observador.peleaCambio(this);
    }
}

// vista/PanelPelea.java
public void peleaCambio(Pelea pelea) {
    repaint();
}

// App.java: la vista se registra
pelea.agregarObservador(panel);
```

```
 ControladorPelea --actualizar()--> Pelea --peleaCambio()--> PanelPelea
     (escribe)                   notificar()                 (lee y dibuja)
```

`actualizar` y `reiniciar` terminan con `notificar()`. `IObservadorPelea` está en el modelo y la implementa la vista: la pelea conoce la interfaz, y la vista depende del modelo.

### Patrón State

```
        IEstadoLuchador
    /         |          \
 Libre      Super      Golpeado
```

La interfaz tiene solo lo que hacen los tres estados: qué animación mostrar (`getAnimacion`), qué hacer en cada tick (`actualizar`) y recibir al visitante (`aceptar`). Lo propio del super lo pide cada estado al `Luchador`: `EstadoLibre.actualizar` llama a `empezarSuper()` (cambia a `EstadoSuper` y deja pendiente la imagen del super) y `EstadoSuper.actualizar` llama a `lanzarRayo()` al llegar al fotograma de lanzamiento. El luchador guarda su rayo en curso y la pelea lo toma con `getRayo()`.

Nadie pregunta en qué estado está un luchador: cada clase de estado responde por sí misma. Cada estado toma su animación del luchador (`EstadoSuper.getAnimacion` devuelve `luchador.getAnimacionSuper()`).

### Patrón Visitor: los dibujos de cada estado

La vista elige los dibujos con despacho doble. `IVisitanteEstado` está en el modelo y la implementa `AparienciaLuchador`, que guarda los dibujos del estado visitado:

```java
// vista/PanelPelea.java
luchador.aceptar(apariencia);
Fotogramas fotogramas = apariencia.getFotogramasElegidos();

// modelo/EstadoSuper.java
public void aceptar(IVisitanteEstado visitante) {
    visitante.visitar(this);
}

// vista/AparienciaLuchador.java
public void visitar(EstadoSuper estado) {
    fotogramasElegidos = superAtaque;
}
```

```
 luchador.aceptar(apariencia) --> estado.aceptar(apariencia) --> apariencia.visitar(this)
                                   (EstadoSuper)                  visitar(EstadoSuper): dibujos del super
```

El modelo solo conoce la interfaz `IVisitanteEstado`; la vista depende del modelo.

### Impacto sobre el rival

`Pelea.actualizarRayos` corre en cada tick, después de que los rayos crecen:

```
rayo.getHitbox() toca al rival?
    sí -> rayo.limitarLargo(distancia hasta el centro del rival)   (se frena en su cuerpo)
          rayo.marcarImpacto()                                     (la vista dibuja la punta de impacto)
          pasaron 130 ms desde el último golpe?
              sí -> rival.recibirGolpe(...)   (EstadoGolpeado 350 ms, en su lugar)
                    nueva chispa (Impacto) en la punta
```

- `impactando` se borra en cada `Rayo.actualizar` y la pelea lo vuelve a marcar mientras siga tocando.
- Cada golpe crea un `EstadoGolpeado` nuevo: el aturdimiento y la animación vuelven a empezar.
- El rayo se corta si su dueño sale del super: `Luchador.forzarEstado` desactiva el rayo en curso en cada cambio de estado.

### Choque de rayos

Con dos rayos en direcciones opuestas, los dos se recortan en el punto medio entre sus orígenes y cada 5 ticks salta una chispa en el encuentro.

### El rayo con piezas

`SpritesRayo` recorta las piezas de la hoja de Marvel vs. Capcom 2 y `DibujanteRayo.dibujar` las arma (las usan los dos supers):

```
 anillos (en las manos, en loop mientras dura el rayo)
   (( )
 +------+--------+--------+--------+-----\
 | cola | cuerpo | cuerpo | cuerpo | punta )   <- tocando al rival: impacto (azul/violeta alternados)
 +------+--------+--------+--------+-----/
 origen        (el cuerpo se repite)      largo
```

### Tiempos y dibujos por separado

El modelo sabe cuántos fotogramas tiene cada animación y cuánto dura cada uno (`Animacion` en `Ken`/`Goku`, avanzada por `ReproductorAnimacion`). Cómo se ve cada fotograma lo sabe la vista (`Fotogramas`, recortados por `AparienciaKen`/`AparienciaGoku`). `AparienciaLuchador.verificar` revisa al arrancar que cada estado tenga tantos dibujos como fotogramas.
