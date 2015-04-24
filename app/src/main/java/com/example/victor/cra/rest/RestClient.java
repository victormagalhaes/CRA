package com.example.victor.cra.rest;

import com.example.victor.cra.rest.service.DisciplinaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient {
    private static final String BASE_URL = "http://localhost:8000/";
    private DisciplinaService apiService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(DisciplinaService.class);
    }

    public DisciplinaService getDisciplinaService() {
        return apiService;
    }
}
