LibreTranslate Java Restful Client
---
At some point, we had to translate the Java text, but we didn't find comfortable and understandable libraries, so I wrote my decision.
### `Add as depend:`

| **Gradle:**

```groovy
repositories {
    // other repositories
    maven {
        name = "clojars.org"
        url = uri("https://repo.clojars.org")
    }
}

dependencies {
    // other depends
    implementation 'net.clojars.suuft:libretranslate-java:1.0.4'
}
```

| **Maven:**

Repository:

```xml
<repository>
    <id>clojars.org</id>
    <url>https://repo.clojars.org</url>
</repository>
```

Depend:

```xml

<dependency>
    <groupId>net.clojars.suuft</groupId>
    <artifactId>libretranslate-java</artifactId>
    <version>1.0.4</version>
</dependency>
```
### `Usage:`
For example, we will translate the text from Russian to English and put it in the console:
```java
System.out.println(Translator.translate(Language.RUSSIAN, Language.ENGLISH, "Для примера, переведем текст с русского на английский и выведем в консоль:"));
```
If you have LibreTranslate installed on your server, you can change the URL for requests. Example:
```java
Translator.setUrlApi("https://libretranslate.de/translate");
```