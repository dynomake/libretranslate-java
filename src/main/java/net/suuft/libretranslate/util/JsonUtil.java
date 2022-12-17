package net.suuft.libretranslate.util;

import com.google.gson.Gson;
import lombok.NonNull;
import lombok.experimental.UtilityClass;


@UtilityClass
public class JsonUtil {

    private final Gson gson = new Gson();

    public String to(@NonNull Object object) {
        return gson.toJson(object);
    }

    public <T> T from(@NonNull String s, Class<T> tClass) {
        return gson.fromJson(s, tClass);
    }
}
