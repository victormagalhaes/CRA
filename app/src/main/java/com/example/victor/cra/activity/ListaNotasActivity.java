package com.example.victor.cra.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.SharedPreferencesHelper;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListaNotasActivity extends ListActivity {
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
                    ArrayAdapter<Nota> notaAdapter = new ArrayAdapter<Nota>(ListaNotasActivity.this, android.R.layout.simple_list_item_1, notas);
                    setListAdapter(notaAdapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ListaNotasActivity.this, "Não possui nenhuma nota, ainda!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
