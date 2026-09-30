# Clase 14: patrones estructurales

Proyecto Java sin herramientas de construccion. Para abrirlo: en VSCode,
Archivo -> Abrir carpeta, y elegir esta carpeta (la que contiene src y lib).

Es una batalla en consola: soldados, escuadras, equipamiento y un subsistema
de combate con dado, reglas y bitacora. Todo en el paquete modelo.

- `src/modelo/Combatiente.java`: la interfaz de lo que pelea. No se modifica.
- `src/modelo/Soldado.java`: un combatiente suelto. No se modifica.
- `src/modelo/Escuadra.java`: un grupo de soldados que no es Combatiente. TODO 1.
- `src/modelo/SoldadoConEspada.java`, `SoldadoConEscudo.java`,
  `SoldadoConEspadaYEscudo.java`: el equipamiento como subclases. TODO 2.
- `src/modelo/Dado.java`, `ReglasCombate.java`, `Bitacora.java`: el subsistema
  de combate. No se modifican.
- `src/Programa.java`: arma los bandos y resuelve tres ataques a mano. TODO 1,
  2 y 3.

Para ejecutar, abrir Programa.java y usar el boton Run que aparece sobre el
metodo main. Imprime la bitacora y termina.

Las consignas estan en el enunciado de la actividad.
