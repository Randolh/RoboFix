# Registro de Cambios - RoboFix

Resumen simple de los cambios realizados en el proyecto y el motivo de cada uno.

---

### 1. Paquetes `robot` y `excepcion`
- **Que se hizo:** Se crearon las carpetas `randolh.robofix.robot` (para `Robot` y `ModoRobot`) y `randolh.robofix.excepcion` (para errores personalizados).
- **Por que:** Para no tener todas las clases revueltas en un solo lugar y mantener el proyecto ordenado.

---

### 2. Enum `ModoRobot`
- **Que se hizo:** Se creo un enum con los unicos tres modos permitidos: `NORMAL`, `AHORRO` y `MANTENIMIENTO`.
- **Por que:** Para evitar que el usuario escriba cualquier palabra inventada y asegurar que solo se usen modos validos.

---

### 3. Excepcion `ConfiguracionRobotException`
- **Que se hizo:** Se creo un error personalizado usando `throw` para cuando se ingresen datos invalidos en el robot.
- **Por que:** Para avisar de forma clara que fallo (por ejemplo: energia fuera de rango o modo desconocido) en lugar de que el programa falle sin explicacion.

---

### 4. Validaciones en `Robot`
- **Que se hizo:** 
  - La energia solo puede estar entre 0 y 100.
  - Se creo el metodo `actualizarConfiguracion(energia, modo)` que revisa que ambos datos sean correctos antes de guardarlos.
  - Los constructores reutilizan este metodo para no repetir codigo.
- **Por que:** Para que el robot no termine con energia negativa o superior a 100, y para evitar que quede a medio configurar si uno de los datos esta mal.

---

### 5. Control de errores en `Main`
- **Que se hizo:**
  - Se agrego `leerEntero` para leer numeros de forma segura.
  - Se agrego un `try-catch` al cambiar la configuracion del robot.
- **Por que:** Para que el programa no se caiga si el usuario escribe letras en el menu, y para mostrar el mensaje de error conservando los datos anteriores si la configuracion falla.

---

### 6. Patron Singleton en `Inventario`
- **Que se hizo:**
  - Se creo el paquete `randolh.robofix.inventario`.
  - Se hizo privado el constructor de `Inventario` y se creo el metodo `getInstancia()`.
  - `Taller` ahora obtiene el inventario compartido mediante `Inventario.getInstancia()`.
- **Por que:** Para que todos los talleres compartan la misma lista de repuestos en lugar de tener inventarios separados y desincronizados.

---

### 7. Validaciones de Stock y `InventarioException`
- **Que se hizo:**
  - Se creo la excepcion personalizada `InventarioException`.
  - El metodo `descontar` valida que el repuesto exista, que la cantidad sea positiva y que haya stock suficiente antes de restar.
  - En `Taller.reparar` se captura la excepcion si no hay repuestos o si la energia no alcanza, avisando del problema en lugar de fingir exito.
- **Por que:** Para evitar que el inventario quede con numeros negativos o se descuenten repuestos inexistentes (cumpliendo las pruebas P3, P4 y P5).

---

### 8. Patron Adapter para `EscanerAntiguo`
- **Que se hizo:**
  - Se creo el paquete `randolh.robofix.diagnostico`.
  - Se creo el modelo `ResultadoDiagnostico` con los datos limpios y organizados.
  - Se creo la interfaz `ServicioDiagnostico` y la clase adaptadora `AdaptadorEscaner`.
  - La clase `EscanerAntiguo` se mantuvo 100% intacta sin modificar ninguna linea.
  - `Taller` ahora usa el adaptador en lugar de llamar directamente al escaner o partir texto con barras (`|`).
- **Por que:** Para cumplir con el desacoplamiento requerido: si el formato del escaner viejo cambia o viene roto, solo el adaptador se encarga de interpretarlo o avisar del error mediante `DiagnosticoException`, protegiendo el resto de la aplicacion (cumpliendo las pruebas P2 y P12).

---

### 9. Conversion a Checked Exceptions (Obligatorias por Compilador)
- **Que se hizo:**
  - Las excepciones `ConfiguracionRobotException`, `InventarioException` y `DiagnosticoException` ahora heredan directamente de `Exception`.
  - Se agregaron las clausulas `throws` correspondientes en las firmas de los metodos.
  - Los llamados en `Taller` y `Main` manejan obligatoriamente estas excepciones en bloques `try-catch`.
- **Por que:** Para obligar al compilador a exigir el manejo explícito de errores donde se usen estos métodos, garantizando robustez total y evitando que cualquier error pase desapercibido.

---

### 10. Patron Memento para Guardar y Restaurar Configuraciones
- **Que se hizo:**
  - Se creo la clase `RobotMemento` en `randolh.robofix.robot` para encapsular de forma inmutable la energia y el modo.
  - Se agregaron los metodos `guardar()` y `restaurar(memento)` en `Robot`.
  - Se agregaron las opciones `7. Guardar configuracion` y `8. Restaurar configuracion` en el menu de `Main`, dejando `9. Salir` al final.
  - Se valido que si no existe una copia previa guardada, se informe al usuario en lugar de fallar.
- **Por que:** Para permitir al usuario crear un punto de respaldo de la configuracion del robot y volver a el en caso de cambios no deseados o errores, sin alterar nunca el codigo ni el nombre del robot (cumpliendo las pruebas P9 y P10).
