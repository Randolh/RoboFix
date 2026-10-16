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
