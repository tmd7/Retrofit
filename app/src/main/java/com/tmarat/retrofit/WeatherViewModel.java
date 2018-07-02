package com.tmarat.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.tmarat.retrofit.pojo.WeatherRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherViewModel extends ViewModel
    implements Contract.ViewModel {

  private MutableLiveData<DataWeather> mutableLiveData;

  private static final String BASE_URL = "http://api.openweathermap.org/";
  private static final String API = "be4254a9c1592f329d3b479b522e69c3";

  private String city;
  private Contract.WeatherApiEndpoint endpoint;

  private void initRetfrofit() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  private void requestRetrofit(final String city, String api) {
    endpoint.loadWeather(city, API)
        .enqueue(new Callback<WeatherRequest>() {
          @Override
          public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
            if (response.body() != null) {
              DataWeather dataWeather = new DataWeather();
              dataWeather.setCity(city);
              dataWeather.setTem(String.valueOf(response.body().getMain().getTemp()));
              dataWeather.setHum(String.valueOf(response.body().getMain().getHumidity()));
              dataWeather.setPress(String.valueOf(response.body().getMain().getPressure()));

              mutableLiveData.postValue(dataWeather);
            }
          }

          @Override public void onFailure(Call<WeatherRequest> call, Throwable t) {

          }
        });
  }

  @Override
  public LiveData<DataWeather> getDataWeather() {
    if (mutableLiveData != null) {
      mutableLiveData = new MutableLiveData<>();
    }
    return mutableLiveData;
  }

  @Override public void onUserClicked(String userInput) {
    // TODO: 02.07.2018 RestrofitRequest(userInput);
    this.city = userInput;
  }
  //MutableLiveData<> data;
}
