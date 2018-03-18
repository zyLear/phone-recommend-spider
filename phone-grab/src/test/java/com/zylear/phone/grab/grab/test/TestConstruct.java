package com.zylear.phone.grab.grab.test;

import org.junit.Test;

/**
 * Created by 28444 on 2017/9/24.
 */
public class TestConstruct extends Farther {




    public TestConstruct(){
        System.out.println("TestConstruct");
    }

    {
        name = "son";
        System.out.println("no static {}"+name);
    }

    static {
        System.out.println("static {}");
    }

    @Test
    public void test(){
        new TestConstruct();
    }

    public static void main(String[] args) {
        new TestConstruct();
    }

    @Test
    public void testTwo(){
        String string = "234";
        String[] strings = string.split("5");
        System.out.println(strings[0]);
    }
}
