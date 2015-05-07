package com.example.victor.cra.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Aluno;
import com.example.victor.cra.util.SharedPreferencesHelper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends Activity {
    @InjectView(R.id.activity_main_search)
    protected EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
    }


    @OnClick(R.id.activity_main_search_button)
    protected void onSearchClick() {

        if (!searchEditText.getText().toString().equals("")) {

            App.getRestClient().getAluno(searchEditText.getText().toString(), new Callback<List<Aluno>>() {
                @Override
                public void success(List<Aluno> alunos, Response response) {
                    List<Aluno> resultado = alunos;
                    if (resultado.isEmpty()) {
                        searchEditText.setText("");
                        Toast.makeText(LoginActivity.this, "Tente outra vez", Toast.LENGTH_SHORT).show();
                    } else if (resultado.get(0).getMatricula().toString().equals(searchEditText.getText().toString())) {
                        SharedPreferencesHelper sp = new SharedPreferencesHelper(LoginActivity.this);
                        sp.set("USER_LOGIN", true);
                        sp.set("MATRICULA", resultado.get(0).getMatricula().toString());

                        finish();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    searchEditText.setText("");
                    Toast.makeText(LoginActivity.this, "Tente outra vez", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
