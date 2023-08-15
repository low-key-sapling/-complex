package com.lowkey.complex.counterStrategy;

/**
 * 计算器之加法策略
 */
public class AddCounterStrategy implements CounterStrategy {
    @Override
    public double calculate(long num1, long num2) {
        return num1 + num2;
    }
}
