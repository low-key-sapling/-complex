package com.lowkey.complex.counterStrategy.auto;

import com.lowkey.complex.counterStrategy.CounterStrategyType;
import org.springframework.stereotype.Component;

/**
 * 计算器之减法策略
 */
@Component
public class SubtractionCounterStrategy implements AutoCounterStrategy {
    @Override
    public double calculate(long num1, long num2) {
        return num1 - num2;
    }

    @Override
    public void register() {
        AutoRegisterCounterStrategyFactory.registerChargeStrategy(CounterStrategyType.SUBTRACTION, this);
    }
}
