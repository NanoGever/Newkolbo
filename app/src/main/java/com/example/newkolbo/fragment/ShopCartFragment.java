package com.example.newkolbo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newkolbo.Activity.MainActivity;
import com.example.newkolbo.Activity.MainOrder;
import com.example.newkolbo.Activity.OrderActivity;
import com.example.newkolbo.Activity.PaymentActivity;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.R;
import com.example.newkolbo.ShopCartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShopCartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);

        TextView tvprice;
        tvprice = view.findViewById(R.id.priceorder);
        ListView lv = view.findViewById(R.id.lv);

        //when we choose shop cart tab we need to calculate sum price and updated my order
        int sumprice = 0;
        for(Perfume p : OrderActivity.myOrder2.getPerfumeList())
            sumprice = sumprice + p.getPrice() * p.getAmount();
        OrderActivity.myOrder2.setSumprice(sumprice);

        //create new perfumeList to display
        ArrayList<Perfume> displayList = new ArrayList<>();
        for(Perfume p : OrderActivity.myOrder2.getPerfumeList())
            if (p.getAmount() != 0)
                displayList.add(p);
        //display on list view
        ShopCartAdapter adp = new ShopCartAdapter(requireContext(), displayList);
        lv.setAdapter(adp);
        tvprice.setText("מחיר סופי: " + OrderActivity.myOrder2.getSumprice());

        Button btPay = view.findViewById(R.id.btPay);
        btPay.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             //upload OrderActivity.myOrder2 to database
             DatabaseReference myRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/").getReference("orders").push();
             OrderActivity.myOrder2.setOrdernum(myRef.getKey());
             myRef.setValue(OrderActivity.myOrder2);
             //go to payment activity
             startActivity(new Intent(getContext(), PaymentActivity.class));
         }
        });
        return view;
    }
}