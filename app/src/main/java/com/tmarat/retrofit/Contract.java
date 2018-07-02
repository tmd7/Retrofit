package com.tmarat.retrofit;

import android.arch.lifecycle.LiveData;
import com.tmarat.retrofit.pojo.WeatherRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Contract {
  interface View {

  }

  interface ViewModel {
    void onUserClicked(String userInput);
    LiveData<DataWeather> getDataWeather();
  }

  interface WeatherApiEndpoint {
    @GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("appid") String keyApi);
  }
}
