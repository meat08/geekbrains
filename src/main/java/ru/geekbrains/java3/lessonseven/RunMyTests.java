package ru.geekbrains.java3.lessonseven;

import ru.geekbrains.java3.lessonseven.annotations.AfterSuite;
import ru.geekbrains.java3.lessonseven.annotations.BeforeSuite;
import ru.geekbrains.java3.lessonseven.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RunMyTests {

    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 10;
    private static final Map<Integer, Set<Method>> priorityMap = new TreeMap<>();

    private static void add(int priority, Method method) {
        Set<Method> methodsSet = getPriority(priority);
        methodsSet.add(method);
    }

    private static Set<Method> getPriority(int priority) {
        return priorityMap.computeIfAbsent(priority, key -> new HashSet<>());
    }

    public static void start(Class<?> className) {
        int beforeAnnotationCount = 0;
        int afterAnnotationCount = 0;

        for (Method method :className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                add(MIN_PRIORITY - 1, method);
                beforeAnnotationCount++;
                if(beforeAnnotationCount > 1) throw new RuntimeException("@BeforeSuite больше 1");
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                add(MAX_PRIORITY + 1, method);
                afterAnnotationCount++;
                if(afterAnnotationCount > 1) throw new RuntimeException("@AfterSuite больше 1");
            }
            if (method.getAnnotation(Test.class) != null) {
                Test test = method.getAnnotation(Test.class);
                add(test.priority(), method);
            }
        }

        try {
            Object testClass = className.newInstance();
            for (Integer key : priorityMap.keySet()) {
                for (Method method : priorityMap.get(key)) {
                    method.invoke(testClass);
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(TestClass.class);
    }
}
