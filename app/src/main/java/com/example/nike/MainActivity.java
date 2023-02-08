package com.example.nike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
MeowBottomNavigation bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Home()).commit();

        bnv=findViewById(R.id.bottomNav);
        bnv.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bnv.add(new MeowBottomNavigation.Model(2,R.drawable.football));
        bnv.add(new MeowBottomNavigation.Model(3,R.drawable.bag));
        bnv.add(new MeowBottomNavigation.Model(4,R.drawable.person));

        bnv.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bnv.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment temp=null;
                switch (item.getId()){
                    case 1:
                        temp=new Home();
                        break;

                    case 2:
                        temp=new Football();
                        break;

                    case 3:
                        temp=new Cart();
                        break;

                    case 4:
                        temp=new Profile();
                        break;
                }
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                  transaction.replace(R.id.frame,temp).commit();

            }
        });
        bnv.show(1,true);
    }
}