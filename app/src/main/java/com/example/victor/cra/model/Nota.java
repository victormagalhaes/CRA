package com.example.victor.cra.model;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

@Parcel
public class Nota {
    @SerializedName("nota")
    private Double nota;

    @SerializedName("aluno")
    private Aluno aluno;

    @SerializedName("disciplina")
    private Disciplina disciplina;

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return this.getDisciplina().getNome() + " - " + this.getNota().toString();
    }
}
