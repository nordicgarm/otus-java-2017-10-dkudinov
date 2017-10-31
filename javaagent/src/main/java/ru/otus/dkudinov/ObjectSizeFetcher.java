package ru.otus.dkudinov;

import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
    // @TODO Add bytecode transformer method, add getter with list of all loaded classes

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        System.out.println("Starting agent...");

        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}