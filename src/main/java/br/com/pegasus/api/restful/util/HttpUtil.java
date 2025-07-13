package br.com.pegasus.api.restful.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class HttpUtil {

    private static Executor executor;

    public static @NotNull CompletableFuture<ResponseEntity<Void>> createResponse(HttpStatus status) {
        return createResponse(status, null);
    }

    public static @NotNull <T> CompletableFuture<ResponseEntity<T>> createResponse(HttpStatus status, T body) {
        Objects.requireNonNull(status, "HttpStatus não pode ser null");

        return CompletableFuture.supplyAsync(() -> (body == null)
                        ? ResponseEntity.status(status).build()
                        : ResponseEntity.status(status).body(body)
                , createExecutor());
    }


    private static @NotNull Executor createExecutor(){
        if(executor == null){
            executor = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors(), r -> {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        t.setName("http-util-executor-" + t.getId());
                        return t;
                    });
        }
        return executor;
    }
}
