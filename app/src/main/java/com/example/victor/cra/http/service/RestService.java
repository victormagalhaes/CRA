package com.example.victor.cra.http.service;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.client.Response;
import retrofit.Callback;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Disciplina;
import com.example.victor.cra.model.Nota;
import com.google.gson.JsonElement;

import java.util.List;

public interface RestService {
    @GET("/notas/")
    void listNotas(@Query("aluno") String user, Callback<List<Nota>> callback);

    @POST("/notas/")
    void addNota(@Body Nota nota, Callback<JsonElement> callback);

    @GET("/disciplinas/")
    void listDisciplinas(@Query("matricula_excluir") String matriculaExcluir, Callback<List<Disciplina>> callback);

    @GET("/alunos/")
    void listAlunos(@Query("matricula") String user, Callback<List<Aluno>> callback);

    @DELETE("/notas/{id}/")
    void deleteNota(@Path("id") int idNota, Callback<Response> callback);

    @PUT("/notas/{id}/")
    void alterNota(@Path("id") int idNota, @Body Nota nota, Callback<Response> callback);
}