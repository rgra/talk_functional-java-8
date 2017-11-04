package io.github.rgra.java9.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpClient.Version;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

/**
 * @author rgra
 */
public class HTTP {

    public static void main(String[] args)
            throws URISyntaxException {
        jdk.incubator.http.HttpClient httpClient = HttpClient.newBuilder()
            .version(Version.HTTP_2)
            .build();

        CompletableFuture<Void> slowResponse = async(httpClient, "http://oredev.org")
            .thenApply(response -> "Response Slow: " + response.body().length())
            .exceptionally(t -> "Failed Local: " + t.getMessage())
            .thenAccept(System.out::println);

        CompletableFuture<Void> fastResponse = async(httpClient, "http://google.de")
            .thenApply(response -> "Response Fast: " + response.body().length())
            .exceptionally(t -> "Failed Local: " + t.getMessage())
            .thenAccept(System.out::println);

        // CompletableFuture<Void> slowResponse = async(httpClient, "http://127.0.0.1:8080/slow")
        // .thenApply(response -> "Response Slow: " + response.body())
        // .exceptionally(t -> "Failed Local: " + t.getMessage())
        // .thenAccept(System.out::println);
        //
        // CompletableFuture<Void> fastResponse = async(httpClient, "http://127.0.0.1:8080/bremen")
        // .thenApply(response -> "Response Fast: " + response.body())
        // .exceptionally(t -> "Failed Local: " + t.getMessage())
        // .thenAccept(System.out::println);

        CompletableFuture.allOf(fastResponse, slowResponse)
            .join();
    }

    private static CompletableFuture<HttpResponse<String>> async(HttpClient httpClient, String url)
            throws URISyntaxException {
        return httpClient.sendAsync(get(url),
            HttpResponse.BodyHandler.asString(StandardCharsets.UTF_8));
    }

    private static HttpRequest get(String url) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET().build();
    }

}
