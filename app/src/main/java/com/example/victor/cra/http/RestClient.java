package com.example.victor.cra.http;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Disciplina;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.http.service.RestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.Callback;


public class RestClient {
    private static final String BASE_URL = "http://192.168.56.1:8000/"; // Esse IP é o IP da máquina virtual do genymotion procurando o meu localhost =]
    private RestService apiService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        apiService = restAdapter.create(RestService.class);
    }

    public void getNota(String user, Callback<List<Nota>> cb) {
        apiService.listNotas(user, cb);
    }

    public void getDisciplinas(Callback<List<Disciplina>> cb) {
        apiService.listDisciplinas(cb);
    }

    public void addNota(Nota nota, Callback<JsonElement> cb) {
        apiService.addNota(nota, cb);
    }

    public void getAluno(String user, Callback<List<Aluno>> cb){
        apiService.listAlunos(user, cb);
    }

    public void alterNota(int idNota, Nota nota, Callback cb) {
        apiService.alterNota(idNota, nota, cb);
    }

    public void deleteNota(int idNota, Callback cb) {
        apiService.deleteNota(idNota, cb);
    }
}
