package com.evansowino.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private String apiKey;
    private String locationURL;
    private String weatherURL;
    private String charEncode;
    private String defaultLocation;
    private String getDefaultLocationKey;
    private String weatherURLTail;
    private CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        apiKey = res.getString(R.string.api_key);
        locationURL = res.getString(R.string.location_url);
        weatherURL = res.getString(R.string.weather_url);
        charEncode = res.getString(R.string.char_encoding);
        defaultLocation = res.getString(R.string.default_location);
        getDefaultLocationKey = res.getString(R.string.default_location_key);
        weatherURLTail = res.getString(R.string.weather_url_tail);

        if (isNetworkAvailable()) {
            sendRequest();
        } else {
            Toast.makeText(this,
                    R.string.sorry_network_unavailable, Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void sendRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(getUserWeatherURL()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String data = response.body().string();
                    Log.v(TAG, data);
                    if (response.isSuccessful()) {
                        mCurrentWeather = getCurrentDetails(data);
                        Log.v(TAG, mCurrentWeather.toString());
                    } else {
                        alertUserABoutError();

                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception caught: ", e);
                } catch (JSONException e) {
                    Log.e(TAG, "JSON Exception caught: ", e);
                }
            }
        });
    }

    private CurrentWeather getCurrentDetails(String data) throws JSONException {
        JSONArray forecast = new JSONArray(data);
        JSONObject forecastData = forecast.getJSONObject(0);

        long time = forecastData.getLong(getString(R.string.epoch_time));
        String summary = forecastData.getString(getString(R.string.weather_text));
        boolean isDayTime = forecastData.getBoolean(getString(R.string.is_day_time));
        int icon = forecastData.getInt(getString(R.string.weather_icon));

        JSONObject metric = forecastData.getJSONObject(getString(R.string.temperature))
                                        .getJSONObject(getString(R.string.metric));
        String value = metric.getString(getString(R.string.value));
        String unit = metric.getString(getString(R.string.unit));

        return new CurrentWeather(time, summary, icon, isDayTime,
                    new Temperature(getString(R.string.metric), value, unit));
    }

    private void alertUserABoutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getSupportFragmentManager(), "error_dialog ");
    }

    private String getUserLocationURL() {
        String URL = null;
        try {
            URL = locationURL + URLEncoder.encode(defaultLocation, charEncode) + apiKey;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "UnsupportedEncodingException caught: ", e);
        }

        return URL;
    }

    private String getUserWeatherURL() {
        return weatherURL + getDefaultLocationKey + weatherURLTail;
    };
}
