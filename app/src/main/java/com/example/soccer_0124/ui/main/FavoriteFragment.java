package com.example.soccer_0124.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccer_0124.DatabaseHelper;
import com.example.soccer_0124.LastMatchAdapter;
import com.example.soccer_0124.Match;
import com.example.soccer_0124.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    private ArrayList<Match> listMatch;
    private RecyclerView rvMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rvMatch = view.findViewById(R.id.rv_favmatch);
        rvMatch.setHasFixedSize(true);
        rvMatch.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper db = new DatabaseHelper(getContext());

        listMatch = db.getAllfavorite();
        if(listMatch.size() !=0){
            LastMatchAdapter adapter = new LastMatchAdapter(listMatch);
            rvMatch.setAdapter(adapter);
        }

        return view;
    }
}