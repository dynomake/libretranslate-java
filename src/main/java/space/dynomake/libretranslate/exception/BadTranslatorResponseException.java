package space.dynomake.libretranslate.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BadTranslatorResponseException extends RuntimeException {
    int code;
    String host;
}
