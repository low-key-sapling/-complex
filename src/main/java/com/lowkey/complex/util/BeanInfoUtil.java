package com.lowkey.complex.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanInfoUtil {

    // 设置bean的某个属性值  
    public static void setProperty(Class<?> tClass, String userName) throws Exception {
        Object o = tClass.newInstance();
        Field[] fields = tClass.getFields();
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, tClass);
        // 获得用于写入属性值的方法  
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值  
        methodSetUserName.invoke(o, "wong");
        System.out.println("set userName:" + o);
    }

    // 获取bean的某个属性值  
    public static void getProperty(Class<?> tClass, String userName) throws Exception {
        Object o = tClass.newInstance();
        // 获取Bean的某个属性的描述符  
        PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, o.getClass());
        // 获得用于读取属性值的方法  
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值  
        Object objUserName = methodGetUserName.invoke(o);
        System.out.println("get userName:" + objUserName.toString());
    }
}
