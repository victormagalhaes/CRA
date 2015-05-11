package com.example.victor.cra.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.adapter.NotaAdapter;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.SharedPreferencesHelper;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by victor on 10/05/15.
 */
public class CalculoCRActivity extends ActionBarActivity {
    @InjectView(R.id.CRtextView)
    protected TextView crTextView;

    @InjectView(R.id.cargaCumpridaTextView)
    protected TextView cargaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cr);
        ButterKnife.inject(this);

        SharedPreferencesHelper sp = new SharedPreferencesHelper(CalculoCRActivity.this);
        String matricula = sp.getString("MATRICULA");

        if (!matricula.equals("")) {
            App.getRestClient().getNota(matricula, new Callback<List<Nota>>() {
                @Override
                public void success(List<Nota> notas, Response response) {
                    if (!notas.isEmpty()) {
                        Double somaNumerador = 0.0;
                        Double somaDenominador = 0.0;
                        try {
                            for (int i = 0; i < notas.size(); i++) {
                                Nota n = new Nota(notas.get(i).getNota(), notas.get(i).getAluno(), notas.get(i).getDisciplina());
                                Double notaVezesCargaHoraria = (Double) n.getNota() * n.getDisciplina().getCargaHoraria();
                                somaNumerador = somaNumerador + notaVezesCargaHoraria;
                                somaDenominador = somaDenominador + Double.parseDouble(String.valueOf(n.getDisciplina().getCargaHoraria()));
                            }
                            Double cra = (Double) somaNumerador/somaDenominador;
                            cargaTextView.setText("Carga horária cumprida = " + somaDenominador.toString());

                            DecimalFormat df = new DecimalFormat("#.00");
                            crTextView.setText("CRA = " + df.format(cra));
                        } catch (Exception e) {
                            Log.d("Erro", e.toString());
                        }
                    } else {
                        Toast.makeText(CalculoCRActivity.this, "Não possui nenhuma nota!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(CalculoCRActivity.this, "Não foi possível carregar as notas!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
