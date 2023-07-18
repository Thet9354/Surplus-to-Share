package com.example.surplustoshare.NavFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.surplustoshare.Adapter.RecentOrderAdapter;
import com.example.surplustoshare.Models.Order;
import com.example.surplustoshare.R;
import com.example.surplustoshare.SpaceItemDecoration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends Fragment {

    private ImageView imgView_back;
    private androidx.recyclerview.widget.RecyclerView rv_orderHistory;

    private Context mContext;

    private RecentOrderAdapter recentOrderAdapter;

    Order order;


    private final ArrayList<Order> orderArrayList = new ArrayList<>();


    FirebaseDatabase database = FirebaseDatabase.getInstance("Enter your own url here");
    DatabaseReference databaseReference  = database.getReference().child("Users");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

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
        rv_orderHistory = v.findViewById(R.id.rv_orderHistory);

        //ImageView
        imgView_back = v.findViewById(R.id.imgView_back);

        initUI();

        pageDirectories();
    }

    private void pageDirectories() {

        imgView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initUI() {
        initRecView();
    }

    private void initRecView() {
        //for better performance of recyclerview.

        rv_orderHistory.setHasFixedSize(true);

        recentOrderAdapter = new RecentOrderAdapter(getContext(), orderArrayList);
        rv_orderHistory.setAdapter(recentOrderAdapter);

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        rv_orderHistory.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_orderHistory.setLayoutManager(llm);

        rv_orderHistory.setAdapter(recentOrderAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {

            String userCart = "User's Cart";
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.child("Cart").child(userCart).getChildren())
                {
                    order = dataSnapshot.getValue(Order.class);
                    orderArrayList.add(order);
                }
                recentOrderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}