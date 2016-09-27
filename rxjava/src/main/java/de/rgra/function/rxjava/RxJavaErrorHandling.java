package de.rgra.function.rxjava;

import io.reactivex.Observable;

public class RxJavaErrorHandling {

	public static void main(String[] args) {
		Observable<String> errorObservable = Observable.just("1", "2", "3")
			.concatWith(Observable.error(new RuntimeException("Test")));

		errorObservable.doOnError(e -> System.out.println("onErrorResumeNext"))
			.onErrorResumeNext(Observable.just("A", "B", "C"))
			.subscribe(System.out::println, e -> e.printStackTrace());
	}

}
