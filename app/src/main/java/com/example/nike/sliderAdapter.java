package com.example.nike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class sliderAdapter extends SliderViewAdapter<sliderAdapter.viewHolder> {
    List<sliderModal> data;

    public sliderAdapter(Context context, List<sliderModal> data) {
        this.data = data;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        sliderModal sliderItem=data.get(position);
        viewHolder.name.setText(sliderItem.getName());
        viewHolder.about.setText(sliderItem.getAbout());
        viewHolder.price.setText(sliderItem.getPrice());
        Glide.with(viewHolder.itemView).load(sliderItem.getImage()).into(viewHolder.image);

    }

    @Override
    public int getCount() {
        return data.size();
    }

    public class viewHolder extends SliderViewAdapter.ViewHolder {
        ImageView image;
        TextView name,about,price;
        public viewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.slider_image);
            name=itemView.findViewById(R.id.slider_name);
            about=itemView.findViewById(R.id.slider_about);
            price=itemView.findViewById(R.id.slider_price);
        }
    }
}
