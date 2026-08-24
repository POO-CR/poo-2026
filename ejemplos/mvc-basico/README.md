## Ejemplo basico de Modelo Vista Controlador

```
                    ┌──────────────────────────────┐
                    │           USUARIO            │
                    └───────────────┬──────────────┘
                            ▲                │
                            │ ve el       interactúa
                            │ resultado      │
                            │                ▼
                    ┌───────────────────────────────┐
                    │            VISTA              │
                    │ Presenta la información al    │
                    │ usuario y captura sus acciones│
                    └───────┬───────────────┬───────┘
                            ▲               │
                     actualiza          notifica
                            │           acción
                            │               ▼
                    ┌───────────────────────────────┐
                    │         CONTROLADOR           │
                    │  Recibe la acción de la Vista │
                    │      y coordina el flujo      │
                    └───────────────┬───────────────┘
                                    │ actualiza/consulta
                                    ▼
                    ┌───────────────────────────────┐
                    │           MODELO              │
                    │   Datos y lógica de negocio   │
                    │       de la aplicación        │
                    └───────────────────────────────┘
```

- **Modelo**: gestiona los datos y las reglas de negocio.
- **Vista**: muestra la información al usuario y captura sus interacciones.
- **Controlador**: recibe las acciones de la Vista y coordina Modelo y Vista.

- Modelo, Vista y Controlador son roles de nuestras clases dentro del proyecto, no significa que obligatoriamente las clases o directorios se tengan que llamar asi, pero se mantienen los nombres de los directorios directorios para claridad y organizacion
    - Por ejemplo, la clase que llamamos `LoQueQuieran` es un modelo en este proyecto
    - Es una convencion comun llamar a los controladores `SomethingController` en ingles o `ControladorAlgo` en español
    - Lo mismo para las vistas, dependiendo del tipo de componente: `PlayersPanel`, `PanelJugadores`, `VentanaAlgo`, `SomethingFrame`
    
### Notas:

- El flujo es
    - Controlador agrega escuchadores a los componentes visuales
    - Usuario interactua con la vista
    - Los escuchadores reaccionan a los inputs del usuario
    - El controlador a traves del codigo incluido en los escuchadores interactua con el modelo: consulta o escribe datos
    - Luego de modificar y/o leer los datos, los envia devuelta a la vista
- Siempre manejar pares `Vista` + `Controlador`, la relacion deber ser 1 Vista + 1 controlador
- La ventana que contiene todo suele ser una sola, una clase que hereda de JFrame, lo unico que se cambia es el panel que se ve  en un determinado momento
- Una vista generalmente es una clase que hereda de JPanel y tiene un proposito especifico, por ejemplo, ser la pantalla de menu
- Un controlador puede usar varias instancias de Modelo, pero siempre esta pensado para trabajar con una unica clase de Vista especifica
- Los controladores pueden hablar entre si: Un ControladorA puede tener un atributo de tipo ControladorB y pasarle y recibir informacion o instrucciones

### Como ejecutar

El punto de entrada es `src/App.java`.

Desde la raiz del proyecto (mismo comando en bash y PowerShell):

```
javac -d out -sourcepath src src/App.java
java -cp out App
```

`-sourcepath src` le indica a `javac` la raiz del arbol de paquetes: al compilar `App.java` sigue sus `import` y compila `controlador/Controlador.java`, `modelo/LoQueQuieran.java` y `vista/Vista.java` de forma transitiva, sin tener que enumerarlos a mano.

