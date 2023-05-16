package com.solovev;

import com.solovev.model.MultCalculator;
import com.solovev.model.NumberCalculator;
import com.solovev.model.SumCalculator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {
    //ToDo move exp out of main
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        int length = 3;
        List<NumberCalculator> list = List.of(new SumCalculator(),
                new MultCalculator(),
                new SumCalculator(1),
                new MultCalculator(1),
                new SumCalculator(length),
                new MultCalculator(length));

        list.forEach(System.out::println);
        //Test2
        System.out.println("\nTest2");
        int[][] fills = {
                {},
                {1, 2},
                {-1, 0, 2}
        };
        for (NumberCalculator n : list) {
            for (int[] i : fills) {
                n.fill(i);
                System.out.println(n);
            }
        }
        //Test3 +4 + 7
        System.out.println("\nTest3 +4 + 7");
        list.forEach(calc -> System.out.println(calc.result()));

        //ToDo Why this does not work with varargs?
        //     list.get(0).getClass().getSuperclass().getDeclaredMethod("fill",int[].class).invoke(list.get(3),1,2,3);
        //     System.out.println(list.get(3));

        //Test5
        System.out.println("\nTest5");
        for (NumberCalculator n : list) {
            NumberCalculator created = n.create();
            System.out.printf("Class: %s; Instance: %s\n", created.getClass().getSimpleName(), created);
        }

        //Test6
        System.out.println("\nTest5");
        for (NumberCalculator n : list) {
            List<int[]> arrays = n.getData();
            System.out.print("arrays containing \"mass\": [");
            arrays.forEach(arr -> System.out.print(Arrays.toString(arr)));
            System.out.println("]");
        }
        //Test7
        System.out.println("\nTest7");
        for (NumberCalculator n : list) {
            Field f = n.getClass().getSuperclass().getDeclaredField("dataA");
            f.setAccessible(true);
            System.out.printf("Original Field Object: %s ; Original Field Value: %s\n",
                    f.get(n),
                    Arrays.toString((int[]) f.get(n)));
            System.out.printf("Get Field Object: %s ; Get Field Value: %s\n",
                    n.getDataA(),
                    Arrays.toString(n.getDataA()));
        }
        //Test8
        System.out.println("\nTest8");
        for (NumberCalculator n : list) {
            List<Method> getMass = n.getMass();
            getMass.forEach(System.out::println);
        }
    }
}

