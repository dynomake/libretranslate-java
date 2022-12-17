package net.suuft.libretranslate.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateRequest {

    String q;
    String source;
    String target;
    String format; //"text",
//    String api_key;
}
