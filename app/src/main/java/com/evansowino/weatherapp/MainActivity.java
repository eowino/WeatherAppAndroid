package com.evansowino.weatherapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        sendRequest();
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
                    Log.v(TAG, response.body().string());
                    if (!response.isSuccessful()) {
                        alertUserABoutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception caught: ", e);
                }
            }
        });
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
