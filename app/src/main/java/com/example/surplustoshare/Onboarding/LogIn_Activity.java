package com.example.surplustoshare.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surplustoshare.MainActivity;
import com.example.surplustoshare.R;

public class LogIn_Activity extends AppCompatActivity {

    private EditText editText_email, editTxt_password;

    private androidx.appcompat.widget.AppCompatButton btn_logIn;

    private String mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmail = editText_email.getText().toString();
                mPassword = editTxt_password.getText().toString();

                emailValidation();
                passwordValidation();
                validateInput();
            }
        });
    }

    private void validateInput() {
        if (!emailValidation() | !passwordValidation()){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private boolean passwordValidation() {
        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Field cannot be empty to proceed");
            return false;
        }
        else
            return true;
    }

    private boolean emailValidation() {
        if (mEmail.isEmpty())
        {
            editText_email.setError("Field cannot be empty to proceed");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        editText_email = findViewById(R.id.editText_email);
        editTxt_password = findViewById(R.id.editTxt_password);

        btn_logIn = findViewById(R.id.btn_logIn);

    }
}