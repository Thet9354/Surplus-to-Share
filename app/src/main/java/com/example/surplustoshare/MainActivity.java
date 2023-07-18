package com.example.surplustoshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.surplustoshare.NavFragment.History;
import com.example.surplustoshare.NavFragment.Home;
import com.example.surplustoshare.NavFragment.Profile;
import com.example.surplustoshare.NavFragment.Store;
import com.example.surplustoshare.Onboarding.Login_Choice_Activity;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout fragment_container;
    private ChipNavigationBar bottom_nav_bar;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        intent = getIntent();

        initWidget();

    }

    private void initWidget() {
        fragment_container = findViewById(R.id.fragment_container);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        bottom_nav_bar.setItemSelected(R.id.nav_home, true);
        bottomMenu();
    }

    private void bottomMenu() {
        bottom_nav_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i)
                {
                    case R.id.nav_home:
                        fragment = new Home();
                        break;

                    case R.id.nav_store:
                        fragment = new Store();
                        break;

                    case R.id.nav_history:
                        fragment = new History();
                        break;

                    case R.id.nav_profile:
                        fragment = new Profile();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }


}