package com.example.nike;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class cardAdapter extends FirebaseRecyclerAdapter<cardModal,cardAdapter.viewHolder> {

    public cardAdapter(@NonNull FirebaseRecyclerOptions<cardModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull cardModal model) {
        Animation animation= AnimationUtils.loadAnimation(holder.image.getContext(),R.anim.slideimage);
        holder.name.setText(model.getName());
        holder.buy.setText(model.getPrice());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
        holder.image.startAnimation(animation);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.buy.getContext(),Detail.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("name",model.getName());
                intent.putExtra("price",model.getPrice());
                holder.buy.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new viewHolder(view);
    }

    public class  viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        Button buy;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.card_image);
            name=itemView.findViewById(R.id.card_name);
            buy=itemView.findViewById(R.id.card_btn);

        }
    }
}
