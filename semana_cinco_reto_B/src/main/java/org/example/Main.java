
package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        long inicio = System.currentTimeMillis();

        try {
            System.out.println("INICIANDO EJECUCION PARALELA");

            System.out.println("Lanzando proceso 1: 127.0.0.1");
            Process p1 = new ProcessBuilder("ping", "-n", "1", "-w", "3000", "127.0.0.1").start();

            System.out.println("Lanzando proceso 2: 192.0.2.1");
            Process p2 = new ProcessBuilder("ping", "-n", "1", "-w", "3000", "192.0.2.1").start();

            int code1 = p1.waitFor();
            int code2 = p2.waitFor();

            System.out.println("Codigo de salida 1: " + code1);
            System.out.println("Codigo de salida 2: " + code2);

            int intentos1 = 0;
            int intentos2 = 0;

            while (code1 != 0 && intentos1 < 3) {
                intentos1++;
                System.out.println("Reintento " + intentos1 + "/3 para 127.0.0.1");

                Process reintento1 = new ProcessBuilder(
                        "ping", "-n", "1", "-w", "3000", "127.0.0.1"
                ).start();

                code1 = reintento1.waitFor();
                System.out.println("Codigo de salida 1: " + code1);
            }

            while (code2 != 0 && intentos2 < 3) {
                intentos2++;
                System.out.println("Reintento " + intentos2 + "/3 para 192.0.2.1");

                Process reintento2 = new ProcessBuilder(
                        "ping", "-n", "1", "-w", "3000", "192.0.2.1"
                ).start();

                code2 = reintento2.waitFor();
                System.out.println("Codigo de salida 2: " + code2);
            }

            if (code1 == 0 && code2 == 0) {
                System.out.println("Ambos servidores responden. Abriendo Bloc de notas.");
                new ProcessBuilder("notepad.exe").start();
            } else {
                System.out.println("Algun servidor sigue caido. Abriendo Calculadora.");
                new ProcessBuilder("calc.exe").start();
            }

        } catch (IOException e) {
            System.out.println("Error: No se pudo lanzar el proceso");
        } catch (InterruptedException e) {
            System.out.println("Error: La espera fue interrumpida de forma inesperada");
            Thread.currentThread().interrupt();
        }

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
}
