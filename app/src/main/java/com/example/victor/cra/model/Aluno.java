package com.example.victor.cra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Aluno {
    @SerializedName("matricula")
    private String matricula;

    public String getMatricula()
    {
        return matricula;
    }
}