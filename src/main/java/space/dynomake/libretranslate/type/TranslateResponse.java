package space.dynomake.libretranslate.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateResponse {
    String translatedText;

    DetectedLanguage detectedLanguage;

    @Getter
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class DetectedLanguage {
        int confidence;
        String language;
    }
}
