package ru.otus.dkudinov.stand;

/**
 * Stand for testing app memory usage and size of created objects
 *
 * Before run you need mvn package javaagent from the same name module
 *
 * VM options: -javaagent:"javaagent\target\javaagent-1.0-SNAPSHOT.jar"
 */

import ru.otus.dkudinov.ObjectSizeFetcher;
import org.openjdk.jol.info.GraphLayout;

public class App
{
    private static final int MB = 1024 * 1024;

    public static void main( String[] args )
    {
        new App().startApp();
    }

    void startApp() {
        startMemoryUsage();

        System.out.println("\n");

        startObjectSize();
    }

    static void gc() {
        System.gc();
    }

    void startMemoryUsage() {
        System.out.println("Step 1 - before creating an object");
        printMemoryUsage();

        byte[] b = new byte[512 * MB];

        System.out.println(String.format("Step 2 - after creating the object %s Mb", b.length / MB));
        printMemoryUsage();

        b = null;
        gc();

        System.out.println("Step 3 - after nulling and gc working");
        printMemoryUsage();
    }

    static void printMemoryUsage() {
        Runtime r = Runtime.getRuntime();

        System.out.format("Memory usage:  Used = %,13d,  Total = %,13d,  Max = %,13d,  Free = %,13d\n",
                r.totalMemory() - r.freeMemory(),
                r.totalMemory(),
                r.maxMemory(),
                r.freeMemory());
    }

    void startObjectSize() {
        String s = new String("");
        printObjectSize(s);

        s = "12345";
        printObjectSize(s);

        s = null;

        A theA = new A();
        printObjectSize(theA);
        printMemoryUsage();

        B theB  = new B();
        printObjectSize(theB);
        printMemoryUsage();
    }

    static void printObjectSize(Object o) {
        System.out.format("Object size %7s: Plane = %,7d,  Total = %,7d\n",
                o.getClass().getSimpleName(),
                ObjectSizeFetcher.getObjectSize(o),
                GraphLayout.parseInstance(o).totalSize());
    }

    class A {
        int i = 0;
        long l = 0L;
        B b = new B();
    }

    class B {
        byte[] b = new byte[128 * MB];
    }
}
