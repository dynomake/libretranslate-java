package space.dynomake.libretranslate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Language {

    RUSSIAN("ru"),
    ENGLISH("en"),
    ARABIC("ar"),
    AZERBAIJANI("az"),
    CATALAN("ca"),
    CHINESE("zh"),
    CZECH("cs"),
    DANISH("da"),
    DUTCH("nl"),
    ESPERANTO("eo"),
    FINNISH("fi"),
    FRENCH("fr"),
    GERMAN("de"),
    GREEK("el"),
    HEBREW("he"),
    HINDI("hi"),
    HUNGARIAN("hu"),
    INDONESIAN("id"),
    IRISH("ga"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("ko"),
    PERSIAN("fa"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    SLOVAK("sk"),
    SPANISH("es"),
    SWEDISH("sv"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    NONE("none")
    ;

    String code;

    public static Language fromCode(String code) {
        return Arrays.stream(Language.values())
                .filter(lang -> lang.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(NONE);
    }
}
