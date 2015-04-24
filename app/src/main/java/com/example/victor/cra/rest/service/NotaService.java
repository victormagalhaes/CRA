package com.example.victor.cra.rest.service;

import retrofit.http.GET;
import retrofit.http.Query;
import com.example.victor.cra.model.Nota;
import java.util.List;
import retrofit.Callback;



public interface NotaService {
    @GET("/notas/")
    List<Nota> listNotas(@Query("aluno") String user, Callback<Nota> callback);
}