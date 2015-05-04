package com.example.victor.cra.rest.model;

import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Nota;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;
import java.util.ArrayList;

@Parcel
public class ApiResponse {
    @SerializedName("nota")
    private ArrayList<Nota> nota;

    @SerializedName("aluno")
    private ArrayList<Aluno> aluno;

    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(ArrayList<Aluno> aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Nota> getNota() {
        return nota;
    }

    public void setNota(ArrayList<Nota> nota) {
        this.nota = nota;
    }
}
