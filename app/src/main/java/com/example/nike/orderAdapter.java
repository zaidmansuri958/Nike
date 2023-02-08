package com.example.nike;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class orderAdapter extends FirebaseRecyclerAdapter<buyModal,orderAdapter.viewholder> {


    public orderAdapter(@NonNull FirebaseRecyclerOptions<buyModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull buyModal model) {
        holder.order_name.setText(model.getName());
        holder.order_size.setText(model.getSize());
        holder.order_price.setText(model.getPrice());
        Glide.with(holder.order_image.getContext()).load(model.getImage()).into(holder.order_image);

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(holder.cancel);

            }

            private void showDialog(View view) {
               AlertDialog.Builder dialog=new AlertDialog.Builder(view.getContext());
               dialog.setMessage("Do you want to cancel this order!!").setCancelable(false).
                       setPositiveButton("Cancel order", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               FirebaseAuth mAuth=FirebaseAuth.getInstance();
                               String uid=mAuth.getUid().toString();
                               DatabaseReference database=FirebaseDatabase.getInstance().getReference("User_order");
                               database.child(uid).child(model.getName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if(task.isSuccessful()){
                                           Toast.makeText(view.getContext(), "Order Successfully canceled", Toast.LENGTH_SHORT).show();
                                       }
                                       else{
                                           Toast.makeText(view.getContext(), "Order not canceled yet", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               });


                           }
                       }).setNegativeButton("close", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();

                   }
               });
               AlertDialog alertDialog=dialog.create();
               alertDialog.show();
            }
        });

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercard,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView order_image;
        TextView order_name,order_price,order_size;
        Button cancel;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            order_image=itemView.findViewById(R.id.order_image);
            order_name=itemView.findViewById(R.id.order_name);
            order_price=itemView.findViewById(R.id.order_price);
            order_size=itemView.findViewById(R.id.order_size);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }
}
