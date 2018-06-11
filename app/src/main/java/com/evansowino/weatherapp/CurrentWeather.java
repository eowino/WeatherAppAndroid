package com.evansowino.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentWeather {
    private long time; // Epoch time
    private String summary;
    private int icon;
    private boolean isDayTime;
    private Temperature temperatureType;

    public CurrentWeather(long time, String summary, int icon, boolean isDayTime, Temperature temperatureType) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.isDayTime = isDayTime;
        this.temperatureType = temperatureType;
    }


    public long getTime() {
        return time;
    }

    public String getFormattedTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date dateTime = new Date(time * 1000);
        return  formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIcon() {
        return icon;
    }

    public int getIconId() {
        // "Sunny","Cloudy","Rain","Drizzle","Shower","Flurries","Storm","Snow",
        // "Windy","Cold","Ice","Hot","Fog","Overcast","Sleet","Clear","Moonlight",

        int iconId = R.drawable.clear_day;

        switch (summary) {
            case "Rain":
            case "Shower":
            case "Storm":
            case "Drizzle":
                iconId = R.drawable.rain;
                break;
            case "Snow":
            case "Flurries":
                iconId = R.drawable.snow;
                break;
            case "Sleet":
            case "Ice":
            case "Cold":
                iconId = R.drawable.sleet;
                break;
            case "Windy":
                iconId = R.drawable.wind;
                break;
            case "Fog":
                iconId = R.drawable.fog;
                break;
            case "Cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "Overcast":
                iconId = R.drawable.partly_cloudy;
                break;
            case "Moonlight":
                iconId = R.drawable.cloudy_night;
                break;
        }

        return iconId;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    public void setDayTime(boolean dayTime) {
        isDayTime = dayTime;
    }

    public Temperature getTemperatureType() {
        return temperatureType;
    }

    public void setTemperatureType(Temperature temperatureType) {
        this.temperatureType = temperatureType;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "time=" + time +
                ", summary='" + summary + '\'' +
                ", icon=" + icon +
                ", isDayTime=" + isDayTime +
                ", temperatureType=" + temperatureType.toString() +
                '}';
    }
}
