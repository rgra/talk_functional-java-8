/*
 * @(#)VertxRouting.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */

package io.github.rgra.live;

import java.util.concurrent.TimeUnit;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

/**
 * @author rgra
 */
public class VertxRouting {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);
        router.route("/bremen").handler(routingContext -> {
            routingContext.response()
                .setChunked(true)
                .write("Germany")
                .end();
        });
        router.route("/copenhagen").handler(routingContext -> {
            routingContext.response()
                .setChunked(true)
                .write("Denmark")
                .end();
        });

        vertx.createHttpServer(new HttpServerOptions())
            // .requestHandler(router::accept)
            .listen(8080, res -> {
                if (res.failed()) {
                    res.cause().printStackTrace();
                }
            });
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
