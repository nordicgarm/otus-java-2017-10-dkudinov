package ru.otus.dkudinov;

import java.util.*;

/**
 * Написать свою реализацию ArrayList на основе массива.
 * class MyArrayList<T> implements List<T>{...}
 *
 * Проверить, что на ней работают методы 
 * addAll(Collection<? super T> c, T... elements),
 * static <T> void copy(List<? super T> dest, List<? extends T> src),
 * static <T> void sort(List<T> list, Comparator<? super T> c)
 * из java.util.Collections
 */

public class App 
{
    public static void main( String[] args )
    {
        MyArrayList<Integer> list = new MyArrayList<>();

		// addAll
        Collections.addAll(list, 1, 2, -1, 0);
        System.out.println(Arrays.toString(list.toArray()));
		
		// copy
		MyArrayList<Integer> list2 = new MyArrayList<>();
		Collections.addAll(list2, 1, 1, 1, 1);
		Collections.copy(list2, list);

		System.out.println(Arrays.toString(list2.toArray()));
		
		// sort
		Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer i1, Integer i2)
				{
					return i1 - i2;
				}
			}
		);
		System.out.println(Arrays.toString(list.toArray()));
    }
}
