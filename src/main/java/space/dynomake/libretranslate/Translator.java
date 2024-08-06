package space.dynomake.libretranslate;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import space.dynomake.libretranslate.exception.BadTranslatorResponseException;
import space.dynomake.libretranslate.type.TranslateResponse;
import space.dynomake.libretranslate.util.JsonUtil;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

@UtilityClass
public class Translator {

    @Setter
    private String urlApi = "https://translate.fedilab.app/translate";

    @Setter
    private String apiKey = "unknown";

    public String translate(@NonNull String from, @NonNull String to, @NonNull String request) {
        return translateDetect(from, to, request).getTranslatedText();
    }

    public TranslateResponse translateDetect(@NonNull String from, @NonNull String to, @NonNull String request) {
        try {

            URL url = new URL(urlApi);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("accept", "application/json");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            httpConn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());

            writer.write("q=" + URLEncoder.encode(request, "UTF-8") + "&source=" + from + "&api_key=" + apiKey + "&target=" + to + "&format=text");
            writer.flush();
            writer.close();
            httpConn.getOutputStream().close();

            if (!(httpConn.getResponseCode() / 100 == 2))
                throw new BadTranslatorResponseException(httpConn.getResponseCode(), urlApi);

            InputStream responseStream = httpConn.getInputStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";

            return JsonUtil.from(response, TranslateResponse.class);
        } catch (Exception e) {
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String translate(@NonNull Language from, @NonNull Language to, @NonNull String request) {
        if (to == Language.NONE || from == to) return request;
        return translate(from.getCode(), to.getCode(), request);
    }

    public TranslateResponse translateDetect(@NonNull Language to, @NonNull String request) {
        return translateDetect("auto", to.getCode(), request);
    }

    public String translate(@NonNull Language to, @NonNull String request) {
        if (to == Language.NONE) return request;
        return translate("auto", to.getCode(), request);
    }
}
