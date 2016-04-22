package com.example.andre.mytestapp;

import com.example.andre.mytestapp.model.Artist;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Andre on 21.04.2016.
 * Все просто и лаконично.
 * Интерфейс описывает методы для REST API
 */
public interface RetrofitService {
    @GET("/mobilization-2016/artists.json")
    Call<ArrayList<Artist>> get();
}
