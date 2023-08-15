package com.lowkey.complex.counterStrategy;

/**
 * 计算器策略
 */
public interface CounterStrategy {
    /**
     * 进行计算
     *
     * @param num1 数1
     * @param num2 数2
     * @return 计算结果
     */
    double calculate(long num1, long num2);
}
