# Pruebas Iniciales - RoboFix

En este documento se registran las pruebas realizadas al programa inicial, respondiendo las preguntas de la seccion 3.1 del taller y explicando los errores encontrados de forma clara y sencilla.

---

## 1. Respuestas a las Preguntas del Taller (Seccion 3.1)

### 1. Que pasa si ingresas una energia negativa?
- **Lo que paso al probarlo:** Escribimos el valor `-50` en la opcion de cambiar configuracion y el programa lo guardo sin problema.
- **El problema:** La energia no deberia ser negativa ni pasar de 100. El robot termina con una energia que no tiene sentido.

### 2. Que pasa si cambias el modo a un valor que no existe?
- **Lo que paso al probarlo:** Escribimos `"INVENTADO"` como nuevo modo y el sistema lo acepto.
- **El problema:** El sistema solo deberia permitir tres modos: `NORMAL`, `AHORRO` o `MANTENIMIENTO`. Si el usuario escribe cualquier otra palabra, el programa la guarda sin revisar.

### 3. Que pasa si reparas varias veces el robot?
- **Lo que paso al probarlo:** Reparamos el robot 5 veces seguidas. 
  - La cantidad de baterias en el inventario empezo en 2 y termino en `-3`.
  - La energia del robot bajo de 80 a `-20`.
  - Cada vez que reparamos, el programa mostro el mensaje: "Robot reparado correctamente."
- **El problema:** 
  - El taller sigue gastando repuestos aunque ya no haya ninguno disponible.
  - La reparacion le quita 20 puntos de energia al robot cada vez, en lugar de arreglarlo.
  - El mensaje dice que todo salio bien, cuando en realidad el robot se quedo sin energia y el inventario quedo en numeros negativos.

### 4. Los dos talleres comparten realmente el inventario?
- **Lo que paso al probarlo:** Al hacer una reparacion en el taller 1, la bateria del taller 1 bajo a 1, pero en el taller 2 la bateria se quedo en 2.
- **El problema:** Cada taller tiene su propia lista de repuestos por separado. Si un taller gasta una pieza, el otro no se entera. Deberian usar una sola lista compartida.

### 5. Como podrias recuperar la configuracion anterior del robot?
- **Lo que paso al probarlo:** Si cambias la energia o el modo por error, no hay ningun boton ni opcion para volver atras.
- **El problema:** No existe ninguna forma de guardar una copia del estado del robot antes de hacer cambios para poder restaurarla despues.

---

## 2. Otros Errores Encontrados en el Programa

### 1. El programa se cierra si escribes letras en lugar de numeros
Si en el menu o al pedir la energia escribes una letra (como "abc"), el programa se cae con un error en la consola y se cierra de golpe. Falta validar lo que escribe el usuario.

### 2. Se cae el programa al reparar robots desconocidos
Si se intenta reparar un robot que no sea el `RX-01`, el sistema busca un repuesto llamado `SISTEMA`, el cual no existe en la lista de repuestos. Como no lo encuentra, el programa se detiene por un error.

### 3. El texto del escaner esta pegado al codigo
El taller lee directamente el texto con barras (`|`) que entrega el escaner viejo. Si el escaner manda un texto con formato incompleto o diferente, el programa se rompe al intentar leerlo. Se necesita un adaptador que ordene esa informacion antes de usarla.

### 4. Todas las clases estan en un solo archivo
Todo el codigo del proyecto esta metido en un solo archivo llamado `Main.java`. Para que el proyecto este ordenado y sea facil de entender, cada clase deberia estar en su propio archivo y organizada en carpetas.
