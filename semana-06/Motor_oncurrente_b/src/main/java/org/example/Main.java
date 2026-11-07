package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("==================================================");
        System.out.println("   UDITflix BACKEND · DESCARGA MASIVA DE ASSETS  ");
        System.out.println("==================================================");

        Random azar = new Random();

        int d1 = 1000 + azar.nextInt(3001);
        int d2 = 1000 + azar.nextInt(3001);
        int d3 = 1000 + azar.nextInt(3001);
        int d4 = 1000 + azar.nextInt(3001);

        ArrayList<Thread> hilos = new ArrayList<>();

        hilos.add(new Thread(() -> descargar("catalogo-metadatos", d1), "WORKER-1"));
        hilos.add(new Thread(() -> descargar("sesion-y-perfil", d2), "WORKER-2"));
        hilos.add(new Thread(() -> descargar("portadas-assets", d3), "WORKER-3"));
        hilos.add(new Thread(() -> descargar("analiticas", d4), "WORKER-4"));

        hilos.get(0).setPriority(Thread.MAX_PRIORITY);
        hilos.get(1).setPriority(Thread.MAX_PRIORITY);
        hilos.get(2).setPriority(Thread.NORM_PRIORITY);
        hilos.get(3).setPriority(Thread.MIN_PRIORITY);

        long inicio = System.currentTimeMillis();

        System.out.println("[MAIN] Lanzando workers...\n");

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long total = System.currentTimeMillis() - inicio;
        int masLento = Math.max(Math.max(d1, d2), Math.max(d3, d4));
        int suma = d1 + d2 + d3 + d4;

        System.out.println("\n==================================================");
        System.out.println(" CATÁLOGO LISTO");
        System.out.println(" Login habilitado");
        System.out.println(" Tiempo concurrente real: " + total + " ms");
        System.out.println(" Duración del módulo más lento: " + masLento + " ms");
        System.out.println(" Suma de latencias: " + suma + " ms");
        System.out.println("==================================================");
    }

    public static void descargar(String modulo, int duracion) {

        Thread hilo = Thread.currentThread();

        System.out.println("[INICIO] " + modulo
                + " | Hilo: " + hilo.getName()
                + " | Prioridad: " + hilo.getPriority());

        try {
            Thread.sleep(duracion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[FIN] " + modulo
                + " | Hilo: " + hilo.getName()
                + " | Latencia: " + duracion + " ms"
                + " | Prioridad: " + hilo.getPriority());
    }
}