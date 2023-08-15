package com.lowkey.complex.counterStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lowkey
 * @description 通过MAP方式进行获取策略:通过注册方式进行新增策略，新增策略无需更改代码只需关注新增的策略即可，此方式为手动注册
 * 通过map进行获取策略结果相同，进化后if语句没有了，减少了复杂度，增加新的策略后只需调用策略注册接口就好，不需要修改获取策略的代码
 * @date 2023/10/11
 */
public class CounterStrategyMapFactory {

    /**
     * 存储策略
     */
    static Map<CounterStrategyType, CounterStrategy> chargeStrategyMap = new HashMap<>();

    // 注册默认策略
    static {
        registerChargeStrategy(CounterStrategyType.ADD, new AddCounterStrategy());
        registerChargeStrategy(CounterStrategyType.SUBTRACTION, new SubtractionCounterStrategy());
    }

    /**
     * 提供注册策略接口，外部只需要调用此接口接口新增策略
     */
    public static void registerChargeStrategy(CounterStrategyType taxType, CounterStrategy taxStrategy) {
        chargeStrategyMap.put(taxType, taxStrategy);
    }

    /**
     * 通过map获取策略，当增加新的策略时无需修改代码
     */
    public static CounterStrategy getChargeStrategy(CounterStrategyType taxType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时增加圈复杂度
        if (chargeStrategyMap.containsKey(taxType)) {
            return chargeStrategyMap.get(taxType);
        } else {
            throw new Exception("未配置相应策略");
        }
    }
}