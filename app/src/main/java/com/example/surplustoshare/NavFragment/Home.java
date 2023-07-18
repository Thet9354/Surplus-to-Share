package com.example.surplustoshare.NavFragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.surplustoshare.Adapter.JustInAdapter;
import com.example.surplustoshare.Adapter.PromoAdapter;
import com.example.surplustoshare.Models.JustIn;
import com.example.surplustoshare.Models.Promotions;
import com.example.surplustoshare.R;

import java.util.ArrayList;

public class Home extends Fragment {

    private androidx.recyclerview.widget.RecyclerView rv_promo, rv_justIn;
    private RelativeLayout rel_groceries, rel_Restaurants;
    private androidx.appcompat.widget.AppCompatButton btn_seeAll;

    private Context mContext;

    private PromoAdapter promoAdapter;
    private JustInAdapter justInAdapter;

    private final ArrayList<Promotions> promotionsArrayList = new ArrayList<>();
    private final ArrayList<JustIn> justInArrayList = new ArrayList<>();

    int[] promoPics = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3, R.drawable.promo4};
    int[] justInPics = {R.drawable.justin1, R.drawable.greek_yogurt, R.drawable.magnum_icecream, R.drawable.bagel};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //RecyclerView
        rv_promo = v.findViewById(R.id.rv_promo);
        rv_justIn = v.findViewById(R.id.rv_justIn);

        //RelativeLayout
        rel_groceries = v.findViewById(R.id.rel_groceries);
        rel_Restaurants = v.findViewById(R.id.rel_Restaurants);

        //Button
        btn_seeAll = v.findViewById(R.id.btn_seeAll);

        initUI();

    }

    private void initUI() {

        //------------Promotions----------
        //for better performance of recyclerview.

        rv_promo.setHasFixedSize(true);

        promoAdapter = new PromoAdapter(getContext(), promotionsArrayList);
        rv_promo.setAdapter(promoAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_promo.setLayoutManager(llm);

        new LoadPromo().execute();



        //------------Just In----------
        //for better performance of recyclerview.

        rv_justIn.setHasFixedSize(true);

        justInAdapter = new JustInAdapter(getContext(), justInArrayList);
        rv_justIn.setAdapter(justInAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llml = new LinearLayoutManager(mContext);
        llml.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llml.setOrientation(LinearLayoutManager.HORIZONTAL);
        llml.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_justIn.setLayoutManager(llml);

        new LoadJustIn().execute();
    }

    Promotions promotions;
    JustIn justIn;

    class LoadPromo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] promoCaptions = getResources().getStringArray(R.array.promo);

                for (int i = 0 ; i < promoCaptions.length; i++)
                {
                    promotions = new Promotions();
                    promotions.setPromoImg(promoPics[i]);
                    promotions.setCaption(promoCaptions[i]);
                    promotionsArrayList.add(promotions);
                    promotions = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (promotionsArrayList != null && promotionsArrayList.size() > 0) {
                promoAdapter = new PromoAdapter(mContext, promotionsArrayList);
                rv_promo.setAdapter(promoAdapter);
                promoAdapter.notifyDataSetChanged();
            }
        }
    }

    class LoadJustIn extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] justInTitle = getResources().getStringArray(R.array.justInTitle);
                String[] justInPrice = getResources().getStringArray(R.array.justInPrice);


                for (int i = 0 ; i < justInTitle.length; i++)
                {
                    justIn = new JustIn();
                    justIn.setFoodImg(justInPics[i]);
                    justIn.setFoodTitle(justInTitle[i]);
                    justIn.setFoodPrice(justInPrice[i]);
                    justInArrayList.add(justIn);
                    justIn = null;
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (justInArrayList != null && justInArrayList.size() > 0) {
                justInAdapter = new JustInAdapter(mContext, justInArrayList);
                rv_justIn.setAdapter(justInAdapter);
                justInAdapter.notifyDataSetChanged();
            }
        }
    }

}