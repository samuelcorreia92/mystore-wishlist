package com.mystore.wishlist.apprest.config;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private boolean ok;
    private ZonedDateTime timestamp;
    private String message;
    private String trace;
    private T data;

    public ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.ok().body(
                new Response<>(true, ZonedDateTime.now(), "ok", null, data)
        );
    }

}
