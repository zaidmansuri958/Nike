package com.example.nike;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Home extends Fragment {
    RecyclerView recycle;
    cardAdapter adapter;
    Context context;
    CardView watch;
    SliderView slider;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_23.png?alt=media&token=f4966c90-c0d8-4aba-bbbd-fcc317e068d5";
    String url2="https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_19.png?alt=media&token=310a9a78-cdf6-4b97-b515-f574b0729718";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_1.png?alt=media&token=7d1c81ae-7f45-4ac5-8dba-b6f6b9039cb7";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Home() {

    }

    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        watch=root.findViewById(R.id.watch_icon);
        recycle = root.findViewById(R.id.recycle);
        slider = root.findViewById(R.id.slider);
        context = container.getContext();

        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Watch.class);
                getActivity().startActivity(intent);

            }
        });

        ArrayList<sliderModal> sliderDataArrayList = new ArrayList<>();


        sliderDataArrayList.add(new sliderModal(url1,"Air Force 1","Nike Air Force 1 best seller shoes in the world","$ 1000"));
        sliderDataArrayList.add(new sliderModal(url2,"Air Jordan ","Nike Air Jordan best seller shoes in the world","$ 800"));
        sliderDataArrayList.add(new sliderModal(url3,"Nike Style ","Nike Style best seller shoes in the world","$ 500"));

        sliderAdapter slideradapter = new sliderAdapter(context, sliderDataArrayList);

        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        slider.setSliderAdapter(slideradapter);

        slider.setScrollTimeInSec(3);

        slider.setAutoCycle(true);

        slider.startAutoCycle();


        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recycle.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<cardModal> options = new FirebaseRecyclerOptions.Builder<cardModal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("shoes"), cardModal.class).build();
        adapter = new cardAdapter(options);
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