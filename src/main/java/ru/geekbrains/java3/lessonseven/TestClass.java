package ru.geekbrains.java3.lessonseven;

import ru.geekbrains.java3.lessonseven.annotations.AfterSuite;
import ru.geekbrains.java3.lessonseven.annotations.BeforeSuite;
import ru.geekbrains.java3.lessonseven.annotations.Test;

public class TestClass {

    @BeforeSuite
    public void beforeMethod() {
        System.out.println("beforeMethod run");
    }
    @AfterSuite
    public void afterMethod() {
        System.out.println("afterMethod run");
    }
    @Test(priority = 1)
    public void testMethod1() {
        System.out.println("testMethod1 run with priority 1");
    }
    @Test(priority = 3)
    public void testMethod2() {
        System.out.println("testMethod2 run with priority 3");
    }
    @Test(priority = 3)
    public void testMethod3() {
        System.out.println("testMethod3 run with priority 3");
    }
    @Test(priority = 5)
    public void testMethod4() {
        System.out.println("testMethod4 run with priority 5");
    }
    @Test(priority = 2)
    public void testMethod5() {
        System.out.println("testMethod5 run with priority 2");
    }

}
