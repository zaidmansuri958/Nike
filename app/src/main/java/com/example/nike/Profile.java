package com.example.nike;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference database;
    TextView name,email,contact,address,city,pincode;
    Button logout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Profile() {

    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View root=inflater.inflate(R.layout.fragment_profile, container, false);
        name=root.findViewById(R.id.user_name);
        email=root.findViewById(R.id.user_email);
        contact=root.findViewById(R.id.user_contact);
        address=root.findViewById(R.id.user_address);
        city=root.findViewById(R.id.user_city);
        pincode=root.findViewById(R.id.user_pincode);
        logout=root.findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance().getReference("User");
        String uid=mAuth.getUid().toString();
        database.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText("Hello,"+snapshot.child("name").getValue().toString());
                email.setText("Email:"+snapshot.child("email").getValue().toString());
                contact.setText("Mobile:"+snapshot.child("number").getValue().toString());
                address.setText("Address:"+snapshot.child("address").getValue().toString());
                city.setText("City:"+snapshot.child("city").getValue().toString());
                pincode.setText("Pincode: "+snapshot.child("pincode").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);

            }
        });
        return root;
    }
}