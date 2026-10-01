import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedTransferQueue;

public class main {

    public static void main(String[] args) {
        System.out.println("======================");
        System.out.println("UDITFLIX - CATALOGO");
        System.out.println("======================");

        // TRY CATCH
        // Lanzar un programa externo o esperar a que termine PUEDE FALLAR
        // try -> "Si algo sale mal, ha esto en otro en vez de romper el programa"
        String[][] videos ={
                {"Animación 3D", "127.0.0.0"},
                {"Videojuegos", "127.0.0.0"},
                {"Kotlin", "127.0.0.0"},
                {"Android", "127.0.0.1"},
                {"Flutter", "127.0.0.1"}
        };
        // Bucle para recorrer el codigo
        for (int i = 0; i < 5 ; i++) {

            System.out.println("[VIDEO] " + videos[i][0]);

        try {
            // Crear e iniciar el proceso
            ProcessBuilder pb = new ProcessBuilder(
                    "ping", "-n", "1", videos[i][1]
            );
            pb.redirectErrorStream(true);
            Process proceso = pb.start();
            System.out.println("PID: " + proceso.pid());
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );
            // Muestra el estado del proceso
            int codigo = proceso.waitFor();
            if (codigo == 0){
                System.out.println("ESTADO : ACTIVO");
            } else {
                System.out.println("ESTADO : CAIDO");
            }
            // Muestra si hay errores
        } catch (IOException e){
            System.out.println("No se pudo lanzar el procesto");
        } catch (InterruptedException e){
            System.out.println("La ejecucion fue interrumpida");
        }

        }
        System.out.println("======================");
        System.out.println("COMPROVACION FINALIZADA");
        System.out.println("======================");
    }
}
