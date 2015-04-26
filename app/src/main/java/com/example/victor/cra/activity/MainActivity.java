package com.example.victor.cra.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();

    @InjectView(R.id.activity_main_data)
    protected RelativeLayout dataLayout;

    @InjectView(R.id.activity_main_weather)
    protected RelativeLayout weatherLayout;

    @InjectView(R.id.activity_main_search)
    protected EditText searchEditText;

    @InjectView(R.id.activity_main_sys_country_value)
    protected TextView countryTextView;

    @InjectView(R.id.activity_main_sys_sunrise_value)
    protected TextView sunriseTextView;

    @InjectView(R.id.activity_main_sys_sunset_value)
    protected TextView sunsetTextView;

    @InjectView(R.id.activity_main_weather_icon)
    protected ImageView iconImageView;

    @InjectView(R.id.activity_main_weather_text)
    protected TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.activity_main_search_button)
    protected void onSearchClick() {
        if (!searchEditText.getText().toString().equals("")) {
            App.getRestClient().getNotaService().listNotas(searchEditText.getText().toString(), new Callback<List<Nota>>() {
                @Override
                public void success(List<Nota> apiResponse, Response response) {


                    sunsetTextView.setText("Ninja");
                    sunriseTextView.setText("Ninja");

                    searchEditText.setText("");
//                    Log.e(TAG, "City name : " + apiResponse.getNota());
                    dataLayout.setVisibility(View.VISIBLE);
                    weatherLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, "Error : " + error.getMessage());
                    searchEditText.setText("");
                    dataLayout.setVisibility(View.GONE);
                    weatherLayout.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
