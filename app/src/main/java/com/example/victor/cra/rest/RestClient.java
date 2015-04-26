package com.example.victor.cra.rest;

import com.example.victor.cra.rest.service.NotaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {
    private static final String BASE_URL = "http://192.168.1.105:8000/";
    private NotaService apiService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(NotaService.class);
    }

    public NotaService getNotaService() {
        return apiService;
    }
}
