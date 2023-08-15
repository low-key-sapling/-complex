package com.lowkey.complex.counterStrategy;

/**
 * 计算器之减法策略
 */
public class SubtractionCounterStrategy implements CounterStrategy {
    @Override
    public double calculate(long num1, long num2) {
        return num1 - num2;
    }
}
