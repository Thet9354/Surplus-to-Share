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

import com.example.surplustoshare.Models.Promotions;
import com.example.surplustoshare.Models.Stores;
import com.example.surplustoshare.R;
import com.example.surplustoshare.StoreFood_List_Activity;

import java.util.ArrayList;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.CardViewHolder>{

    private ArrayList<Stores> storesArrayList;

    private Context mContext;

    public StoresAdapter(Context mContext, ArrayList<Stores> storesArrayList) {
        this.storesArrayList = storesArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private StoresAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(StoresAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public StoresAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_store, parent, false);

        return new StoresAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoresAdapter.CardViewHolder holder, int position) {

        holder.imgView_stores.setImageResource(storesArrayList.get(position).getStoreImg());

        holder.txtView_openHours.setText("Opening Hours:" + storesArrayList.get(position).getOpeningHours());
        holder.txtView_salesStart.setText("Sales starts at" + storesArrayList.get(position).getSalesStartSince());
        holder.txtView_location.setText("Location:" + storesArrayList.get(position).getLocation());

        holder.cv_stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, StoreFood_List_Activity.class);
                int pos = holder.getAdapterPosition();
                i.putExtra("Store Image", storesArrayList.get(pos).getStoreImg());
                i.putExtra("Opening Hours", storesArrayList.get(pos).getOpeningHours());
                i.putExtra("Sales Start", storesArrayList.get(pos).getSalesStartSince());
                i.putExtra("Location", storesArrayList.get(pos).getLocation());

                System.out.println(storesArrayList.get(pos).getStoreImg());
                System.out.println(storesArrayList.get(pos).getOpeningHours());
                System.out.println(storesArrayList.get(pos).getSalesStartSince());
                System.out.println(storesArrayList.get(pos).getLocation());

                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storesArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_stores;
        private ImageView imgView_stores;
        private TextView txtView_openHours, txtView_salesStart, txtView_location;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_stores = itemView.findViewById(R.id.cv_stores);
            imgView_stores = itemView.findViewById(R.id.imgView_stores);
            txtView_openHours = itemView.findViewById(R.id.txtView_openHours);
            txtView_salesStart = itemView.findViewById(R.id.txtView_salesStart);
            txtView_location = itemView.findViewById(R.id.txtView_location);

        }
    }
}
