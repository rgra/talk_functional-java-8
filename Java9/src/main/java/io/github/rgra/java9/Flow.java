/*
 * @(#)Flow.java
 * ===========================================================================
 * (C) Copyright Rabea Gransberger 2017
 * ===========================================================================
 * Created on 04.11.2017
 */

package io.github.rgra.java9;

import java.util.Arrays;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @see https://community.oracle.com/docs/DOC-1006738
 */
public class Flow {

    public static void main(String[] args) throws InterruptedException {
        // Create Publisher
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        MySubscriber<String> subscriber = new MySubscriber<>() {
            protected void onNextItem(String item) {
                System.out.println("Got : " + item);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        };
        publisher.subscribe(subscriber);

        // Publish items
        System.out.println("Publishing Items...");
        String[] items = { "1", "x", "2", "x", "3", "x" };
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();

        TimeUnit.SECONDS.sleep(10);
    }

    public abstract static class MySubscriber<T> implements Subscriber<T> {
        private Subscription subscription;

        @Override
        public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public final void onNext(T item) {
            onNextItem(item);
            subscription.request(1);
        }

        protected abstract void onNextItem(T item);

    }
}
