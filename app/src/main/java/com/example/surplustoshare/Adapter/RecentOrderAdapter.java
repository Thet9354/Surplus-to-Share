package com.example.surplustoshare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surplustoshare.Models.Order;
import com.example.surplustoshare.R;

import java.util.ArrayList;

public class RecentOrderAdapter extends RecyclerView.Adapter<RecentOrderAdapter.CardViewHolder>{

    private ArrayList<Order> orderArrayList;
    private Context mContext;

    public RecentOrderAdapter(Context mContext, ArrayList<Order> orderArrayList) {
        this.mContext = mContext;
        this.orderArrayList = orderArrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private RecentOrderAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(RecentOrderAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public RecentOrderAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_cart, parent, false);

        return new RecentOrderAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentOrderAdapter.CardViewHolder holder, int position) {

        holder.txtView_foodName.setText(orderArrayList.get(position).getFoodName());
        holder.txtView_foodPrice.setText(orderArrayList.get(position).getFoodPrice());
        holder.txtView_foodDesc.setText(orderArrayList.get(position).getFoodDesc());
        holder.img_transaction.setImageResource(orderArrayList.get(position).getFoodImage());

    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_cartOrder;
        private ImageView img_transaction;
        private TextView txtView_foodName, txtView_foodDesc, txtView_foodPrice;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_cartOrder = itemView.findViewById(R.id.cv_cartOrder);
            img_transaction = itemView.findViewById(R.id.img_transaction);
            txtView_foodName = itemView.findViewById(R.id.txtView_foodName);
            txtView_foodDesc = itemView.findViewById(R.id.txtView_foodDesc);
            txtView_foodPrice = itemView.findViewById(R.id.txtView_foodPrice);


        }
    }
}
