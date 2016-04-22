package com.example.andre.mytestapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.andre.mytestapp.model.Artist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Andre on 21.04.2016.
 * Класс для кэширования данных. Используем SharedPreferences. В будущем можно заменить на бд, если потребуется. А пока данных не так много.
 * Делаем синглитон для удобства инициализации  и на случай масштабирования
 */
public class Cash {
    private static final String NAME_FILE = BuildConfig.APPLICATION_ID;
    private static final String CASH = "cash";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor sharedPrefEditor;
    private static Gson gson = new GsonBuilder().create();
    private static Type fooType = new TypeToken<ArrayList<Artist>>() {
    }.getType();

    private static volatile Cash Instance = null;

    private Cash(Context context) {
        try {
            sharedPref = context.getSharedPreferences(NAME_FILE, Context.MODE_PRIVATE);
            sharedPrefEditor = sharedPref.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cash getInstance(Context context) {
        Cash localInstance = Instance;
        if (localInstance == null) {
            synchronized (Cash.class) {
                localInstance = Instance;
                if (localInstance == null)
                    Instance = localInstance = new Cash(context);
            }
        }
        return localInstance;
    }

    public void put(ArrayList<Artist> artists) {
        sharedPrefEditor.putString(CASH, gson.toJson(artists, fooType));
        sharedPrefEditor.apply();
    }

    public ArrayList<Artist> take() {
        return gson.fromJson(sharedPref.getString(CASH, ""), fooType);
    }

    /**
     *
     * @return if shared is empty FALSE, else Not empty TRUE
     */
    public boolean is() {
        return sharedPref.getString(CASH, "").compareTo("") != 0;
    }
}
