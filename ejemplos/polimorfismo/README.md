# Ejemplo de Polimorfismo (Tarifas de Proveedores)

Ejemplo de polimorfismo estático y dinámico calculando la tarifa de distintos proveedores de telefonía.

## Ejecutar

```
javac -d out src/*.java
java -cp out App
```

O en VS Code: abrir esta carpeta como workspace (requiere la extensión "Language Support for Java") y F5.

## Mecanismo

`TarifaProveedor` es una clase abstracta que define el cálculo de la tarifa como la suma de tres partes (SMS, minutos, GB), cada una con su propio método:

```java
public double calcular(int totalSMS, int totalMinutos, int totalGigas) {
    return calcularSMS(totalSMS) + calcularMinutosDeLlamada(totalMinutos) + calcularConsumoGB(totalGigas);
}
```

`calcularSMS`, `calcularMinutosDeLlamada` y `calcularConsumoGB` tienen una implementación por defecto con los precios base ($1 SMS, $15 minuto, $20 GB), pero son `protected` para que las clases hijas puedan redefinirlos: `calcular()` fija el algoritmo base comun y cada clase hija cambia solo los pasos donde tiene un recargo.

- `TarifaClaro`: redefine `calcular()` completo, 20% extra sobre el total.
- `TarifaPersonal`: redefine `calcularMinutosDeLlamada` (+20%) y `calcularConsumoGB` (+50%).
- `TarifaMovistar`: redefine `calcularSMS` (+10%), `calcularMinutosDeLlamada` (+20%) y `calcularConsumoGB` (+30%).

Las tres además implementan `getNombre()`, que en `TarifaProveedor` es abstracto: no tiene cuerpo, así que obliga a cada hija a definir el suyo.

## Polimorfismo estático vs dinámico

`App.java` muestra los dos tipos, uno después del otro:

```java
/* Polimorfismo estatico */
new TarifaClaro().calcular(sms, minutos, gigas);
new TarifaPersonal().calcular(sms, minutos, gigas);
new TarifaMovistar().calcular(sms, minutos, gigas);
```

En cada línea el compilador ya conoce el tipo exacto del objeto (`TarifaClaro`, `TarifaPersonal`, `TarifaMovistar`), la resolucion de qué implementacion de `calcular()` se va ejecutar se resuelve en tiempo de compilación, antes de que el programa siquiera arranque. Sigue siendo polimorfismo (el mismo nombre de método significa algo distinto según la clase), pero no hay ambigüedad que resolver en tiempo de ejecucion: de ahí "estático".

```java
TarifaProveedor[] proveedores = {
        new TarifaClaro(),
        new TarifaPersonal(),
        new TarifaMovistar()
};

for (TarifaProveedor proveedor : proveedores) {
    /* Polimorfismo dinamico */
    double total = proveedor.calcular(sms, minutos, gigas);
    System.out.printf("%s: $%.2f\n", proveedor.getNombre(), total);
}
```

Acá, la variable `proveedor` está declarada como de tipo `TarifaProveedor`, el tipo comun o clase padre para cada tipo de Tarifa, pero cada elemento del arreglo es en verdad una instancia de un tipo concreto. 

Si declaramos una variable como de un tipo base, por el polimorfismo podemos asignarle instancias de cuaquier clase hija. 

```java
TarifaProveedor proveedor1 = new TarifaClaro();
TarifaProveedor proveedor2 = new TarifaMovistar();
```

Al estar ejecutandose el programa, Java decide qué version del metodo `calcular()` usar según el tipo real del objeto creado en memoria, no según el tipo declarado de la variable (Que es `TarifaProveedor`), y esa decisión se toma en tiempo de ejecución, en el momento en el que el programa lo va a usar: de ahí "dinámico".

```
tipo declarado (App.java):  TarifaProveedor
                                    |
                          proveedor.calcular()
                                    |
                 +------------------+-------------------+
                 |                  |                   |
        tipo real: Claro    tipo real: Personal   tipo real: Movistar
        -> su calcular()    -> su calcular()      -> su calcular()
```

## Archivos

- `src/TarifaProveedor.java`: clase abstracta, algoritmo base (`calcular`) y pasos redefinibles (`calcularSMS`, `calcularMinutosDeLlamada`, `calcularConsumoGB`); declara `getNombre()` como abstracto.
- `src/TarifaClaro.java`, `src/TarifaPersonal.java`, `src/TarifaMovistar.java`: hijas de `TarifaProveedor`, cada una redefine los pasos donde su proveedor tiene recargo.
- `src/App.java`: pide por teclado SMS, minutos y GB, y muestra los dos usos de polimorfismo (estático y dinámico) sobre los mismos datos.
