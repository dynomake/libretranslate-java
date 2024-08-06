LibreTranslate Java Restful Client
---
At some point we had to translate text in Java but we couldn't find any understandable libraries that we were comfortable with. So I wrote this library.
### `Add as depend:`

| **Gradle:**

```groovy
repositories {
    // other repositories
    maven {
        name = "dynomakeRepository"
        url = uri("https://maven.dynomake.space/releases")
    }
}

dependencies {
    // other depends
    implementation "space.dynomake:libretranslate-java:1.0.9"
}
```

| **Maven:**

Repository:

```xml
<repository>
    <id>dynomakeRepository</id>
    <url>https://maven.dynomake.space/releases</url>
</repository>
```

Depend:

```xml
<dependency>
    <groupId>space.dynomake</groupId>
    <artifactId>libretranslate-java</artifactId>
    <version>1.0.9</version>
</dependency>
```
### `Usage:`
For example, we will translate the text from Russian to English and put it in the console:
```java
System.out.println(Translator.translate(Language.RUSSIAN, Language.ENGLISH, "Для примера, переведем текст с русского на английский и выведем в консоль:"));
```
If you have LibreTranslate installed on your server, you can change the URL for requests. Example:
```java
Translator.setUrlApi("https://your_domain_with_libretranslate.com/translate");
Translator.setApiKey("your_api_key_if_required");
```
