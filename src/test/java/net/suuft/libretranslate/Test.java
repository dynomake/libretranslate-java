package net.suuft.libretranslate;

public class Test {
    public static void main(String[] args) {
        Translator.setUrlApi("https://libretranslate.de/translate");
        System.out.println(Translator.translate("ru", "en", "Для примера, переведем текст с русского на английский и выведем в консоль:"));
    }
}
