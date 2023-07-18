package com.example.surplustoshare;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surplustoshare.Adapter.FoodItemAdapter;
import com.example.surplustoshare.Models.FoodItem;
import com.example.surplustoshare.Models.Stores;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

public class StoreFood_List_Activity extends AppCompatActivity {

    private ImageView imgView_back, imgView_scan, imgView_map;
    private TextView txtView_store;
    private RecyclerView rv_foodItems;
    Intent intent;

    private static final int requestCamera1 = 12;

    private Bitmap bitmap, defaultBitmap;

    FoodItemAdapter foodItemAdapter;
    private final ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();
    private String salesStart, openingHours, location, storeName;
    private int storeImage;

    double lat = 0;
    double lon = 0;

    int[] foodPic = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_food_list);

        intent = getIntent();

        initWidget();

        geTransferredData();

        pageDirectories();

    }

    private void pageDirectories() {

        imgView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgView_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarCode();
            }
        });

        imgView_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a URI with the coordinates
                String uri = "geo:" + lat + "," + lon + "?q=" + lat + "," + lon + "(Marker+Title)";

                // Create an intent with the URI
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

                // Start the map activity
                startActivity(intent);

            }
        });
    }

    private void scanBarCode() {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Volume up to on flash");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureAct.class);
            barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null)
        {
            Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_SHORT).show();
            //TODO: Retrieve the bar code content and setText it to the Title, amount, and description



            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });


    private void geTransferredData() {

        storeImage = intent.getIntExtra("Store Image", 0);
        openingHours = intent.getStringExtra("Opening Hours");
        salesStart = intent.getStringExtra("Sales Start");
        location = intent.getStringExtra("Opening Hours");

        initUI();
    }

    private void initUI() {

        switch (location)
        {
            case "8am to 9pm":
                storeName = "Cold Storage";
                lat = 1.372264613225725;
                lon = 103.93168629304246;
                txtView_store.setText(storeName);
                break;
            case "9am to 10:30pm":
                storeName = "Sheng Siong";
                lat = 1.4093172615787255;
                lon = 103.7567521696371;
                txtView_store.setText(storeName);
                break;
            case "7:30pm to 11pm":
                storeName = "NTUC";
                lat = 1.470808152966054;
                lon = 103.81179767059746;
                txtView_store.setText(storeName);
                break;
            case "10am to 10pm":
                storeName = "Neon Pigeon";
                lat = 1.2883386790774713;
                lon = 103.8476986485723;
                txtView_store.setText(storeName);
                break;
        }

        initRecView();
    }

    private void initRecView() {
        rv_foodItems.setHasFixedSize(true);

        foodItemAdapter = new FoodItemAdapter(getApplicationContext(), foodItemArrayList, storeName, storeImage);
        rv_foodItems.setAdapter(foodItemAdapter);

        //layout to contain recyclerview
        GridLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
        llm.setSmoothScrollbarEnabled(true);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_foodItems.setLayoutManager(llm);

        new LoadFoodItem().execute();
    }


    FoodItem foodItem;

    class LoadFoodItem extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] foodTitle = getResources().getStringArray(R.array.foodItemName1);
                String[] foodDesc = getResources().getStringArray(R.array.foodItemDesc1);
                String[] foodPrice = getResources().getStringArray(R.array.foodItemPrice1);


                for (int i = 0 ; i < foodTitle.length; i++)
                {
                    foodItem = new FoodItem();
                    foodItem.setFoodImg(foodPic[i]);
                    foodItem.setFoodName(foodTitle[i]);
                    foodItem.setFoodDesc(foodDesc[i]);
                    foodItem.setFoodPrice(foodPrice[i]);

                    foodItemArrayList.add(foodItem);
                    foodItem = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (foodItemArrayList != null && foodItemArrayList.size() > 0) {
                foodItemAdapter = new FoodItemAdapter(getApplicationContext(), foodItemArrayList, storeName, storeImage);
                rv_foodItems.setAdapter(foodItemAdapter);
                foodItemAdapter.notifyDataSetChanged();
            }
        }
    }


    private void initWidget() {

        imgView_back = findViewById(R.id.imgView_back);
        imgView_scan = findViewById(R.id.imgView_scan);
        txtView_store = findViewById(R.id.txtView_store);
        rv_foodItems = findViewById(R.id.rv_foodItems);
        imgView_map = findViewById(R.id.imgView_map);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCamera1)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            int newWidth = 300;
            int newHeight = 200;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imgBitMap, newWidth, newHeight, false);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission has been granted, start the camera activity
        } else {
            Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
            // Permission has been denied, show an explanation or disable the feature that requires the permission
        }
    }
}