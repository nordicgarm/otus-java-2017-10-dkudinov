package ru.otus.dkudinov;

import java.util.Arrays;
import java.util.Collections;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyArrayList<Integer> list = new MyArrayList<>();

        Collections.addAll(list, 1, 2, 3, 4, 5);


        System.out.println(Arrays.toString(list.toArray()));



    }
}
