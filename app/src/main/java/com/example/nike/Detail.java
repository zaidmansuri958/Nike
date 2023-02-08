package com.example.nike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Detail extends AppCompatActivity {
    ImageView image, box;
    TextView name, price;
    ProgressDialog pd;
    FirebaseAuth mAuth;
    DatabaseReference database, datacreate;
    RadioGroup radio;
    RadioButton radioButton;

    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();

        radio = findViewById(R.id.radio);
        image = findViewById(R.id.detail_image);
        name = findViewById(R.id.detail_name);
        price = findViewById(R.id.detail_price);


        String product_image = intent.getStringExtra("image");
        String product_name = intent.getStringExtra("name");
        String product_price = intent.getStringExtra("price");

        Glide.with(Detail.this).load(product_image).into(image);
        name.setText(product_name);
        price.setText(product_price);


        buy = findViewById(R.id.buy);
        pd = new ProgressDialog(this);
        pd.setTitle("Order");
        pd.setMessage("Trying to sent your order");
        pd.create();


        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid().toString();
        database = FirebaseDatabase.getInstance().getReference("User");
        datacreate = FirebaseDatabase.getInstance().getReference("User_order");


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio.getCheckedRadioButtonId()==-1) {
                    Toast.makeText(Detail.this, "Please select your shoe size", Toast.LENGTH_SHORT).show();
                }
                else {
                    pd.show();
                    int selectedId = radio.getCheckedRadioButtonId();
                    radioButton = findViewById(selectedId);
                    String size = radioButton.getText().toString();
                    database.child(uid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String user_name = snapshot.child("name").getValue().toString();
                            String user_email = snapshot.child("email").getValue().toString();
                            String user_number = snapshot.child("number").getValue().toString();
                            String user_password = snapshot.child("password").getValue().toString();
                            String user_address = snapshot.child("address").getValue().toString();
                            String user_city = snapshot.child("city").getValue().toString();
                            String user_pincode = snapshot.child("pincode").getValue().toString();

                            buyModal data = new buyModal(product_name, product_image, product_price, size, user_name, user_email, user_number, user_password, user_address, user_city, user_pincode);
                            datacreate.child(uid).child(product_name).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        pd.dismiss();
                                        Toast.makeText(Detail.this, "Your order successfully sent", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Detail.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Detail.this, "Some error occured", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Detail.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}