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
import android.widget.ImageView;

import com.example.surplustoshare.Adapter.PromoAdapter;
import com.example.surplustoshare.Adapter.StoresAdapter;
import com.example.surplustoshare.Models.Promotions;
import com.example.surplustoshare.Models.Stores;
import com.example.surplustoshare.R;

import java.util.ArrayList;


public class Store extends Fragment {

    private ImageView imgView_back;
    private androidx.recyclerview.widget.RecyclerView rv_stores;
    private StoresAdapter storesAdapter;

    private Context mContext;
    private final ArrayList<Stores> storesArrayList = new ArrayList<>();

    int[] storesPic = {R.drawable.groceries, R.drawable.store2, R.drawable.store4, R.drawable.store4, R.drawable.groceries, R.drawable.store2, R.drawable.store4, R.drawable.store4};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

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
        rv_stores = v.findViewById(R.id.rv_stores);

        //ImageView
        imgView_back = v.findViewById(R.id.imgView_back);

        initUI();

    }



    private void initUI() {
        //------------Stores----------
        //for better performance of recyclerview.

        rv_stores.setHasFixedSize(true);

        storesAdapter = new StoresAdapter(getContext(), storesArrayList);
        rv_stores.setAdapter(storesAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_stores.setLayoutManager(llm);

        new LoadStores().execute();
    }

    Stores stores;

    class LoadStores extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] storeOpenHours = getResources().getStringArray(R.array.storeOpenHours);
                String[] storeSalesStart = getResources().getStringArray(R.array.storeSalesStart);
                String[] storeLocation = getResources().getStringArray(R.array.storeLocation);


                for (int i = 0 ; i < storeOpenHours.length; i++)
                {
                    stores = new Stores();
                    stores.setStoreImg(storesPic[i]);
                    stores.setOpeningHours(storeOpenHours[i]);
                    stores.setSalesStartSince(storeSalesStart[i]);
                    stores.setLocation(storeLocation[i]);

                    storesArrayList.add(stores);
                    stores = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

            if (storesArrayList != null && storesArrayList.size() > 0) {
                storesAdapter = new StoresAdapter(mContext, storesArrayList);
                rv_stores.setAdapter(storesAdapter);
                storesAdapter.notifyDataSetChanged();
            }
        }
    }

}