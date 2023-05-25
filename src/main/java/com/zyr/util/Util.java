package com.zyr.util;

import java.net.URL;
import java.net.URLClassLoader;

public class Util {

    public static Object loadClassFromJar(String jarPath, String className) throws Exception{
        // 创建一个URL对象，指向jar包的路径
        // URL url = new URL("jar:file:" + jarPath + "!/");
        URL url = new URL(jarPath);
        // 创建一个URLClassLoader对象，用于加载jar包中的类
        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        // 使用Class.forName方法，传入类的全限定名和URLClassLoader对象，获取类的对象实例
        Class<?> clazz = loader.loadClass(className);
        // 返回类的对象实例
        return clazz.getDeclaredConstructor().newInstance();

    }
}
