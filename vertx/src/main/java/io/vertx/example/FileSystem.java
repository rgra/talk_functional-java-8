/*
 * @(#)FileSystem.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2016
 * ===========================================================================
 * Created on 25.09.2016
 */

package io.vertx.example;

import io.vertx.core.Vertx;

/**
 * @author rgra
 */
public class FileSystem {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx
            .fileSystem()
            .readFile("d:/test.csv", result -> {
                if (result.succeeded()) {
                    System.out.println(result.result());
                }
                else {
                    System.err.println("Oh oh ..." + result.cause());
                }
                vertx.close();
            });
    }
}
