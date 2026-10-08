package org.example;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;

public class PipelineAuditoria {
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("   PILDORA TECNICA: SECUENCIAL VS PARALELO");
        System.out.println("===============================================\n");

        try {
            System.out.println(" INICIANDO EJECUCION SECUENCIAL ");
            long inicioSecuencial = System.currentTimeMillis();

            System.out.println("    -> Lanzando proceso 1( y esperando a que muera...)");
            Process p1 = new ProcessBuilder("ping", "-n", "1", "127.0.0.1").start();
            p1.waitFor();
            System.out.println("    -> Lanzando proceso 2( y esperando a que muera...)");
            Process p2 = new ProcessBuilder("ping", "-n", "2", "127.0.0.1").start();
            p2.waitFor();

            int code1 = p1.waitFor();
            int code2 = p2.waitFor();
            System.out.println("Codigo de salida 1: " + code1);
            System.out.println("Codigo de salida 2: " + code2);
            if (code1 != 0 || code2 != 0 ) {
                new ProcessBuilder("calc.exe").start();
            } else
                new ProcessBuilder("notepad.exe").start();

            long finSecuencial = System.currentTimeMillis();
            System.out.println("TIEMPO TOTAL SECUENCIAL: " +  (finSecuencial - inicioSecuencial)+ " ms\n");


        } catch (IOException e) {
            System.out.println("Error: No se pudo lanzar el proceso");
        } catch (InterruptedException e) {
            System.out.println("Error: La espera fue interrumpida de forma inesperada");
        }

    }

}
