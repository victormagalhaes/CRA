package com.example.victor.cra.http.service;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.client.Response;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Nota;

import java.util.List;
import retrofit.Callback;


public interface RestService {
    @GET("/notas/")
    void listNotas(@Query("aluno") String user, Callback<List<Nota>> callback);

    @GET("/alunos/")
    void listAlunos(@Query("matricula") String user, Callback<List<Aluno>> callback);

    @DELETE("/notas/{id}/")
    void deleteNota(@Path("id") int idNota, Callback<Response> callback);

    @POST("/notas/{id}/")
    void alterNota(@Path("id") int idNota, @Body float nota, Callback<Nota> cb);
}