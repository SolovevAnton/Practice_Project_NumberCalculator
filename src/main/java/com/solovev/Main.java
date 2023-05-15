package com.solovev;

import com.solovev.model.NumberCalculator;

public class Main {
    public static void main(String[] args) {

        NumberCalculator calc = new NumberCalculator(3);
        System.out.println(calc);
        System.out.println(calc.create());
    }
}