package space.dynomake.libretranslate;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import space.dynomake.libretranslate.exception.BadTranslatorResponseException;
import space.dynomake.libretranslate.type.TranslateResponse;
import space.dynomake.libretranslate.util.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

import static java.nio.charset.StandardCharsets.UTF_8;

@UtilityClass
public class Translator {

    @Setter
    private String urlApi = "https://translate.fedilab.app/translate";

    @Setter
    private String apiKey = "unknown";

    @Setter
    private static int connectTimeout = 5000; // 5 seconds

    @Setter
    private static int readTimeout = 5000;

    public String translate(@NonNull String from, @NonNull String to, @NonNull String request) {
        return translateDetect(from, to, request).getTranslatedText();
    }

    public TranslateResponse translateDetect(@NonNull String from, @NonNull String to, @NonNull String request) {
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(urlApi);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(connectTimeout);
            httpConn.setReadTimeout(readTimeout);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0");

            httpConn.setDoOutput(true);

            // Build request body
            String requestBody = "q=" + URLEncoder.encode(request, "UTF-8") + "&source=" + from + "&target=" + to + "&format=text";
            // Write request
            try (OutputStream outputStream = httpConn.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream, UTF_8)) {
                writer.write(requestBody);
                writer.flush();
            }

            // Check response code before reading
            int responseCode = httpConn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new BadTranslatorResponseException(responseCode, urlApi);
            }

            try (InputStream responseStream = httpConn.getInputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, UTF_8))) {
                return JsonUtil.from(reader, TranslateResponse.class);
            }
        } catch (IOException e) {
            throw new RuntimeException("Network error during translation", e);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Translation failed", e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
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
