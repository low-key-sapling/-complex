package com.lowkey.complex.counterStrategy.annotation;

import com.lowkey.complex.counterStrategy.CounterStrategyType;

import java.lang.annotation.*;

/**
 * @author lowkey
 * @description 自定义计算类型注解
 * @date 2023/08/26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CounterStrategyTypeAnnotation {
    CounterStrategyType counterStrategyType();
}