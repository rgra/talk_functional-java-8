/*
 * @(#)JavaslangPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on Sep 26, 2016
 */

package io.github.rgr.vavr;

import static io.vavr.API.*;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Option;

/**
 * @author gransberger
 */
public class VavrPlayground {

    public static void main(String[] args) {

        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        System.out.println(add2.apply(4));

        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        try {
            System.out.println(divide.apply(5, 0));
        }
        catch (Throwable e) {
            e.printStackTrace();
        }

        System.out.println(safeDivide.apply(5, 0));

        int i = 1;
        String s = Match(i)
            .of(Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?"));
        System.out.println(s);
    }

    static void displayHelp() {
        System.out.println("Help");
    }

    static void displayVersion() {
        System.out.println("Version");
    }

}
