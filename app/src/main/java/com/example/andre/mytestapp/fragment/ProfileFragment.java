package com.example.andre.mytestapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andre.mytestapp.FormatText;
import com.example.andre.mytestapp.MainActivity;
import com.example.andre.mytestapp.R;
import com.example.andre.mytestapp.model.Artist;

/**
 * Created by Andre on 20.04.2016.
 */
public class ProfileFragment extends Fragment {
    private Artist artist;
    public static final String URL_ARTIST = "https://music.yandex.ru/iframe/#playlist/artist/";

    public ProfileFragment() {
    }

    public ProfileFragment createInstance(Artist artist) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.artist = artist;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        //initViews(rootView);
        ((TextView) rootView.findViewById(R.id.count_albums)).setText(FormatText.getCountTextAlbums(artist.getAlbums()) + " - " + FormatText.getCountTextSongs(artist.getTracks()));
        ((TextView) rootView.findViewById(R.id.style)).setText(FormatText.changeCommaSymbol(artist.getGenres()));
        ((TextView) rootView.findViewById(R.id.text)).setText(FormatText.firstUpperCase(artist.getDescription()));
        Glide.with(getActivity())
                .load(artist.getCover().getBig())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into((ImageView) rootView.findViewById(R.id.cover_big));
        return rootView;
    }



}
