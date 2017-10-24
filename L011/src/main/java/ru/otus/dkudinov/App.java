package ru.otus.dkudinov;

/**
 * author: dkudinov
 * desc: the app doesn't do anything interesting
 */

import com.google.common.base.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        ArrayList<String> nums = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            nums.add(String.valueOf(i + 1));
        }

        System.out.println("What do you want to say to this World?");

        Scanner scanner = new Scanner(System.in);

        String theAnswer = scanner.nextLine();

        if ("Hello, World!".equals(theAnswer)) {
            System.out.println(Joiner.on(", ").join("Yeahh", "dude", "you're right!"));
        } else {
            System.out.println(theAnswer + " Ok, just count up to 10... " + Joiner.on(", ").join(nums));
        }
    }
}
