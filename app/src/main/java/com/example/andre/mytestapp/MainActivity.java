package com.example.andre.mytestapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.andre.mytestapp.fragment.ItemFragment;
import com.example.andre.mytestapp.fragment.ProfileFragment;
import com.example.andre.mytestapp.model.Artist;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andre on 20.04.2016.
 */
public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://download.cdn.yandex.net/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static RetrofitService service = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(RetrofitService.class);
    private CircleProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress = (CircleProgressBar) findViewById(R.id.progress);
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
        loadList(); // Если данные обновляеются у нас редко то можно сделать проверку на уже кэшированые данные.
    }

    public void onEvent(ArrayList<Artist> list) {
        new ItemFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, ItemFragment.newInstance(list))
                .commit();
    }

    private void loadList() {
        Call<ArrayList<Artist>> call = service.get();
        call.enqueue(new Callback<ArrayList<Artist>>() {
            @Override
            public void onResponse(Call<ArrayList<Artist>> call, Response<ArrayList<Artist>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) try {
                    ArrayList<Artist> artists = response.body();
                    onEvent(artists);
                    Cash.getInstance(getApplicationContext()).put(artists);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.error_data), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Artist>> call, Throwable t) {
                /* Если у нас уже есть закэшированные данные, то используем их */
                if (Cash.getInstance(getApplicationContext()).is()) {
                    onEvent(Cash.getInstance(getApplicationContext()).take());
                } else
                    Toast.makeText(MainActivity.this, getResources().getText(R.string.error_net), Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        try {
            if (fragment instanceof ItemFragment)
                getSupportActionBar().setTitle(getResources().getText(R.string.title_artists));

        } catch (Exception ignored) {
        }
    }
}
