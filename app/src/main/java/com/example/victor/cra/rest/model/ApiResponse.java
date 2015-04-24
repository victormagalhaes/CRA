package com.example.victor.cra.rest.model;

import com.example.victor.cra.model.Disciplina;
import com.example.victor.cra.model.Aluno;
import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

@Parcel
public class ApiResponse {
    @SerializedName("disciplina")
    private Disciplina disciplina;

    @SerializedName("aluno")
    private Aluno aluno;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }
}
