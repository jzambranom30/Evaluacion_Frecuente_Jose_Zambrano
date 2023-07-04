package com.example.evaluacion_frecuente_jose_zambrano.Modelos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VistaRevista {
    private String volume;
    private String number;
    private String year;
    private String date_published;
    private String cover;

    public VistaRevista(JSONObject json) throws JSONException {
        volume = json.getString("volume").toString();
        number = json.getString("number").toString();
        year = json.getString("year").toString();
        date_published = json.getString("date_published");
        cover = json.getString("cover");
    }

    public static ArrayList<VistaRevista> JsonObjectsBuild(JSONArray jsondato) throws JSONException {
        ArrayList<VistaRevista> vistarevistas = new ArrayList<>();
        for (int i = 0; i < jsondato.length() && i<20; i++) {
            vistarevistas.add(new VistaRevista(jsondato.getJSONObject(i)));
        }
        return vistarevistas;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
