package com.lowkey.complex;

import com.lowkey.complex.counterStrategy.CounterStrategy;
import com.lowkey.complex.counterStrategy.CounterStrategyFactory;
import com.lowkey.complex.counterStrategy.CounterStrategyMapFactory;
import com.lowkey.complex.counterStrategy.CounterStrategyType;
import com.lowkey.complex.counterStrategy.annotation.AutoCounterStrategyAnnotation;
import com.lowkey.complex.counterStrategy.annotation.AutoRegisterCounterStrategyAnnotationFactory;
import com.lowkey.complex.counterStrategy.auto.AutoCounterStrategy;
import com.lowkey.complex.counterStrategy.auto.AutoRegisterCounterStrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CounterStrategyTest {
    @Test
    public void testStrategy() {
        //通过计算器策略工厂方式进行计算
        System.out.println("=======计算器工厂模式 IF/ELSE注册 START=======");
        try {
            CounterStrategy chargeStrategy = CounterStrategyFactory.getCounterStrategyFactory(CounterStrategyType.ADD);
            double charge = chargeStrategy.calculate(100, 20);
            System.out.println("加法计算结果:" + charge);

            chargeStrategy = CounterStrategyFactory.getCounterStrategyFactory(CounterStrategyType.SUBTRACTION);
            charge = chargeStrategy.calculate(100, 20);
            System.out.println("减法计算结果:" + charge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=======计算器工厂模式 手动调用注册方法注册 START=======");
        //通过MAP计算器工厂方式进行计算
        try {
            CounterStrategy chargeStrategy = CounterStrategyMapFactory.getChargeStrategy(CounterStrategyType.ADD);
            double charge = chargeStrategy.calculate(100, 20);
            System.out.println("加法计算结果:" + charge);

            chargeStrategy = CounterStrategyMapFactory.getChargeStrategy(CounterStrategyType.SUBTRACTION);
            charge = chargeStrategy.calculate(100, 20);
            System.out.println("减法计算结果:" + charge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=======计算器工厂模式 反射方法自动注册方法注册 START=======");
        //通过自动注册策略工厂进行计算，此种方式只需关注新增策略本身即可
        try {
            AutoCounterStrategy chargeStrategy = AutoRegisterCounterStrategyFactory.getChargeStrategy(CounterStrategyType.ADD);
            double charge = chargeStrategy.calculate(100, 20);
            System.out.println("加法计算结果:" + charge);

            chargeStrategy = AutoRegisterCounterStrategyFactory.getChargeStrategy(CounterStrategyType.SUBTRACTION);
            charge = chargeStrategy.calculate(100, 20);
            System.out.println("减法计算结果:" + charge);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=======计算器工厂模式 注解方法自动注册方法注册 START=======");
        //通过注解方式自动注册策略工厂进行计算，此种方式只需关注新增策略本身即可
        try {
            AutoCounterStrategyAnnotation chargeStrategy = AutoRegisterCounterStrategyAnnotationFactory.getChargeStrategy(CounterStrategyType.ADD);
            double charge = chargeStrategy.calculate(100, 20);
            System.out.println("加法计算结果:" + charge);

            chargeStrategy = AutoRegisterCounterStrategyAnnotationFactory.getChargeStrategy(CounterStrategyType.SUBTRACTION);
            charge = chargeStrategy.calculate(100, 20);
            System.out.println("减法计算结果:" + charge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
