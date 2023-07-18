package com.example.surplustoshare.SplashPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;

import com.example.surplustoshare.MainActivity;
import com.example.surplustoshare.R;

public class AddToCart_Splash_Activity extends AppCompatActivity {

    private String phoneNumber = "93542856";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);

        addToCartAlert();
    }

    private void addToCartAlert() {

        //if the balance is close to exceeding the budget or already exceeding, send sms to the user
        String alertMessage = "Hey nigga";

        SmsManager smsManager = SmsManager.getDefault();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
            }
        } else {
            //SEND ALERT
            alertMessage = "Your item has been added to the cart!";
            smsManager.sendTextMessage(phoneNumber, null, alertMessage, null, null);
        }



        System.out.println("Passed through");

    }

}