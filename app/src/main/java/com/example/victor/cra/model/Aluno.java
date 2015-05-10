package com.example.victor.cra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Aluno implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("matricula")
    private String matricula;

    public int getId() {
        return id;
    }

    public String getMatricula()
    {
        return matricula;
    }
}