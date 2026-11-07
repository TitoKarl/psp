**# README.md**

**## Reto 3 · Motor Concurrente en UDITflix**

**\*\*Modulo:\*\*** 0490 · Programacion de Servicios y Procesos (2º DAM)  
**\*\*RA vinculado:\*\*** RA2 · Programacion de aplicaciones multihilo  
**\*\*Prueba realizada:\*\*** ☑ B · El Optimizador de Descarga Masiva de Assets  
**\*\*Autor/a:\*\*** Juan Carlos Rodriguez

**## 📺 1. Resumen del problema**

La aplicacion de UDITflix necesita descargar varios modulos al iniciar. Para reducir el tiempo de espera, el programa utiliza varios hilos que trabajan al mismo tiempo. Los hilos se guardan en un ArrayList, se ejecutan con start() y el programa espera a que terminen mediante join().

**## 🧠 2. Antes de empezar: planifico**

\- **\*\*¿Que parte de la base v0 de clase voy a reutilizar tal cual?\*\***  
La creacion y ejecucion de hilos.

\- **\*\*¿Que es nuevo y me da mas respeto?\*\***  
Controlar varios hilos al mismo tiempo.

\- **\*\*Mi plan en 4-5 pasos, en orden:\*\***
  - Crear los modulos de descarga.
  - Guardar los hilos en un ArrayList.
  - Asignar prioridades.
  - Lanzar los hilos.
  - Esperar a que terminen.

**## 🏗️ 3. Arquitectura de la solucion**

Los cuatro hilos realizan descargas distintas y se ejecutan en paralelo. Cuando todos terminan, se habilita el login.

**### Hilos utilizados**

\- WORKER-1: catalogo y metadatos.
\- WORKER-2: sesion y perfil.
\- WORKER-3: portadas y recursos graficos.
\- WORKER-4: analiticas.

**## 🚀 4. Como ejecutar el proyecto**

\- Abrir el proyecto en IntelliJ IDEA.
\- Ejecutar la clase Main.
\- Utilizar JDK 17 o superior.

**## 🔍 5. Mientras programo: mi diario de decisiones**

\| Que intentaba | Que paso realmente | Que aprendi |
\|--------------|-------------------|-------------|
\| Ejecutar varios hilos | Funciono correctamente | Aprendi a usar start() y join() |
\| Medir tiempos | Los valores cambiaban | Las latencias son aleatorias |

**## 🧠 7. Preguntas de metacognicion tecnica**

**\*\*1. ¿Por que el tiempo total es el del hilo mas lento y no la suma de todos?\*\***  
Porque todos trabajan al mismo tiempo.

**\*\*2. ¿Que diferencia real hay entre llamar a start() y llamar a run()?\*\***  
start() crea un hilo nuevo y run() no.

**\*\*3. ¿Que pasaria si quitaras los join()? ¿Y si pusieras el join() justo despues de cada start()?\*\***  
Sin join() el programa seguiria antes de tiempo y con join() seria secuencial.

**\*\*4. ¿Que diferencias hay entre trabajar con duraciones fijas y con latencias aleatorias?\*\***  
Las fijas siempre dan el mismo resultado y las aleatorias cambian.

**\*\*5. ¿El orden en que terminan los hilos puede cambiar entre ejecuciones?\*\***  
Si, porque cada hilo tarda un tiempo distinto.

**\*\*6. ¿Que problema te costo mas depurar y como lo resolviste?\*\***  
Coordinar correctamente start() y join().

**\*\*7. Si en lugar de 4 tareas fueran 500, ¿seguiria teniendo sentido crear un Thread por tarea?\*\***  
No, porque consumiria demasiados recursos.

**## 🌍 8. Mundo real**

Este tipo de programacion se utiliza en aplicaciones como Netflix o Spotify para cargar varios recursos a la vez.

**## 📂 11. Estructura del proyecto**

\`\`\`text
src/main/java/org/example/
└── Main.java

README.md
\`\`\`