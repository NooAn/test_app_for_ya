package com.example.andre.mytestapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.andre.mytestapp.FormatText;
import com.example.andre.mytestapp.R;
import com.example.andre.mytestapp.model.Artist;

import java.util.ArrayList;

/**
 * Created by Andre on 20.04.2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private static final String URL_IMAGE_DEFAULT = "https://i.ytimg.com/vi/IaaYWdryo4Y/hqdefault.jpg";
    OnItemClickListener mItemClickListener;
    private ArrayList<Artist> mArtists;
    private Context mContext;

    public Adapter(ArrayList<Artist> dataset, Context c) {
        mArtists = dataset;
        mContext = c;
    }

    public void setArtists(ArrayList<Artist> mArtists) {
        this.mArtists = mArtists;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mArtists.size() > 0) {
            holder.name.setText(mArtists.get(position).getName());
            holder.style.setText(String.valueOf(FormatText.changeCommaSymbol(mArtists.get(position).getGenres())));
            holder.count.setText(String.valueOf(FormatText.getCountTextAlbums(mArtists.get(position).getAlbums())));
            Glide.with(mContext)
                    .load(getLinkImage(mArtists.get(position).getCover().getSmall()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (mArtists != null)
            size = mArtists.size();
        return size;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public String getLinkImage(String link) {
        if (link != null && link.isEmpty())
            return link;
        else return URL_IMAGE_DEFAULT;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View view;
        public TextView style;
        public TextView name;
        public TextView count;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            view = v;
            count = (TextView) v.findViewById(R.id.count);
            name = (TextView) v.findViewById(R.id.name);
            style = (TextView) v.findViewById(R.id.status);
            image = (ImageView) v.findViewById(R.id.image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }
}
