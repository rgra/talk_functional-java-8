package de.rgra.function.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class RxJavaTimed {

	public static void main(String[] args) {
		Observable<String> list = Observable.just("Rabea", "vJUG", "RxJava").repeat(20);
		Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);

		Observable<String> timedNameEmitter = timer
			.zipWith(list, (x, y) -> y);

		// window
		timedNameEmitter.window(10, TimeUnit.SECONDS)
			.subscribe(o -> o.startWith("10sec Group: ").toList()
				.subscribe(System.out::println));

		// take 5 sec
		timedNameEmitter.take(5, TimeUnit.SECONDS)
			.doOnComplete(() -> System.out.println("----Five seconds gone"))
			.subscribe(System.out::println);

		// take 30 sec blocking
		timedNameEmitter.take(30, TimeUnit.SECONDS)
			.blockingSubscribe(System.out::println, e -> e.printStackTrace());

	}

}
