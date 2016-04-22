package com.example.andre.mytestapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andre.mytestapp.R;
import com.example.andre.mytestapp.adapter.Adapter;
import com.example.andre.mytestapp.model.Artist;

import java.util.ArrayList;


public class ItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private ArrayList<Artist> list;
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    public ItemFragment() {
    }

    public static ItemFragment newInstance(ArrayList<Artist> list) {
        ItemFragment fragment = new ItemFragment();
        fragment.list = list;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        assert mRecyclerView != null;
        mRecyclerView.setVisibility(View.INVISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new Adapter(this.list, getActivity());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        setUpRececlerView(list);
        return view;
    }

    private void setUpRececlerView(final ArrayList list) {
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.w("LOG", "click on "+position);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new ProfileFragment().createInstance((Artist) list.get(position)))
                        .addToBackStack("list")
                        .commit();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
