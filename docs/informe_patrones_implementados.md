# Informe de Patrones Implementados - RoboFix

Este documento presenta la justificacion de los tres patrones de diseño aplicados en el taller y la conclusion tecnica sobre las mejoras y limitaciones del sistema.

---

## 1. Justificacion de los Patrones de Diseño

### Patron 1: Singleton (Centralizacion del Inventario)
- **Problema original:** Cada vez que se creaba un objeto `Taller`, este ejecutaba `new Inventario()`, generando copias separadas en memoria. Como resultado, si un taller descontaba una bateria, el otro taller no se enteraba y mantenia el stock desfasado, permitiendo inconsistencias en la disponibilidad de repuestos.
- **Solucion aplicada:** Se cerro el constructor publico de `Inventario` haciendolo `private` y se implemento un metodo estatico `getInstancia()`, asegurando que exista unicamente un objeto `Inventario` en toda la ejecucion de la aplicacion.
- **Por que corresponde al patron:** Cumple con la definicion clasica de Singleton al garantizar la existencia de una sola instancia de una clase y proporcionar un punto de acceso global y controlado a ella, resolviendo de raiz el problema de inventarios desincronizados.

---

### Patron 2: Adapter (Integracion del Escaner Antiguo)
- **Problema original:** El aplicativo dependia de una clase externa (`EscanerAntiguo`) que entregaba los resultados en un formato de texto plano delimitado por barras verticales (`|`). La clase `Taller` contenia codigo duplicado para partir ese texto (`split`) y asumia ciegamente la presencia de cuatro campos, rompiendose ante cualquier cambio o dato malformado.
- **Solucion aplicada:** Se diseñó una interfaz moderna (`ServicioDiagnostico`) y una clase adaptadora (`AdaptadorEscaner`) que envuelve a `EscanerAntiguo` sin tocar una sola linea de su codigo. El adaptador recibe el texto, lo valida, maneja errores y lo transforma en un objeto estructurado (`ResultadoDiagnostico`).
- **Por que corresponde al patron:** Permite que dos interfaces incompatibles trabajen juntas: adapta la interfaz obsoleta del escaner a la interfaz moderna que espera el taller, desacoplando completamente la logica de negocio del formato del proveedor externo.

---

### Patron 3: Memento (Guardar y Restaurar Configuraciones)
- **Problema original:** Cuando se modificaba la energia o el modo de operacion de un robot, los cambios sobreescribian directamente los atributos del objeto. Si el cambio se hacia por error o la reparacion requeria volver al estado inicial, no habia manera de recuperar la configuracion anterior.
- **Solucion aplicada:** Se creo la clase inmutable `RobotMemento` junto con los metodos `guardar()` y `restaurar(memento)` en la clase `Robot`. Desde el menu se agregaron las opciones 7 y 8 para respaldar y recuperar el estado sin alterar el codigo ni el nombre del robot.
- **Por que corresponde al patron:** Captura y externaliza el estado interno de un objeto (energia y modo) sin violar el principio de encapsulamiento, permitiendo que el robot sea restaurado a ese estado posteriormente sin exponer sus variables internas.

---

## 2. Conclusion Tecnica

### Que mejoro en el diseño:
1. **Desacoplamiento y Modularidad:** Cada clase tiene una responsabilidad unica y clara (SRP). El modelo de datos, la gestion de inventario, el diagnostico y las excepciones estan organizados en paquetes independientes (`robot`, `inventario`, `diagnostico`, `excepcion`).
2. **Robustez y Tolerancia a Fallos:** Se erradicaron las caidas del programa por texto no numerico (`InputMismatchException`), indices fuera de rango (`ArrayIndexOutOfBoundsException`) y referencias nulas (`NullPointerException`).
3. **Consistencia en Reglas de Negocio:** El sistema ahora valida que la energia este entre 0 y 100, que los modos sean unicamente los permitidos (`NORMAL`, `AHORRO`, `MANTENIMIENTO`) y que las piezas no bajen a numeros negativos.
4. **Respeto a componentes externos:** Se integro el escaner antiguo sin modificar su implementacion, cumpliendo con la premisa de biblioteca externa intocable.

### Limitaciones que todavia presenta el aplicativo:
1. **Persistencia volatil (en memoria):** Toda la informacion (el inventario, el robot y las copias guardadas con Memento) reside en la memoria RAM. Al cerrar el programa con la opcion 9 (Salir), los datos se pierden y al reiniciar vuelven a sus valores iniciales.
2. **Un solo robot en ejecucion:** El sistema actual solo manipula un unico robot creado en el `main`. Para un entorno real de taller se requeriria una lista o mapa de robots para gestionar multiples clientes y modelos simultaneamente.
3. **Historial de respaldo simple:** El patron Memento implementado solo guarda un unico respaldo a la vez. No permite tener un historial secuencial con multiples puntos de recuperacion ("deshacer/rehacer").
4. **Interfaz por consola secuencial:** La aplicacion depende de una lectura sincronica por consola mediante `Scanner`, lo cual no soporta operaciones concurrentes o remotas de multiples usuarios.
