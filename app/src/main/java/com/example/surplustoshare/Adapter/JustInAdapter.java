package com.example.surplustoshare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surplustoshare.Models.JustIn;
import com.example.surplustoshare.Models.Promotions;
import com.example.surplustoshare.R;

import java.util.ArrayList;

public class JustInAdapter extends RecyclerView.Adapter<JustInAdapter.CardViewHolder>{

    private ArrayList<JustIn> justInArrayList;

    private Context mContext;

    public JustInAdapter(Context mContext, ArrayList<JustIn> justInArrayList) {
        this.justInArrayList = justInArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private JustInAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(JustInAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public JustInAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_justin, parent, false);

        return new JustInAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JustInAdapter.CardViewHolder holder, int position) {

        holder.imgView_justIn.setImageResource(justInArrayList.get(position).getFoodImg());
        holder.txtView_justInTitle.setText(justInArrayList.get(position).getFoodTitle());
        holder.txtView_justInPrice.setText(justInArrayList.get(position).getFoodPrice());

    }

    @Override
    public int getItemCount() {
        return justInArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_justIn;
        private ImageView imgView_justIn;
        private TextView txtView_justInTitle, txtView_justInPrice;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            //CardView
            cv_justIn = itemView.findViewById(R.id.cv_justIn);

            //ImageView
            imgView_justIn = itemView.findViewById(R.id.imgView_justIn);

            //TextView
            txtView_justInTitle = itemView.findViewById(R.id.txtView_justInTitle);
            txtView_justInPrice = itemView.findViewById(R.id.txtView_justInPrice);
        }
    }
}
