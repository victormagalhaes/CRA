package com.example.victor.cra.rest.service;

import retrofit.http.GET;

import com.example.victor.cra.model.Aluno;
import retrofit.Callback;
import retrofit.http.Query;


public interface AlunoService {
    @GET("/alunos/")
    void detailAluno(@Query("matricula") String user, Callback<Aluno> callback);
}