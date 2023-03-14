package com.lowkey.complex;

import com.lowkey.complex.util.ClassLoaderUtils;

/**
 * @author yuanjifan
 * @description
 * @date 2022年04月01日 14:06
 */
public class ClassLoaderUtilsTest {
    //@Test
    void testClassLoaderUtils() {
        //jar所在路径
        String jarPath = "D:\\helloworld.jar";
        ClassLoaderUtils.loadJar(jarPath);
        try {
            //利用反射
            Class<?> aClass = Class.forName("StringUtilss");
            Object instance = aClass.newInstance();
            //调用方法
            aClass.getDeclaredMethod("getString", String.class).invoke(instance, "hello word");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
