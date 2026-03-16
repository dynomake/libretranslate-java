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

    NONE("none", "No translation"),

    ENGLISH("en", "English"),
    ALBANIAN("sq", "Albanian"),
    ARABIC("ar", "Arabic"),
    AZERBAIJANI("az", "Azerbaijani"),
    BASQUE("eu", "Basque"),
    BENGALI("bn", "Bengali"),
    BULGARIAN("bg", "Bulgarian"),
    CATALAN("ca", "Catalan"),
    CHINESE("zh-Hans", "Chinese"),
    CHINESE_TRADITIONAL("zh-Hant", "Chinese (traditional)"),
    CZECH("cs", "Czech"),
    DANISH("da", "Danish"),
    DUTCH("nl", "Dutch"),
    ESPERANTO("eo", "Esperanto"),
    ESTONIAN("et", "Estonian"),
    FINNISH("fi", "Finnish"),
    FRENCH("fr", "French"),
    GALICIAN("gl", "Galician"),
    GERMAN("de", "German"),
    GREEK("el", "Greek"),
    HEBREW("he", "Hebrew"),
    HINDI("hi", "Hindi"),
    HUNGARIAN("hu", "Hungarian"),
    INDONESIAN("id", "Indonesian"),
    IRISH("ga", "Irish"),
    ITALIAN("it", "Italian"),
    JAPANESE("ja", "Japanese"),
    KABYLE("kab", "Kabyle"),
    KOREAN("ko", "Korean"),
    KYRGYZ("ky", "Kyrgyz"),
    LATVIAN("lv", "Latvian"),
    LITHUANIAN("lt", "Lithuanian"),
    MALAY("ms", "Malay"),
    NORWEGIAN("nb", "Norwegian"),
    PERSIAN("fa", "Persian"),
    POLISH("pl", "Polish"),
    PORTUGUESE("pt", "Portuguese"),
    PORTUGUESE_BRAZIL("pt-BR", "Portuguese (Brazil)"),
    ROMANIAN("ro", "Romanian"),
    RUSSIAN("ru", "Russian"),
    SLOVAK("sk", "Slovak"),
    SLOVENIAN("sl", "Slovenian"),
    SPANISH("es", "Spanish"),
    SWEDISH("sv", "Swedish"),
    TAGALOG("tl", "Tagalog"),
    THAI("th", "Thai"),
    TURKISH("tr", "Turkish"),
    UKRAINIAN("uk", "Ukrainian"),
    URDU("ur", "Urdu"),
    VIETNAMESE("vi", "Vietnamese")
    ;

    String code;
    String name;

    @Override
    public String toString() {
        return name;
    }

    public static Language fromCode(String code) {
        return Arrays.stream(Language.values())
                .filter(lang -> lang.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(NONE);
    }
}
