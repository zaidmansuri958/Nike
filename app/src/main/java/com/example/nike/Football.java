package com.example.nike;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Football extends Fragment {
    RecyclerView recycle;
    FootballAdapter adapter;
    Context context;
    EditText search;
    SliderView slider;
    String url1="https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/Football%20shoes%2FFootball_1.png?alt=media&token=44ec8826-ec00-406c-aa74-601d6e55440d";
    String url2="https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/Football%20shoes%2FFootball_3.png?alt=media&token=c4334dd6-6886-4c9d-8f75-62b8abedfd69";
    String url3="https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/Football%20shoes%2FFootball_9.png?alt=media&token=ad3f30c2-1ffa-4318-8a5e-a257d9cbbf6f";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Football() {

    }

    public static Football newInstance(String param1, String param2) {
        Football fragment = new Football();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_football, container, false);
        search = root.findViewById(R.id.top);
        recycle = root.findViewById(R.id.recycle);
        slider = root.findViewById(R.id.slider);
        context = container.getContext();

        ArrayList<sliderModal> sliderDataArrayList = new ArrayList<>();


        sliderDataArrayList.add(new sliderModal(url1, "CR 7 Golden ", "CR 7 Golden best seller shoes in the world", "$ 1000"));
        sliderDataArrayList.add(new sliderModal(url2, "CR 7 Black ", "CR 7 Black best seller shoes in the world", "$ 800"));
        sliderDataArrayList.add(new sliderModal(url3, "Nike Orange ", "Nike Orange best seller shoes in the world", "$ 500"));

        sliderAdapter slideradapter = new sliderAdapter(context, sliderDataArrayList);

        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        slider.setSliderAdapter(slideradapter);

        slider.setScrollTimeInSec(3);

        slider.setAutoCycle(true);

        slider.startAutoCycle();


        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recycle.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<cardModal> options = new FirebaseRecyclerOptions.Builder<cardModal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("Football"), cardModal.class).build();
        adapter = new FootballAdapter(options);
        recycle.setAdapter(adapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}