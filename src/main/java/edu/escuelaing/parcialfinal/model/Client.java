package edu.escuelaing.parcialfinal.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Client {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String BODY = "https://www.alphavantage.co/query?";
    private static final String API_KEY = "demo";
    private static final Map<String, String> cache = new HashMap<>();

    public String invoke(String val, String type) throws IOException {
        String url = buildUrl(val, type);

        if (cache.containsKey(url)) {
            return cache.get(url);
        }

        String response = sendGetRequest(url);
        if (response != null) {
            cache.put(url, response);
        }

        return response;
    }

    public String TestInvoke(String url) throws IOException {
        return sendGetRequest(url);
    }

    private String buildUrl(String val, String type) {
        StringBuilder urlBuilder = new StringBuilder(BODY);
        urlBuilder.append("function=").append(type)
                .append("&symbol=").append(val)
                .append("&apikey=").append(API_KEY);

        if (type.equals(Type.TIME_SERIES_INTRADAY.name())) {
            urlBuilder.append("&interval=5min");
        }

        return urlBuilder.toString();
    }

    private String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(con);
        } else {
            System.err.println("GET request not worked, response code: " + responseCode);
            return "GET request not worked";
        }
    }

    private String readResponse(HttpURLConnection con) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }
}
