package com.example.victor.cra.rest.service;

import com.example.victor.cra.rest.model.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface DisciplinaService {
    @GET("/notas/")
    public void getDisciplina(@Query("aluno") String strAluno, Callback<ApiResponse> callback);
}
