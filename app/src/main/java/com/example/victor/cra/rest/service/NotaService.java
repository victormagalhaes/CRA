package com.example.victor.cra.rest.service;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Nota;
import java.util.List;
import retrofit.Callback;



public interface NotaService {
    @GET("/notas/")
    void listNotas(@Query("aluno") String user, Callback<List<Nota>> callback);

    @GET("/alunos/")
    void listAlunos(@Query("matricula") String user, Callback<List<Aluno>> callback);






}