package com.example.victor.cra.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Disciplina {
    @SerializedName("id")
    private int id;

    @SerializedName("nome")
    private String nome;

    @SerializedName("carga_horaria")
    private int cargaHoraria;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
}