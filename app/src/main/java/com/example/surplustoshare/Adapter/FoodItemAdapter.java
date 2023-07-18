package com.example.surplustoshare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surplustoshare.FoodDetail_Activity;
import com.example.surplustoshare.Models.FoodItem;
import com.example.surplustoshare.Models.Stores;
import com.example.surplustoshare.R;
import com.example.surplustoshare.StoreFood_List_Activity;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.CardViewHolder>{

    private ArrayList<FoodItem> foodItemArrayList;

    private Context mContext;

    private String storeName;
    private int storeImage;

    public FoodItemAdapter(Context mContext, ArrayList<FoodItem> foodItemArrayList, String storeName, int storeImage) {
        this.foodItemArrayList = foodItemArrayList;
        this.mContext = mContext;
        this.storeName = storeName;
        this.storeImage = storeImage;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private FoodItemAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(FoodItemAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public FoodItemAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_fooditem, parent, false);

        return new FoodItemAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.CardViewHolder holder, int position) {

        holder.imgView_foodItem.setImageResource(foodItemArrayList.get(position).getFoodImg());
        holder.txtView_foodName.setText(foodItemArrayList.get(position).getFoodName());
        holder.txtView_foodDesc.setText(foodItemArrayList.get(position).getFoodDesc());
        holder.txtView_foodPrice.setText(foodItemArrayList.get(position).getFoodPrice());


        holder.cv_foodItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FoodDetail_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                int pos = holder.getAdapterPosition();
                i.putExtra("Food Image", foodItemArrayList.get(pos).getFoodImg());
                i.putExtra("Food Name", foodItemArrayList.get(pos).getFoodName());
                i.putExtra("Food Desc", foodItemArrayList.get(pos).getFoodDesc());
                i.putExtra("Food Price", foodItemArrayList.get(pos).getFoodPrice());
                i.putExtra("Store Name", storeName);
                i.putExtra("Store Image", storeImage);

                System.out.println(foodItemArrayList.get(pos).getFoodImg());
                System.out.println(foodItemArrayList.get(pos).getFoodName());
                System.out.println(foodItemArrayList.get(pos).getFoodDesc());
                System.out.println(foodItemArrayList.get(pos).getFoodPrice());
                System.out.println(storeName);
                System.out.println(storeImage);

                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_foodItem;
        private ImageView imgView_foodItem;
        private TextView txtView_foodName, txtView_foodDesc, txtView_foodPrice;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_foodItem = itemView.findViewById(R.id.cv_foodItem);
            imgView_foodItem = itemView.findViewById(R.id.imgView_foodItem);
            txtView_foodName = itemView.findViewById(R.id.txtView_foodName);
            txtView_foodDesc = itemView.findViewById(R.id.txtView_foodDesc);
            txtView_foodPrice = itemView.findViewById(R.id.txtView_foodPrice);

        }
    }
}
