package com.solovev.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Class to store Numbers in arrays
 */

public abstract class NumberCalculator {
    private final int[] dataA, massPositive, massNegative;

    public NumberCalculator(int lengthOfArrays) {
        dataA = new int[lengthOfArrays];
        massPositive = new int[lengthOfArrays];
        massNegative = new int[lengthOfArrays];
        fill(100, massPositive);
        fill(-100, massNegative);
    }

    /**
     * Method to fill arrays with random numbers
     *
     * @param numToStart the upperbound for random numbers if it is positive, and lower-bound otherwise
     * @param arr        array to fill with numbers
     */
    private void fill(int numToStart, int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numToStart > 0 ? rand.nextInt(numToStart)
                    : rand.nextInt(numToStart, 0);
        }
    }

    /**
     * Fills the dataA array with given numbers
     *
     * @param numbers - numbers to fill dataA
     */
    public void fill(int... numbers) {
        for (int i = 0; i < numbers.length && i < dataA.length; i++) {
            dataA[i] = numbers[i];
        }
    }

    /**
     * Method to do some kind of operation between two numbers
     *
     * @param a first number
     * @param b second number
     * @return result of the operation
     */
    public abstract int operation(int a, int b);

    /**
     * Method invokes operation method for every value in dataA
     *
     * @return result of the operation, if dataA.length < 1 returns 0;
     */
    public int result() {
        if (dataA.length < 1) {
            return 0;
        }
        int result = dataA[0];
        for (int i = 1; i < dataA.length; i++) {
            result = operation(result, dataA[i]);
        }
        return result;
    }

    /**
     * Creates new Instance of the same class as this
     *
     * @return instance of the NumberCalculator with x2 of the arrays length
     */

    public NumberCalculator create() {
        try {
            return this.getClass().getConstructor(int.class).newInstance(this.dataA.length * 2);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Method gets data from all fields with "mass" in it;
     *
     * @return list of arrays which names contains mass in it
     * @throws IllegalAccessException
     */
    public List<int[]> getData() {
        Field[] fields = this.getClass().getDeclaredFields();
        String includes = "mass";
        Function<Field, int[]> extractData = field -> {
            try {
                return (int[]) field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        return Arrays
                .stream(fields)
                .filter(field -> field.getName().contains(includes))
                .map(extractData)
                .toList();

    }

    /**
     * Method creates list of getter methods that start from "getMass"
     *
     * @return List of methods that starts from getMass
     */
    public List<Method> getMass() {
        Method[] methods = this.getClass().getMethods();
        String includes = "getMass";
        return Arrays
                .stream(methods)
                .filter(method -> method.getName().contains(includes))
                .toList();
    }

    public int[] getDataA() {
        return dataA.clone();
    }

    public int[] getMassPositive() {
        return massPositive.clone();
    }

    public int[] getMassNegative() {
        return massNegative.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberCalculator that = (NumberCalculator) o;

        if (!Arrays.equals(dataA, that.dataA)) return false;
        if (!Arrays.equals(massPositive, that.massPositive)) return false;
        return Arrays.equals(massNegative, that.massNegative);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(dataA);
        result = 31 * result + Arrays.hashCode(massPositive);
        result = 31 * result + Arrays.hashCode(massNegative);
        return result;
    }

    @Override
    public String toString() {
        return "NumberCalculator{" +
                "dataA=" + Arrays.toString(dataA) +
                ", massPositive=" + Arrays.toString(massPositive) +
                ", massNegative=" + Arrays.toString(massNegative) +
                '}';
    }
}
