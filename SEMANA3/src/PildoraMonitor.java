import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PildoraMonitor {

    public static void main(String[] args) {
        System.out.println("===MONITOR UDITFLIX===");
        System.out.println("Combrobando servicio...");

        // TRY CATCH
        // Lanzar un programa externo o esperar a que termine PUEDE FALLAR
        // try -> "Si algo sale mal, ha esto en otro en vez de romper el programa"
        try {
        // ProcessBuilder es el "encargado" que prepara la orden que
        // le daremos al sistema operativo. Es como rellenar un formulario
        // "ping" -> el programa que queremos ejecutar
        // "-n" -> opcion de Windows: numero de intentos
        // "1! -> haz solo 1 intento (asi va mas rapido)
        // "127.0.0.1" es a quien hacemos ping
        //              (siempre responde, simula un servicio ACTIVO)
        // OJO "-n" solo vale en windows. Linux y Mac seria "-c"
            ProcessBuilder pb = new ProcessBuilder(
                  "ping", "-n", "1", "127.0.0.1"
            );

            // PASO 2: UNIR los dos canales de salida
            // TODO PROGRAMA TIENE DOS CANALES POR LOS QUE HABLA
            // - salida normal (lo que funciona bien)
            // - salida de error (los mensajes de fallo)
            // Con redirectErrorStream(true) lo juntamos en UNO SOLO
            // Asi, leyendo un unico canal vamos TODO lo que el proceso diga,
            // sea un resultado o un error
            pb.redirectErrorStream(true);

            // PASO 3: LANZAR EL PROCESO
            // start() es el boton de "enviar", Ahora si el sistema operativo
            // crea un programa nuevo (ping) que corre por su cuenta
            // con su propia memoria
            // Process es el objeto con el que controlamos ese programa

            Process proceso = pb.start();

            // PASO 4: MOSTRAR EL PID
            // PID = Process IDentifier, es el  "DNI" del proceso

            System.out.println("PID.´" + proceso.pid());

            // PASO 5: PREPARAR la lectura de lo que dice el proceso

            // El proceso ping escribe su propia consola, que java No ve,
            // Para escularlo nos "conectamos" a su salida con una cadena
            // proceso.getInputStream() -> la "tuberia" por la que sale
            // el texto del proceso ( en bytes ).
            // new InputStreamReader(...) -> traduce esos bytes a letras.
            // new Buffered(...) -> nos deja leer linea a linea

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );
            // Variable donde guardamos cada linea que vayamos leyendo
            // Todavia esta vacia
            String linea;

            // PASO 6: LEER todo lo que el proce so va escribiendo
            while ((linea = lector.readLine()) != null){
                System.out.println(linea);
            }
            // PASO 7 : ESPERAR a que el proceso termine
            // waitFor() es lo que nos garantiza el codigo de salida
            int codigo = proceso.waitFor();

            // PASO 8 : INTERPRETAR EL resultado
            if (codigo == 0){
                System.out.println("ESTADO : SERVICIO ACTIVO");
            } else {
                System.out.println("ESTADO : SERVICIO CON ERROR");
            }
        } catch (IOException e){
            System.out.println("No se pudo lanzar el procesto");
        } catch (InterruptedException e){
            System.out.println("La ejecucion fue interrumpida");
        }
    }
}
