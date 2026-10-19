package org.example;

public class TareaPrueba implements Runnable{

    // Nombre que identifica la tarea, ejm HILO-A
    private final String nombre;

    public TareaPrueba(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Repetimos el bloque tres veces
        for(int i = 1; i < 3; i++) {
            // Mostrar el nombre de la tarea
            // el hilo que ejecuta esta instrucion y el paso
            // Con start(), podemos ver el HILO-A o HILO-B
            // Con run() llamado desde main, veremos "main".

            System.out.println(
                    nombre + " [" +
                            Thread.currentThread() +
                            "] - Paso: " + i
            );

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
