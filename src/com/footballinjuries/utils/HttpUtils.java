package com.footballinjuries.utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HttpUtils {

    public static Response getPlayerStatistics(String footballerName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-football-beta.p.rapidapi.com/players?search=" + footballerName + "&league=2")
                .get()
                .addHeader("x-rapidapi-host", "api-football-beta.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8b7a2aa6a8mshd15126ff3a2d037p11abc4jsnfb17f8ac72bb")
                .build();

        return client.newCall(request).execute();
    }
}
