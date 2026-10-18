# RoboFix - Taller de Refactorizacion y Patrones de Diseño en Java

Proyecto academico de refactorizacion de software y aplicacion de patrones de diseño GoF (Gang of Four) sobre una aplicacion de consola en Java para la gestion y reparacion de robots.

---

## Tabla de Contenidos

1. [Descripcion del Proyecto](#descripcion-del-proyecto)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Patrones de Diseño Implementados](#patrones-de-diseño-implementados)
4. [Correcciones y Reglas de Negocio](#correcciones-y-reglas-de-negocio)
5. [Clonacion y Ejecucion](#clonacion-y-ejecucion)
6. [Opciones del Menu](#opciones-del-menu)
7. [Entregables y Documentacion](#entregables-y-documentacion)

---

## Descripcion del Proyecto

El proyecto original consistia en un unico archivo monolito (`Main.java`) con defectos funcionales, clases acopladas, inventarios inconsistentes, diagnosticos incompatibles y falta de validaciones.

El objetivo fue rescatar y refactorizar el sistema organizandolo en paquetes desacoplados bajo Maven, aplicando tres patrones de diseño fundamentales (Singleton, Adapter y Memento), validando las reglas de negocio e integrando un manejo robusto de excepciones sin alterar las bibliotecas externas simuladas (`EscanerAntiguo`).

---

## Estructura del Proyecto

El codigo fuente esta organizado bajo el estandar Maven en el paquete base `randolh.robofix`:

```text
RoboFix/
|-- pom.xml
|-- docs/
|   |-- informe_patrones_implementados.md
|   |-- pruebas_de_aceptacion.md
|   |-- pruebas_iniciales.md
|   |-- registro_cambios.md
|   `-- screenshots/
`-- src/
    `-- main/
        `-- java/
            `-- randolh/
                `-- robofix/
                    |-- EscanerAntiguo.java
                    |-- Main.java
                    |-- Taller.java
                    |-- diagnostico/
                    |   |-- AdaptadorEscaner.java
                    |   |-- ResultadoDiagnostico.java
                    |   `-- ServicioDiagnostico.java
                    |-- excepcion/
                    |   |-- ConfiguracionRobotException.java
                    |   |-- DiagnosticoException.java
                    |   `-- InventarioException.java
                    |-- inventario/
                    |   `-- Inventario.java
                    `-- robot/
                        |-- ModoRobot.java
                        |-- Robot.java
                        |-- RobotMemento.java
```

---

## Patrones de Diseño Implementados

### 1. Patron Singleton (Modulo de Inventario)
- **Problema previo:** Cada instancia de `Taller` instanciaba un `new Inventario()`, generando inventarios duplicados e inconsistentes en memoria.
- **Solucion:** Se implemento la clase `Inventario` con un constructor privado y un metodo estatico `getInstancia()` (Eager Initialization).
- **Resultado:** Todos los talleres consultan y modifican de forma segura una unica fuente de verdad de repuestos.

### 2. Patron Adapter (Modulo de Diagnostico)
- **Problema previo:** La clase `EscanerAntiguo` devuelve una cadena de texto en formato crudo separado por barras (`RX-01|BATERIA|CRITICO|15`), mientras el sistema necesitaba un objeto tipado. Dicha clase no podia modificarse por simular ser una biblioteca de terceros intocable.
- **Solucion:** Se definio la interfaz `ServicioDiagnostico` y se implemento la clase `AdaptadorEscaner`, la cual envuelve al escaner antiguo, procesa la cadena delimitada y retorna una instancia limpia de `ResultadoDiagnostico`.
- **Resultado:** Se desacoplo el taller de la implementacion antigua del escaner sin modificar una sola linea de `EscanerAntiguo.java`.

### 3. Patron Memento (Modulo de Configuracion del Robot)
- **Problema previo:** Cuando se cambiaba la configuracion de un robot a valores erroneos o no deseados, no existia una forma confiable de revertir el estado anterior.
- **Solucion:** Se creo la clase inmutable `RobotMemento` que almacena una instantanea de la energia y del modo. La clase `Robot` actua como Originator con los metodos `guardar()` y `restaurar(RobotMemento)`. La clase `Main` actua como Caretaker almacenando la referencia del snapshot.
- **Resultado:** Es posible respaldar el estado antes de una operacion riesgosa y restaurarlo de manera inmediata sin exponer los campos internos del robot ni alterar su codigo o nombre.

---

## Correcciones y Reglas de Negocio

Ademas de los patrones, se resolvieron los defectos del aplicativo inicial:

1. **Rango de Energia:** La energia del robot ahora se valida estrictamente en el rango de 0 a 100.
2. **Modos Permitidos:** Se implemento el enum `ModoRobot` para admitir unicamente `NORMAL`, `AHORRO` y `MANTENIMIENTO`.
3. **Actualizacion Atomica:** Los cambios de configuracion se validan de forma integral antes de asignarse. Si uno de los valores es invalido, la configuracion previa se preserva intacta.
4. **Proteccion contra Stock Negativo:** El metodo `descontar` verifica que el repuesto exista y que la cantidad disponible sea suficiente antes de realizar cualquier decremento.
5. **Reparacion Segura:** La reparacion verifica el diagnostico y las piezas antes de descontar inventario, impidiendo reparaciones parciales o falsos reportes de exito.
6. **Manejo de Entrada por Consola:** El menu captura entradas no numericas sin colapsar el programa y mantiene una separacion visual clara entre opciones y respuestas.

---

## Clonacion y Ejecucion

### Requisitos del Sistema
- Java Development Kit (JDK) 8 o superior (probado en JDK 21).
- Apache NetBeans IDE (version 12 o superior recomendada).
- Git.
- Apache Maven (integrado por defecto en NetBeans).

---

### Paso 1: Clonar el Repositorio

Abre una terminal y clona el proyecto con el siguiente comando:

```bash
git clone https://github.com/Randolh/RoboFix.git
cd RoboFix
```

---

### Paso 2: Abrir y Ejecutar en Apache NetBeans

1. **Abrir NetBeans:** Inicia el entorno Apache NetBeans IDE.
2. **Abrir el proyecto:**
   - En el menu superior, ve a `File` -> `Open Project...` (o presiona `Ctrl + Shift + O`).
   - Navega hasta la carpeta clonada y selecciona la carpeta del proyecto `RoboFix` (NetBeans la reconocera automaticamente como proyecto Maven).
   - Haz clic en `Open Project`.
3. **Compilar el proyecto:**
   - En la pestana de la izquierda (`Projects`), haz clic derecho sobre el proyecto `RoboFix` y selecciona `Clean and Build`.
4. **Ejecutar la aplicacion:**
   - Haz clic derecho sobre el proyecto `RoboFix` y selecciona `Run` (o presiona la tecla `F6` / boton verde de Play).
   - La aplicacion se iniciara en la ventana de salida (`Output`) de NetBeans, permitiendo ingresar las opciones e interactuar con el menu.

---

## Opciones del Menu

Al ejecutar la aplicacion se presenta el siguiente menu interactivo:

```text
===== ROBOFIX =====
1. Ver robot
2. Diagnosticar robot
3. Reparar robot
4. Cambiar configuracion
5. Ver inventario
6. Comparar inventarios
7. Guardar configuracion
8. Restaurar configuracion
9. Salir
```

---

## Entregables y Documentacion

Todos los documentos requeridos por la guia de entrega se encuentran redactados en formato Markdown dentro del directorio `docs/`:

1. **Diagnostico Inicial y Defectos:**
   - [docs/pruebas_iniciales.md](docs/pruebas_iniciales.md): Respuestas a las preguntas de exploracion de codigo y detalle de causas y consecuencias de los defectos detectados.
2. **Informe de Patrones y Conclusion Tecnica:**
   - [docs/informe_patrones_implementados.md](docs/informe_patrones_implementados.md): Justificacion de cada patron implementado, mejoras en el diseño y limitaciones remanentes del aplicativo.
3. **Pruebas de Aceptacion (P1 a P12):**
   - [docs/pruebas_de_aceptacion.md](docs/pruebas_de_aceptacion.md): Matriz de pruebas de aceptacion, salidas de consola y capturas de evidencia embebidas para cada escenario.
4. **Registro de Cambios:**
   - [docs/registro_cambios.md](docs/registro_cambios.md): Bitacora de cambios por componente, indicando el problema, la solucion y su justificacion.
