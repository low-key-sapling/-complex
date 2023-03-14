package com.lowkey.complex.util;

import cn.hutool.core.util.ClassLoaderUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author yuanjifan
 * @description 类加载工具类
 * @date 2022年04月01日 13:43
 */
@Slf4j
public class ClassLoaderUtils extends ClassLoaderUtil {
    /**
     * @param jarPath jar路径
     * @author yuanjifan
     * @description 动态加载jar包
     * @date 2022/4/1 13:58
     */
    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        //文件存在
        if (!jarFile.exists()) {
            log.error("jar file not found.");
            return;
        }
        //从URLClassLoader类加载器中获取类的addURL方法
        Method method = null;
        boolean accessible = false;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            //获取方法的访问权限
            accessible = method.isAccessible();
            //修改访问权限为可写
            if (!accessible) {
                method.setAccessible(true);
            }
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            //获取jar文件的url路径
            URL url = jarFile.toURI().toURL();
            //jar路径加入到系统url路径里
            method.invoke(classLoader, url);
        } catch (Exception e) {
            log.error("jar load error.", e);
        } finally {
            if (method != null) {
                method.setAccessible(accessible);
            }
        }
    }
}
