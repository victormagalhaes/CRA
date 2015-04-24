package com.example.victor.cra.app;

import android.app.Application;

import com.example.victor.cra.rest.RestClient;

public class App extends Application
{
    private static RestClient restClient;

    @Override
    public void onCreate()
    {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }
}
