package com.evansowino.weatherapp;

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
