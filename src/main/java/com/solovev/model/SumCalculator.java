package com.solovev.model;

/**
 * Class with operation implementation
 */
public class SumCalculator extends NumberCalculator{
    public SumCalculator(int lengthOfArrays) {
        super(lengthOfArrays);
    }

    @Override
    public int operation(int a, int b) {
        return a + b;
    }
}
