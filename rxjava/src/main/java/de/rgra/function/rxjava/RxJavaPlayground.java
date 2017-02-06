package de.rgra.function.rxjava;

import io.reactivex.Observable;

public class RxJavaPlayground {

	public static void main(String[] args) {

		Observable<String> list = Observable.just("Rabea", "vJUG", "RxJava");
		Observable<String> observable = list
			.filter(s -> s.startsWith("R"));
		observable
			.map(s -> s.length())
			.subscribe(System.out::println);
		observable
			.subscribe(System.out::println);
		
		// onErrorResumeNext
		Observable<String> errorObservable = Observable.just("1", "2", "3")
			.concatWith(Observable.error(new RuntimeException("Test")));
		errorObservable.doOnError(e -> System.out.println("onErrorResumeNext"))
			.onErrorResumeNext(Observable.just("A", "B", "C"))
			.subscribe(System.out::println, e -> e.printStackTrace());
	}

}
