package com.lowkey.complex.counterStrategy;

import org.springframework.stereotype.Component;

/**
 * 通过计算器策略工厂获取策略：此方式新增策略还需要进行判断更改代码
 */
@Component
public class CounterStrategyFactory {

    /**
     * 根据计算类型返回策略
     *
     * @param counterStrategyType 计算类型美剧
     * @return 返回策略实现类
     */
    public static CounterStrategy getCounterStrategyFactory(CounterStrategyType counterStrategyType) throws Exception {
        if (counterStrategyType == CounterStrategyType.ADD) {
            return new AddCounterStrategy();
        } else if (counterStrategyType == CounterStrategyType.SUBTRACTION) {
            return new SubtractionCounterStrategy();
        } else {
            throw new Exception("未配置相应策略");
        }
    }
}