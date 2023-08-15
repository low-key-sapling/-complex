package com.lowkey.complex.counterStrategy.annotation;

import com.lowkey.complex.counterStrategy.CounterStrategyType;
import com.lowkey.complex.util.ApplicationContextHelper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author lowkey
 * @description 计算器策略工厂类，通过注解方式实现自动注册
 * @date 2021/10/11
 */
public class AutoRegisterCounterStrategyAnnotationFactory {

    /**
     * 存储策略
     */
    static Map<CounterStrategyType, AutoCounterStrategyAnnotation> chargeStrategyMap = new HashMap<>();

    static {
        autoRegisterTaxStrategy();
    }

    /**
     * 通过map获取策略，当增加新的策略时无需修改代码
     */
    public static AutoCounterStrategyAnnotation getChargeStrategy(CounterStrategyType taxType) throws Exception {
        // 当增加新的税类型时，需要修改代码，同时增加圈复杂度
        if (chargeStrategyMap.containsKey(taxType)) {
            return chargeStrategyMap.get(taxType);
        } else {
            throw new Exception("未配置相应策略");
        }
    }

    /**
     * 自动注册策略
     */
    private static void autoRegisterTaxStrategy() {
        // 通过反射找到所有的策略子类进行注册
        Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(AutoCounterStrategyAnnotation.class.getPackage().getName())).setScanners(new SubTypesScanner()));
        Set<Class<? extends AutoCounterStrategyAnnotation>> taxStrategyClassSet = reflections.getSubTypesOf(AutoCounterStrategyAnnotation.class);
        if (taxStrategyClassSet != null) {
            for (Class<?> clazz : taxStrategyClassSet) {
                //clazz.newInstance()的部分，通过反射代码获取类的时候，我们获得类的方式是 class.newInstance，
                //这种写法没有与Spring容器关联起来获取bean，虽然也能拿到但是如果类里面有@Autowired这种方式注入的对象就会空了
                //AutoCounterStrategy chargeStrategy = (AutoCounterStrategy) clazz.newInstance();

                // 找到类型注解，自动完成策略注册
                if (clazz.isAnnotationPresent(CounterStrategyTypeAnnotation.class)) {
                    CounterStrategyTypeAnnotation taxTypeAnnotation = clazz.getAnnotation(CounterStrategyTypeAnnotation.class);
                    CounterStrategyType counterStrategyType = taxTypeAnnotation.counterStrategyType();
                    AutoCounterStrategyAnnotation autoCounterStrategyAnnotation = (AutoCounterStrategyAnnotation) ApplicationContextHelper.popBean(clazz);
                    if (Objects.isNull(autoCounterStrategyAnnotation)) {
                        System.err.println("为获取到对象AutoCounterStrategy");
                        continue;
                    }
                    chargeStrategyMap.put(counterStrategyType, autoCounterStrategyAnnotation);
                }
            }
        }
    }
}