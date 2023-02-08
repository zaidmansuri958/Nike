package com.example.nike;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ImageView logo;
    TextView slogan, signUp;
    EditText email, password;
    LinearLayout login;
    FirebaseAuth mAuth;
    Animation top, down;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.bottom_bar);
        signUp = findViewById(R.id.signUp);
        mAuth = FirebaseAuth.getInstance();

        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.justDoIt);
        top = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideimage);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slidebottom);
        logo.startAnimation(top);
        slogan.startAnimation(top);
        login.startAnimation(down);

        pd = new ProgressDialog(this);
        pd.setTitle("Login");
        pd.setMessage("Trying to Login");
        pd.create();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u_email = email.getEditableText().toString();
                String u_password = password.getEditableText().toString();
                if (u_email.isEmpty() || u_password.isEmpty()) {
                    Toast.makeText(Login.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    pd.show();
                    mAuth.signInWithEmailAndPassword(u_email, u_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                pd.dismiss();
                                startActivity(intent);
                                finish();
                            } else {
                                pd.dismiss();
                                Toast.makeText(Login.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}