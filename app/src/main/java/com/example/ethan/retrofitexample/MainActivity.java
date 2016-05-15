package com.example.ethan.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cityName) TextView textView_cityName;
    @BindView(R.id.currentDate) TextView textView_currentDate;
    @BindView(R.id.currentTemp) TextView textView_currentTemp;
    @BindView(R.id.mostlyCloudy) TextView textView_mostlyCloudy;
    @BindView(R.id.refresh) Button button_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.refresh) public void onClick_button_refresh() {

        WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                textView_currentTemp.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                textView_cityName.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity());
                textView_currentDate.setText(response.body().getQuery().getResults().getChannel().getLastBuildDate());
                textView_mostlyCloudy.setText(response.body().getQuery().getResults().getChannel().getDescription());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                Log.e("Failed", t.getMessage());

            }
        });

    }
}
