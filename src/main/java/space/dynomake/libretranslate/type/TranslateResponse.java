package space.dynomake.libretranslate.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateResponse {
    String translatedText;

    DetectedLanguage detectedLanguage;

    @ToString
    @Getter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DetectedLanguage {
        int confidence;
        String language;
    }
}
