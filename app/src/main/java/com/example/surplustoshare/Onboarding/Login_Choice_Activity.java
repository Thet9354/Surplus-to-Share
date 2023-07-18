package com.example.surplustoshare.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.surplustoshare.R;

public class Login_Choice_Activity extends AppCompatActivity {

    private androidx.cardview.widget.CardView cv_business, cv_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        cv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User_LoginPage_Activity.class));
            }
        });

        cv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login_Choice_Activity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initWidget() {

        cv_business = findViewById(R.id.cv_business);
        cv_user = findViewById(R.id.cv_user);

    }
}