package com.example.surplustoshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surplustoshare.SplashPage.AddToCart_Splash_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class FoodDetail_Activity extends AppCompatActivity {

    private ImageView imgView_foodDetail, imgView_store, btn_back, btn_share;

    private TextView txtView_foodTitle, txtView_foodPrices, txtView_storeName, txtView_follow;

    private androidx.appcompat.widget.AppCompatButton btn_addToCart;

    //Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance("enter your own url here");
    DatabaseReference databaseReference  = database.getReference().child("Users");

    Intent intent;

    private String foodName, foodDesc, foodPrice, storeName;
    private int foodImage, storeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        intent = getIntent();

        initWidget();

        getIntentData();

        pageDirectories();

    }

    private void getIntentData() {
        foodName = intent.getStringExtra("Food Name");
        foodDesc = intent.getStringExtra("Food Desc");
        foodPrice = intent.getStringExtra("Food Price");
        foodImage = intent.getIntExtra("Food Image", 0);

        storeImage = intent.getIntExtra("Store Image", 0);
        storeName = intent.getStringExtra("Store Name");

        System.out.println(foodName);
        System.out.println(foodDesc);
        System.out.println(foodPrice);
        System.out.println(foodImage);
        System.out.println(storeImage);
        System.out.println(storeName);


        initUI();
    }

    private void initUI() {
        txtView_foodTitle.setText(foodName);
        txtView_foodPrices.setText(foodPrice);
        imgView_foodDetail.setImageResource(foodImage);

        txtView_storeName.setText(storeName);
        imgView_store.setImageResource(storeImage);
    }

    private void pageDirectories() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this cool food");
                intent.putExtra(Intent.EXTRA_TEXT, "Your application link here");
                startActivity(Intent.createChooser(intent, "Share Via"));
            }
        });

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
    }

    private void addData() {

        String userCart = "User's Cart";

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Cart").hasChild(userCart))
                {
                    long numOfCartOrder = snapshot.child("Cart").child(userCart).getChildrenCount();
                    System.out.println(numOfCartOrder);
                    for (int i = (int) numOfCartOrder; i<=100; i++)
                    {
                        String orderID = "Order " + (numOfCartOrder + 1);

                        databaseReference.child("Cart").child(userCart).child(orderID).child("FoodName").setValue(foodName);
                        databaseReference.child("Cart").child(userCart).child(orderID).child("FoodDesc").setValue(foodDesc);
                        databaseReference.child("Cart").child(userCart).child(orderID).child("FoodPrice").setValue(foodPrice);
                        databaseReference.child("Cart").child(userCart).child(orderID).child("StoreName").setValue(storeName);
                        databaseReference.child("Cart").child(userCart).child(orderID).child("FoodImage").setValue(foodImage);
                        databaseReference.child("Cart").child(userCart).child(orderID).child("StoreImage").setValue(storeImage);

//                    Bitmap bitmap = ((BitmapDrawable) imgView_foodDetail.getDrawable()).getBitmap();
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                    byte[] data = baos.toByteArray();
//
//                    StorageReference riversRef = imagesRef.child(eventID+".jpg");
//                    UploadTask uploadTask = riversRef.putBytes(data);

                        Toast.makeText(FoodDetail_Activity.this, "Course registered successfully", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("FoodName").setValue(foodName);
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("FoodDesc").setValue(foodDesc);
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("FoodPrice").setValue(foodPrice);
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("StoreName").setValue(storeName);
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("FoodImage").setValue(foodImage);
                    databaseReference.child("Cart").child(userCart).child("Order 1").child("StoreImage").setValue(storeImage);

                    Toast.makeText(FoodDetail_Activity.this, "Course registered successfully", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        startActivity(new Intent(getApplicationContext(), AddToCart_Splash_Activity.class));


    }

    private void initWidget() {

        //ImageView
        imgView_foodDetail = findViewById(R.id.imgView_foodDetail);
        imgView_store = findViewById(R.id.imgView_store);
        btn_back = findViewById(R.id.btn_back);
        btn_share = findViewById(R.id.btn_share);

        //TextView
        txtView_foodTitle = findViewById(R.id.txtView_foodTitle);
        txtView_foodPrices = findViewById(R.id.txtView_foodPrices);
        txtView_storeName = findViewById(R.id.txtView_storeName);
        txtView_follow = findViewById(R.id.txtView_follow);

        //Button
        btn_addToCart = findViewById(R.id.btn_addToCart);
    }
}