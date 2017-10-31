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

import java.util.ArrayList;
import java.util.List;

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
        Integer integer = new Integer(1);
        printObjectSize(integer);

        String s = new String("");
        printObjectSize(s);

        s = "1";
        printObjectSize(s);
        s = null;

        byte b1 = 1;
        printObjectSize(b1);

        byte[] b = new byte[8 * MB];
        printObjectSize(b);
        b = null;

        A theA = new A();
        printObjectSize(theA);
        theA = null;

        B theB  = new B();
        printObjectSize(theB);
        theB = null;

        C theC = new C();
        printObjectSize(theC);
        theC = null;

        List<A> listOfA = new ArrayList<>();
        printObjectSize(listOfA);

        for (int i = 0; i < 10; i++) {
            listOfA.add(new A());
        }
        printObjectSize(listOfA);

        for (int i = 0; i < 90; i++) {
            listOfA.add(new A());
        }
        printObjectSize(listOfA);
    }

    static void printObjectSize(Object o) {
        System.out.format("Object size %9s: Plane = %,4d,  Total = %,13d,  Value = %s\n",
                o.getClass().getSimpleName(),
                ObjectSizeFetcher.getObjectSize(o),
                GraphLayout.parseInstance(o).totalSize(),
                o.toString());
    }

    class A {
        int i = 0;
        long l = 0L;
        B b = new B();
    }

    class B {
        byte[] b = new byte[8 * MB];
    }

    class C {

    }
}
