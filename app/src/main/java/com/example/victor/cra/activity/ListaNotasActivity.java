package com.example.victor.cra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.CustomGrid;
import com.example.victor.cra.util.SharedPreferencesHelper;
import com.example.victor.cra.adapter.NotaAdapter;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.fortysevendeg.swipelistview.SwipeListViewListener;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListaNotasActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplinas);

        SharedPreferencesHelper sp = new SharedPreferencesHelper(ListaNotasActivity.this);
        String matricula = sp.getString("MATRICULA");

        if (!matricula.equals("")) {
            App.getRestClient().getNota(matricula, new Callback<List<Nota>>() {
                @Override
                public void success(List<Nota> notas, Response response) {
                    if (!notas.isEmpty()) {
                        SwipeListView swipeListView = (SwipeListView) findViewById(R.id.lista_notas);

                        NotaAdapter notaAdapter = new NotaAdapter(ListaNotasActivity.this, R.layout.custom_row, notas);
                        swipeListView.setAdapter(notaAdapter);
                    } else {
                        Toast.makeText(ListaNotasActivity.this, "Não possui nenhuma nota!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ListaNotasActivity.this, "Não foi possível carregar as notas!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                finish();
                startActivity(getIntent());

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
