package com.example.victor.cra.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;
import com.example.victor.cra.util.CustomGrid;
import com.example.victor.cra.util.SharedPreferencesHelper;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    boolean isLogged = false;

    GridView grid;
    String[] web = {
            "Login",
            "Listar",
            "Incluir",
            "Excluir",
            "Alterar",
            "CR"
    };

    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferencesHelper sp = new SharedPreferencesHelper(MainActivity.this);
        isLogged = sp.getBoolean("USER_LOGIN");

        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferencesHelper sp = new SharedPreferencesHelper(MainActivity.this);

                if (web[position] == "Login") {
                    Intent it = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(it, 1);
                } else if (web[position] == "Listar" && isLogged) {
                    Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                } else if (web[position] == "Incluir" && isLogged) {

                } else if (web[position] == "Excluir" && isLogged) {

                } else if (web[position] == "Alterar" && isLogged) {

                } else if (web[position] == "CR" && isLogged) {

                } else if (web[position] == "Logout" && isLogged) {
                    web[0] = "Login";
                    imageId[0] = R.drawable.image1;
                    sp.set("USER_LOGIN", false);
                    isLogged = sp.getBoolean("USER_LOGIN");

                    CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
                    grid = (GridView) findViewById(R.id.grid);
                    grid.setAdapter(adapter);
                } else if (!isLogged) {
                    Toast.makeText(MainActivity.this, "Faça login para acessar esta área!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                SharedPreferencesHelper sp = new SharedPreferencesHelper(MainActivity.this);
                isLogged = sp.getBoolean("USER_LOGIN");

                if (isLogged) {
                    web[0] = "Logout";
                    imageId[0] = R.drawable.image7;
                    CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
                    grid = (GridView) findViewById(R.id.grid);
                    grid.setAdapter(adapter);
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
