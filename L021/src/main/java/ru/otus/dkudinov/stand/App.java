package ru.otus.dkudinov.stand;

/**
 * Stand for testing size of created objects
 *
 */

import ru.otus.dkudinov.ObjectSizeFetcher;

public class App
{
    public static void main( String[] args )
    {
        App app = new App();

        app.startApp();
    }

    public void startApp() {
        String string = new String("");

        System.out.println(String.format("Size of object %s = %d", string.getClass().getSimpleName(), ObjectSizeFetcher.getObjectSize(string)));
    }
}
