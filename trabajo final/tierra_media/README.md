# Proyecto: Tierra Media - Batalla Táctica

## 1. Integrantes del Equipo 

- Luna, Luis 
- Vega, Alejandro 

## 2. Dominio y Alcance del Sistema 

### Descripción del Problema
Se busca desarrollar una aplicación de escritorio de un videojuego de tipo **RPG Táctico por turnos** ambientado en la mitología de **El Señor de los Anillos**. El jugador tomará el control de una escuadra de Héroes de la Tierra Media (guerreros, arqueros, magos) que deberán enfrentarse strategicamente a las hordas enemigas de Sauron en un mapa dividido en cuadrículas. 

### Objetivo del Sistema
El sistema será un juego funcional, interactivo y extensible que permitirá experimentar combates tácticos por turnos. El diseño aplicará rigurosamente la arquitectura **MVC**, los principios de la Programación Orientada a Objetos (POO) y patrones de diseño para facilitar la incorporación de nuevos personajes, habilidades, mapas y persistencia de datos.

### Funcionalidades Principales (Features)

- **Gestión de Personajes y Clases:**
  - Control de unidades heroicas con atributos propios (Vida, Ataque, Defensa, Rango de Movimiento y Ataque).
  - Variedad de roles con comportamientos únicos (ej. Guerrero cuerpo a cuerpo, Arquero a distancia, Mago en área).
- **Sistema de Combate Táctico por Turnos:**
  - Mapa en cuadrícula donde las unidades se desplazan según su capacidad de movimiento.
  - Gestión de turnos alternados entre la escuadra del jugador y la IA / control enemigo.
  - Cálculo de daño dinámico considerando defensa, alcance y tipo de ataque.
- **Creación Dinámica de Enemigos:**
  - Hordas de enemigos (Orcos, Uruk-hai, Nazgûl) instanciadas mediante patrones de diseño según la dificultad del nivel.
- **Interfaz Gráfica (IGU):**
  - Visualización del tablero táctico y las unidades desplegadas.
  - Panel de control para seleccionar acciones (Mover, Atacar, Pasar Turno).
  - Indicadores visuales de estado (barras de vida, turno activo y registro de combate).
- **Persistencia de Datos:**
  - Guardado y consulta del historial de batallas y mejores puntuaciones (High Scores) en una base de datos SQLite.

## 3. Arquitectura y Diseño 

### Patrón de Diseño Adicional: Factory Method
- **Factory Method:** Se utilizará para la instanciación desacoplada de los diferentes tipos de enemigos (`OrcoFactory`, `UrukHaiFactory`), permitiendo agregar nuevas criaturas sin modificar la lógica principal de la oleada/nivel.
- **Observer:** Se empleará para notificar los cambios en el modelo (movimientos, actualización de vida, cambio de turno) directamente a la interfaz gráfica sin acoplar la vista con la lógica de negocio.

### Diagramas de Diseño

#### **Diagrama de Clases UML (Conceptual)**


#### **Prototipo de la IGU (Wireframe)**

## 4. Stack Tecnológico 

- **Lenguaje:** Java 17
- **IDE:** Visual Studio Code
- **Base de Datos:** SQL Lite
- **Framework de IGU:** Java Swing
- **Control de Versiones:** Git


