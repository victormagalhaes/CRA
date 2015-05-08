package com.example.victor.cra.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.SharedPreferencesHelper;
import com.example.victor.cra.util.adapter.NotaAdapter;
import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.fortysevendeg.swipelistview.SwipeListViewListener;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ListaNotasActivity extends Activity {
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
//                    ArrayAdapter<Nota> notaAdapter = new ArrayAdapter<Nota>(ListaNotasActivity.this, android.R.layout.simple_list_item_1, notas);
//                    setListAdapter(notaAdapter);
                    SwipeListView swipeListView = (SwipeListView) findViewById(R.id.lista_notas);

                    NotaAdapter notaAdapter = new NotaAdapter(ListaNotasActivity.this, R.layout.custom_row, notas);
                    swipeListView.setAdapter(notaAdapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ListaNotasActivity.this, "Não possui nenhuma nota, ainda!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
