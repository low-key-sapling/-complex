package com.lowkey.complex.counterStrategy.auto;

import org.springframework.stereotype.Component;

/**
 * @author lowkey
 * @description 通过策略自动注册获取策略类
 * @date 2023/08/11
 */
@Component
public interface AutoCounterStrategy {

    /**
     * 进行计算
     *
     * @param num1 数1
     * @param num2 数2
     * @return 计算结果
     */
    double calculate(long num1, long num2);

    /**
     * 自动注册策略方法
     */
    void register();
}