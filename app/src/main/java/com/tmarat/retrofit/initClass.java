package com.tmarat.retrofit;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class initClass extends Application {

  private static final String BASE_URL = "http://api.openweathermap.org/";

  @Override public void onCreate() {
    super.onCreate();
    initRetfrofit();
  }

  private void initRetfrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
}
