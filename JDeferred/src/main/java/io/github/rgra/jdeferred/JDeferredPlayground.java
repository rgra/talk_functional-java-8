/*
 * @(#)JDeferredPlayground.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */

package io.github.rgra.jdeferred;

import org.jdeferred.Deferred;
import org.jdeferred.impl.DeferredObject;

/**
 * @author rgra
 */
public class JDeferredPlayground {

    public static void main(String[] args) {
        Deferred<String, String, Integer> deferred = setup();
        deferred.reject("oops");

        deferred = setup();
        deferred.notify(50);
        deferred.notify(100);
        deferred.resolve("done");
    }

    private static Deferred<String, String, Integer> setup() {
        Deferred<String, String, Integer> deferred = new DeferredObject<>();
        deferred.promise()
            .done(result -> System.out.println("Completed: " + result))
            .fail(rejection -> System.out.println("Rejected: " + rejection))
            .progress(System.out::println)
            .always((state, result, rejection) -> System.out.println("Something happend"));
        return deferred;
    }

}
