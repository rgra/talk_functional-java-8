/*
 * @(#)VavrPatternMatching.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */

package io.github.rgra.live;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

import java.time.LocalDate;
/**
 * @author rgra
 */
public class VavrPatternMatching {
    public static void main(String[] args) {
        int num = 1;
        String s = Match(num)
            .of(Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?"));
        System.out.println(s);

        // Object obj = 0.534;
        Object obj = LocalDate.now();
        Object plusOne = Match(obj).of(
            Case($(instanceOf(Integer.class)), i -> i + 1),
            Case($(instanceOf(Double.class)), d -> d + 1),
            Case($(instanceOf(LocalDate.class)), d -> d.plusDays(1)),
            Case($(), o -> {
                throw new NumberFormatException();
            }));
        System.out.println(plusOne);
    }
}
