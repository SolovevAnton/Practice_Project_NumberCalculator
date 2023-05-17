package com.solovev;

import com.solovev.model.MultCalculator;
import com.solovev.model.NumberCalculator;
import com.solovev.model.SumCalculator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int length = 3;
        List<NumberCalculator> list = List.of(new SumCalculator(),
                new MultCalculator(),
                new SumCalculator(1),
                new MultCalculator(1),
                new SumCalculator(length),
                new MultCalculator(length));

        list.forEach(System.out::println);
        try {
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
            //Test8
            System.out.println("\nTest8");
            for (NumberCalculator n : list) {
                Field[] fields = n.getClass().getSuperclass().getDeclaredFields();
                Method[] getters = {
                        n.getClass().getSuperclass().getMethod("getDataA"),
                        n.getClass().getSuperclass().getMethod("getMassPositive"),
                        n.getClass().getSuperclass().getMethod("getMassNegative"),
                };
                int getterCounter = 0;
                for (Field f : fields) {
                    f.setAccessible(true);
                    System.out.printf("Original Field Object: %s; Original Field Value: %s\n",
                            f.get(n),
                            Arrays.toString((int[]) f.get(n)));

                    int[] gotArr = (int[]) getters[getterCounter++].invoke(n);
                    System.out.printf("Get Field Object: %16s; Get Field Value:      %s\n",
                            gotArr,
                            Arrays.toString(gotArr));
                }
            }
            //Test9
            System.out.println("\nTest9");
            for (NumberCalculator n : list) {
                List<int[]> getMass = n.getArrMassRusults();
                getMass.forEach(arr -> System.out.println(Arrays.toString(arr)));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }


    }
}

