package br.com.pegasus.api.restful.util;

import br.com.pegasus.swagger.character.model.PaginationModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class HttpUtil {

    private static final Executor executor;

    static {
        executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(), r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setName("http-util-executor-" + t.getId());
                    return t;
                });
    }

    public static @NotNull CompletableFuture<ResponseEntity<Void>> createResponse(HttpStatus status) {
        return createResponse(status, null);
    }

    public static @NotNull <T> CompletableFuture<ResponseEntity<T>> createResponse(HttpStatus status, T body) {
        if (body == null) {
            CompletableFuture.supplyAsync(() -> ResponseEntity.status(status).build(), executor);
        }
        return CompletableFuture.supplyAsync(() -> ResponseEntity.status(status).body(body), executor);
    }

    public static @NotNull PaginationModel createPaginationModel(Page<?> pageEntity){
        return PaginationModel.builder()
                .page(pageEntity.getNumber())
                .size(pageEntity.getSize())
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .hasNext(pageEntity.hasNext())
                .hasPrevious(pageEntity.hasPrevious())
                .build();
    }

}
