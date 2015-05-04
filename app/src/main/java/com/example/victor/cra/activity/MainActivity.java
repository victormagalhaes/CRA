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

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();
    boolean isLogged = false;

//    @InjectView(R.id.activity_main_data)
//    protected RelativeLayout dataLayout;
//
//    @InjectView(R.id.activity_main_weather)
//    protected RelativeLayout weatherLayout;
//
//    @InjectView(R.id.activity_main_search)
//    protected EditText searchEditText;
//
//    @InjectView(R.id.activity_main_sys_country_value)
//    protected TextView countryTextView;
//
//    @InjectView(R.id.activity_main_sys_sunrise_value)
//    protected TextView sunriseTextView;
//
//    @InjectView(R.id.activity_main_sys_sunset_value)
//    protected TextView sunsetTextView;
//
//    @InjectView(R.id.activity_main_weather_icon)
//    protected ImageView iconImageView;
//
//    @InjectView(R.id.activity_main_weather_text)
//    protected TextView weatherTextView;

    GridView grid;
    String[] web = {
            "Login",
            "Listar",
            "Incluir",
            "Excluir",
            "Alterar",
            "CR"
    } ;

    String[] webLogged = {
            "Logout",
            "Listar",
            "Incluir",
            "Excluir",
            "Alterar",
            "CR"
    } ;
    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };

    int[] imageIdLogged = {
            R.drawable.image7,
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

        SharedPreferences prefs = getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
        isLogged = prefs.getBoolean("USER_LOGIN", false);

        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                    isLogged = false;
                    SharedPreferences pref = getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("USER_LOGIN",false);
                    editor.commit();

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
                // setando em isLogged se o usuário está caso ele venha de Login
                SharedPreferences prefs = getSharedPreferences("USER_LOGIN", Context.MODE_PRIVATE);
                isLogged = prefs.getBoolean("USER_LOGIN", false);

                if (isLogged) {
                    web[0] = "Logout";
                    CustomGrid adapter = new CustomGrid(MainActivity.this, webLogged, imageIdLogged);
                    grid = (GridView) findViewById(R.id.grid);
                    grid.setAdapter(adapter);
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    @OnClick(R.id.activity_main_search_button)
//    protected void onSearchClick() {
//        if (!searchEditText.getText().toString().equals("")) {
//            App.getRestClient().getNotaService().listNotas(searchEditText.getText().toString(), new Callback<List<Nota>>() {
//                @Override
//                public void success(List<Nota> apiResponse, Response response) {
//
//
//                    sunsetTextView.setText("Ninja");
//                    sunriseTextView.setText("Ninja");
//
//                    searchEditText.setText("");
////                    Log.e(TAG, "City name : " + apiResponse.getNota());
//                    dataLayout.setVisibility(View.VISIBLE);
//                    weatherLayout.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    Log.e(TAG, "Error : " + error.getMessage());
//                    searchEditText.setText("");
//                    dataLayout.setVisibility(View.GONE);
//                    weatherLayout.setVisibility(View.GONE);
//                }
//            });
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
