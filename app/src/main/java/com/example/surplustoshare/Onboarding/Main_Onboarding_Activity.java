package com.example.surplustoshare.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.surplustoshare.MainActivity;
import com.example.surplustoshare.R;

public class Main_Onboarding_Activity extends AppCompatActivity {

    private androidx.appcompat.widget.AppCompatButton btn_getStarted;

    private ImageView imgView_backDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onboarding);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login_Choice_Activity.class));
            }
        });

//        imgView_backDoor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
    }

    private void initWidget() {

        btn_getStarted = findViewById(R.id.btn_getStarted);

        imgView_backDoor = findViewById(R.id.imgView_backDoor);

    }
}