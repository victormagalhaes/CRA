package com.example.victor.cra.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Disciplina;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.SharedPreferencesHelper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IncluirNotaActivity extends ActionBarActivity {
    @InjectView(R.id.activity_main_search)
    protected EditText searchEditText;

    @InjectView(R.id.spinner)
    protected Spinner listaDisciplinasSpinner;

    protected Disciplina disciplinaSelecionada;
    protected Aluno alunoAutenticado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_nota);
        ButterKnife.inject(this);

        SharedPreferencesHelper sp = new SharedPreferencesHelper(IncluirNotaActivity.this);
        String matricula = sp.getString("MATRICULA");

        App.getRestClient().getDisciplinas(matricula, new Callback<List<Disciplina>>() {
            @Override
            public void success(List<Disciplina> disciplinas, Response response) {
                if (!disciplinas.isEmpty()) {
                    ArrayAdapter<Disciplina> adapterDisciplina = new ArrayAdapter<Disciplina>(IncluirNotaActivity.this, android.R.layout.simple_spinner_item, disciplinas);
                    adapterDisciplina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    listaDisciplinasSpinner.setAdapter(adapterDisciplina);

                    listaDisciplinasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            disciplinaSelecionada = (Disciplina) listaDisciplinasSpinner.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    SharedPreferencesHelper sp = new SharedPreferencesHelper(IncluirNotaActivity.this);
                    String matricula = sp.getString("MATRICULA");
                    alunoAutenticado = new Aluno(matricula);
                } else {
                    Toast.makeText(IncluirNotaActivity.this, "Você já incluiu todas as disciplinas!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(IncluirNotaActivity.this, "Não foi possível carregar as disciplinas!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick(R.id.activity_main_search_button)
    protected void onSearchClick() {
        if (!searchEditText.getText().toString().equals("")) {
            Double notaAtualizada = Double.parseDouble(searchEditText.getText().toString());
            Nota notaASerIncluida = new Nota(notaAtualizada, alunoAutenticado, disciplinaSelecionada);

            App.getRestClient().addNota(notaASerIncluida, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement json, Response response) {
                    if (((JsonObject) json).get("success").getAsBoolean()) {
                        Toast.makeText(IncluirNotaActivity.this, "Nota incluída!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(IncluirNotaActivity.this, "Você já possui essa nota!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(IncluirNotaActivity.this, "Não foi possível incluir a nota!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
