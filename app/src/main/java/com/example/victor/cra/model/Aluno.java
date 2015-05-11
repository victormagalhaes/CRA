package com.example.victor.cra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

public class Aluno implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("matricula")
    private String matricula;

    public Aluno(String matricula) {
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public String getMatricula()
    {
        return matricula;
    }
}