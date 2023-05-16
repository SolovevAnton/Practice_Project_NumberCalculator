package com.solovev.model;

/**
 * Class with operation implementation as multiply
 */
public class MultCalculator extends NumberCalculator {
    public MultCalculator() {
    }

    public MultCalculator(int lengthOfArrays) {
        super(lengthOfArrays);
    }

    @Override
    public int operation(int a, int b) {
        return a * b;
    }
}
