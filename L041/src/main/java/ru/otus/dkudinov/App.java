package ru.otus.dkudinov;

import java.util.*;

/**
 * VM options: -
 *
 */
public class App 
{
    private List<MegaByte> megaBytes = new ArrayList<>();
    private static final int MB = 1024 * 1024;
    private static final int MAX_MB = 512 * MB;

    public static void main( String[] args )
    {
        new App().start();
    }

    private void start() {
        boolean theLeak;

        long startTime = System.currentTimeMillis();
        long leakTime = startTime + (long) (0.2 * 60 * 1000);
        long oomTime = leakTime + (long) (0.1 * 60 * 1000);

        System.out.println(new Date(startTime));
        System.out.println(new Date(leakTime));
        System.out.println(new Date(oomTime));

        while (true) {
            long currTime = System.currentTimeMillis();

            if (currTime < leakTime) {
                addMb(AppAction.NoLeak);
            } else if (currTime > leakTime && currTime < oomTime) {
                addMb(AppAction.Leak);
            } else if (currTime > oomTime) {
                addMb(AppAction.OOM);
            }
        }
    }

    private void addMb(AppAction action) {
        double k = 0;

        try {
            if (action == AppAction.NoLeak) {
                k = 0.3;
            } else if (action == AppAction.Leak) {
                k = 0.7;
            } else if (action == AppAction.OOM) {
                k = Integer.MAX_VALUE;
            }

            megaBytes.add(new MegaByte());

            if (megaBytes.size() > k * MAX_MB / MB) {
                for (int i = 0; i < megaBytes.size(); i = i + 2) {
                    megaBytes.remove(i);
                }
                System.out.println(String.format("Object is cleaned to %d MB, action is %s, k is %.1f", megaBytes.size(), action, k));
            }

            Thread.sleep(new Random().nextInt(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            System.out.println(String.format("Count of objects is %d (k is %f)", megaBytes.size(), k));

            throw new RuntimeException("That's all, folks!");
        }
    }

    private double rndDbl(double min, double max) {
        double d = 0;

        d = Math.random() + min;
        // TODO implement calculation of random value of double in range [min, max]

        return d;
    }

    private class MegaByte {
        byte[] aByte;

        MegaByte() {
            aByte = new byte[MB];
        }
    }

    private enum AppAction {
        NoLeak, Leak, OOM
    }
}
