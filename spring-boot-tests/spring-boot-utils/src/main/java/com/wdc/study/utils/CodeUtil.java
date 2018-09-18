package com.wdc.study.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author wdc
 * @date 2018/6/27
 */
public class CodeUtil {
    public static <T>  void initAndSet(String instanceName, Class<T> tClass, Object ... excludes) {
        StringBuffer buffer = new StringBuffer();
        T instance = BeanUtils.instantiate(tClass);
        buffer.append(tClass.getSimpleName()).append(" ").append(instanceName).append(" =").append(" new ").append(tClass.getSimpleName()).append("();\n");
        Arrays.stream(BeanUtils.getPropertyDescriptors(tClass))
                .filter(e -> Objects.nonNull(e.getWriteMethod()) && Objects.nonNull(e.getReadMethod()) && Arrays.stream(excludes).noneMatch(ex -> e.getName().equals(ex)))
                .forEach(e -> {
                    try {
                        buffer.append(instanceName).append(".").append(e.getWriteMethod().getName()).append("(")
                                .append(e.getReadMethod().invoke(instance)).append(")").append(";\n");
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }
                });
        System.out.println(buffer);
    }

    public static <T>  void initAndSet(Class<T> tClass) {
        initAndSet("instance", tClass);
    }

    public static <T>  void initAndSet(Class<T> tClass, Object... excludes) {
        initAndSet("instance", tClass, excludes);
    }

}
