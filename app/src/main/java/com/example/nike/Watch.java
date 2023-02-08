package com.example.nike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Watch extends AppCompatActivity {
    RecyclerView recycle;
    cardAdapter adapter;
    SliderView slider;
    String url1 = "https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_23.png?alt=media&token=f4966c90-c0d8-4aba-bbbd-fcc317e068d5";
    String url2="https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_19.png?alt=media&token=310a9a78-cdf6-4b97-b515-f574b0729718";
    String url3 = "https://firebasestorage.googleapis.com/v0/b/nike-fe9fc.appspot.com/o/image%2Fshoe_1.png?alt=media&token=7d1c81ae-7f45-4ac5-8dba-b6f6b9039cb7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        recycle=findViewById(R.id.recycle);
        slider=findViewById(R.id.slider);


        ArrayList<sliderModal> sliderDataArrayList = new ArrayList<>();


        sliderDataArrayList.add(new sliderModal(url1,"Air Force 1","Nike Air Force 1 best seller shoes in the world","$ 1000"));
        sliderDataArrayList.add(new sliderModal(url2,"Air Jordan ","Nike Air Jordan best seller shoes in the world","$ 800"));
        sliderDataArrayList.add(new sliderModal(url3,"Nike Style ","Nike Style best seller shoes in the world","$ 500"));

        sliderAdapter slideradapter = new sliderAdapter(Watch.this, sliderDataArrayList);

        slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        slider.setSliderAdapter(slideradapter);

        slider.setScrollTimeInSec(3);

        slider.setAutoCycle(true);

        slider.startAutoCycle();

        GridLayoutManager layoutManager = new GridLayoutManager(Watch.this, 2);
        recycle.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<cardModal> options = new FirebaseRecyclerOptions.Builder<cardModal>().
                setQuery(FirebaseDatabase.getInstance().getReference().child("watch"), cardModal.class).build();
        adapter = new cardAdapter(options);
        recycle.setAdapter(adapter);
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