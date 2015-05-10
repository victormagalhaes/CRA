package com.example.victor.cra.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.SharedPreferencesHelper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AlterarNotaActivity extends ActionBarActivity {
    @InjectView(R.id.activity_main_search)
    protected EditText searchEditText;

    protected Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_nota);
        ButterKnife.inject(this);

        Intent it = getIntent();
        nota = (Nota) it.getExtras().getSerializable("nota");

        String nomeDisciplina = it.getStringExtra("nomeDisciplina");
        setTitle(nomeDisciplina);

    }

    @OnClick(R.id.activity_main_search_button)
    protected void onSearchClick() {
        if (!searchEditText.getText().toString().equals("")) {
            Double notaAtualizada = Double.parseDouble(searchEditText.getText().toString());

            nota.setNota(notaAtualizada);

            App.getRestClient().alterNota(nota.getId(), nota, new Callback() {
                @Override
                public void success(Object o, Response response) {
                    finish();
                }

                @Override
                public void failure(RetrofitError error) {
                    searchEditText.setText("");
                    Toast.makeText(AlterarNotaActivity.this, "Tente outra vez", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
