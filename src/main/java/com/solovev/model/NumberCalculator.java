package com.solovev.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class NumberCalculator {
    private int[] dataA, massPositive, massNegative;

    public NumberCalculator(int lengthOfArrays) {
        dataA = new int[lengthOfArrays];
        massPositive = new int[lengthOfArrays];
        massNegative = new int[lengthOfArrays];
        fill(100, massPositive);
        fill(-100, massNegative);
    }

    private void fill(int numToStart, int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = numToStart > 0 ? rand.nextInt(numToStart)
                    : rand.nextInt(numToStart, 0);
            arr[i] = num;
        }
    }

    public NumberCalculator create() {
        try {
            return this.getClass().getConstructor(int.class).newInstance(this.dataA.length * 2);
        } catch (Exception ignored) {
        }
        return null;
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
