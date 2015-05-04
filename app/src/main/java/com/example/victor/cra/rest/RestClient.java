package com.example.victor.cra.rest;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.rest.service.NotaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.Callback;


public class RestClient {
    private static final String BASE_URL = "http://10.1.209.39:8000/";
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

    public void getAluno(String user, Callback<List<Aluno>> cb){

        apiService.listAlunos(user, cb);

    }

}
