package net.suuft.libretranslate;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import net.suuft.libretranslate.type.TranslateResponse;
import net.suuft.libretranslate.util.JsonUtil;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

@UtilityClass
public class Translator {

    @Setter
    private String urlApi = "https://translate.fedilab.app/translate";

    public String translate(@NonNull String from, @NonNull String to, @NonNull String request) {
        try {

            URL url = new URL(urlApi);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("accept", "application/json");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            httpConn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());

            writer.write("q=" + URLEncoder.encode(request, "UTF-8") + "&source=" + from + "&target=" + to + "&format=text");
            writer.flush();
            writer.close();
            httpConn.getOutputStream().close();

            if (!(httpConn.getResponseCode() / 100 == 2)) return "Falled translate!";

            InputStream responseStream = httpConn.getInputStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";

            return JsonUtil.from(response, TranslateResponse.class).getTranslatedText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Falled translate!";
        }
    }

    public String translate(@NonNull Language from, @NonNull Language to, @NonNull String request) {
        if (to == Language.NONE || from == to) return request;
        return translate(from.getCode(), to.getCode(), request);
    }

    public String translate(@NonNull Language to, @NonNull String request) {
        if (to == Language.NONE) return request;
        return translate("auto", to.getCode(), request);
    }
}
