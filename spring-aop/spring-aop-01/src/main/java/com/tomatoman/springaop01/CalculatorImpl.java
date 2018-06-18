package com.tomatoman.springaop01;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator{
    public int add(int a, int b) {
        int result = a + b;
        System.out.println("add result = " + result);
        return result;
    }

    public int min(int a, int b) {
        int result = a - b;
        System.out.println("min result = " + result);
        return result;
    }

    public int mul(int a, int b) {
        int result = a * b;
        System.out.println("mul result = " + result);
        return result;
    }

    public int div(int a, int b) {
        int result = a / b;
        System.out.println("div result = " + result);
        return result;
    }
}
