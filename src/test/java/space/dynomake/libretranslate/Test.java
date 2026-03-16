package space.dynomake.libretranslate;


import space.dynomake.libretranslate.type.LanguageTargets;

import static java.util.Arrays.asList;

public class Test {
    public static void main(String[] args) {
        String translated = Translator.translate(Language.ENGLISH, "Для примера, переведем текст с русского на английский и выведем в консоль:");
        System.out.println(translated);

        // Print a list of supported languages for translation
        LanguageTargets[] supportedLanguages = Translator.supportedLanguages("en");
        System.out.println(asList(supportedLanguages));
    }
}
