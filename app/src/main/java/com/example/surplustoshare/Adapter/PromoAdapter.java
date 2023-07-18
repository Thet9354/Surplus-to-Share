package com.example.surplustoshare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.surplustoshare.Models.Promotions;
import com.example.surplustoshare.R;

import java.util.ArrayList;


public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.CardViewHolder>{

    private ArrayList<Promotions> promotionsArrayList;

    private Context mContext;

    public PromoAdapter(Context mContext, ArrayList<Promotions> promotionsArrayList) {
        this.promotionsArrayList = promotionsArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private PromoAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(PromoAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @NonNull
    @Override
    public PromoAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_promo, parent, false);

        return new PromoAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.CardViewHolder holder, int position) {

        holder.imgView_promo.setImageResource(promotionsArrayList.get(position).getPromoImg());
        holder.txtView_promoCaption.setText(promotionsArrayList.get(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return promotionsArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cv_promo;
        private ImageView imgView_promo;
        private TextView txtView_promoCaption;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            //CardView
            cv_promo = itemView.findViewById(R.id.cv_promo);

            //ImageView
            imgView_promo = itemView.findViewById(R.id.imgView_promo);

            //TextView
            txtView_promoCaption = itemView.findViewById(R.id.txtView_promoCaption);
        }
    }
}
