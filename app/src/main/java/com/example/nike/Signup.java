package com.example.nike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    ImageView logo;
    TextView slogan,login;
    LinearLayout bottom;
    Animation top, down;
    EditText name, email, number, password,address,city,pincode;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        SET ANIMATION FOR VIEWS

        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.justDoIt);
        bottom = findViewById(R.id.bottom_bar);
        login=findViewById(R.id.login);
        top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideimage);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidebottom);
        logo.startAnimation(top);
        slogan.startAnimation(top);
        bottom.startAnimation(down);


        name = findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.contact);
        password = findViewById(R.id.password);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        pincode=findViewById(R.id.pincode);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);
        pd.setTitle("Sign Up");
        pd.setMessage("Trying to Sign Up");

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Chek user internet connection is open or not */

                if (!isConnected(Signup.this)) {
                    showDialog();

                } else {


                    String user_email = email.getEditableText().toString();
                    String user_password = password.getEditableText().toString();
                    String user_name = name.getEditableText().toString();
                    String user_number = number.getEditableText().toString();
                    String user_address=address.getEditableText().toString();
                    String user_city=city.getEditableText().toString();
                    String user_pincode=pincode.getEditableText().toString();


                    if(user_password.length()<6){
                        Toast.makeText(Signup.this,"Password should contain more than 6 letters",Toast.LENGTH_SHORT).show();
                    }


                    if (user_password.isEmpty()||user_name.isEmpty()||user_email.isEmpty()||user_address.isEmpty()||user_city.isEmpty()||user_pincode.isEmpty()) {
                        Toast.makeText(Signup.this, "Enter Credentials ", Toast.LENGTH_SHORT).show();
                    } else {
                        pd.show();
                        mAuth.createUserWithEmailAndPassword(user_email, user_password)
                                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Signup.this, "Successful", Toast.LENGTH_SHORT).show();
                                            String user_id = mAuth.getUid().toString();
                                            Authmodal data = new Authmodal(user_name, user_number, user_email, user_password,user_address,user_city,user_pincode);
                                            database = FirebaseDatabase.getInstance();
                                            DatabaseReference reference = database.getReference().child("User");
                                            reference.child(user_id).setValue(data);
                                            pd.dismiss();
                                            Intent intent = new Intent(Signup.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(Signup.this, "Error" + user_email, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }
            }
            private boolean isConnected(Signup signup) {
                ConnectivityManager connectivityManager= (ConnectivityManager) signup.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if((wifi!=null&&wifi.isConnected())||(mobile!=null&&mobile.isConnected())){
                    return true;
                }
                else{
                    return false;
                }
            }

            private void showDialog() {
                AlertDialog.Builder dialog=new AlertDialog.Builder(Signup.this);
                dialog.setMessage("Please connect to the internet to proceed further").
                        setCancelable(false).
                        setPositiveButton("connect", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Settings.ACTION_WIFI_SETTINGS);
                                startActivity(intent);
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog alertDialog=dialog.create();
                alertDialog.show();
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}