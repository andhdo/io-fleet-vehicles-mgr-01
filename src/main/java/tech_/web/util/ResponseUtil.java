package tech_.web.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {
    public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> maybeResponse, HttpHeaders header) {
        return maybeResponse.map(response -> ResponseEntity.ok().headers(header).body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null);
    }
}
