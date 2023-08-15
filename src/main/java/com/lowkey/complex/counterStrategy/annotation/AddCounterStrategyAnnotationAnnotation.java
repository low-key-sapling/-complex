package com.lowkey.complex.counterStrategy.annotation;

import com.lowkey.complex.counterStrategy.CounterStrategyType;
import org.springframework.stereotype.Component;

/**
 * 计算器之加法策略
 */
@CounterStrategyTypeAnnotation(counterStrategyType = CounterStrategyType.ADD)
@Component
public class AddCounterStrategyAnnotationAnnotation implements AutoCounterStrategyAnnotation {
    @Override
    public double calculate(long num1, long num2) {
        return num1 + num2;
    }
}
