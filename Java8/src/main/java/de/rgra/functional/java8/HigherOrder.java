/*
 * @(#)HigherOrder.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */
package de.rgra.functional.java8;

import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;

/**
 * @author rgra
 */
public class HigherOrder {

    public static void main(String[] args) {
        System.out.println(curry(mult(), 5).apply(6));
    }

    private static IntBinaryOperator mult() {
        return (a, b) -> a * b;
    }

    private static IntFunction<Integer> curry(IntBinaryOperator op, int a) {
        return (b) -> op.applyAsInt(a, b);
    }

}
