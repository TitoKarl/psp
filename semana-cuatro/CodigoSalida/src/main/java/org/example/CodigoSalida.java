package org.example;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.sql.SQLOutput;

public class CodigoSalida {
    public static void main(String[] args){
        System.out.println("===========================");
        System.out.println("COMPROVACION DE SERVIDOR");
        System.out.println("===========================");

        try {
            // 1 PREPARAMOS EL PROCESO EXTERNO
            // Vamos a ejecutar el comando "ping"
            // En Windows: pin -n 1 8.8.8.8
            // -n 1 -> realiza solo 1 comprobacion
            // 8.8.8.8 -> direccion
            ProcessBuilder pb = new  ProcessBuilder(
                    "ping",
                    "-n",
                    "1",
                    "8.8.8.8"
            );
            //2. LANZAMOS EL PROCESO
            // start() ejecuta el proceso externo
            // El resultado de start() es un objeto Process
            Process proceso = pb.start();

            //3. OBTENEMOS EL PID
            //pid() nos permite conocer el identificador
            System.out.println("PID: " + proceso.pid());

            //4. ESPERAMOS A QUE TERMINE
            //waitFor() detiene nuestro programa java
            // hasta que el proceso externo termina
            // Ademas, devuelve un numero entero

            int codigoSalida = proceso.waitFor();

            //5. MOSTRAMOS EL CODIGO
            System.out.println("Codigo de salida: " + codigoSalida);

            //6. INTERPETAMOS EL RESULTADO
            // codigo 0 - resultado correcto
            // otro codigo -  resultado no satisfactorio

            if (codigoSalida == 0){
                System.out.println("ESTADO: ACTIVO");
            } else {
                System.out.println("ESTADO: CAIDO");
            }
        } catch (IOException e) {
            System.out.println("ERROR AL LANZAR EL PROCESO");
        } catch (InterruptedException e) {
            System.out.println("EL PROCESO FUE INTERRUMPIDO");
        }

        System.out.println("===========================");
        System.out.println("FIN DE LA COMBROBACION");
        System.out.println("===========================");
    }
}
