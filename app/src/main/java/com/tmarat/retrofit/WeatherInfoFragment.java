package com.tmarat.retrofit;

import android.arch.lifecycle.Observer;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherInfoFragment extends Fragment implements Contract.View {

  private Contract.ViewModel viewModel;

  private EditText editTextCity;
  private TextView textViewCity;
  private TextView textViewTem;
  private TextView textViewHum;
  private TextView textViewPress;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // TODO: 02.07.2018 Live data init
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {

    View root = inflater.inflate(R.layout.fragment_weather_info, container, false);
    subscribeToLiveData();
    initUI(root);
    setOnclickListener(root);
    viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

    viewModel.getDataWeather().observe(this, new Observer<DataWeather>() {
      @Override public void onChanged(@Nullable DataWeather dataWeather) {
        textViewCity.setText(dataWeather.getCity());
        textViewTem.setText(dataWeather.getTem());
        textViewHum.setText(dataWeather.getHum());
        textViewPress.setText(dataWeather.getPress());
      }
    });
    return root;
  }

  private void subscribeToLiveData() {
    viewModel = ViewModelProviders.of(getActivity()).get(WeatherViewModel.class);
    // TODO: 02.07.2018  viewModel.getData().observer()
  }

  private void setOnclickListener(View view) {
    view.findViewById(R.id.bt_ok).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        viewModel.onUserClicked(getUserInput());
      }
    });
  }

  private void initUI(View root) {
    editTextCity = root.findViewById(R.id.edit_city);
    textViewCity = root.findViewById(R.id.text_view_city);
    textViewTem = root.findViewById(R.id.text_view_tem);
    textViewHum = root.findViewById(R.id.text_view_hum);
    textViewPress = root.findViewById(R.id.text_view_press);
  }

  private String getUserInput() {
    return editTextCity.getText().toString();
  }
}
