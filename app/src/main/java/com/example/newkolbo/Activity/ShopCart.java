package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.R;
import com.example.newkolbo.ShopCartAdapter;

import java.util.ArrayList;

public class ShopCart extends AppCompatActivity {
    ListView lv;
    TextView tvprice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop_cart);

        tvprice = findViewById(R.id.priceorder);
        lv = findViewById(R.id.lv);

        //create new perfumeList to display
        ArrayList<Perfume> displayList = new ArrayList<>();
        for(Perfume p : MainOrder.myOrder.getPerfumeList())
            if (p.getAmount() != 0)
                displayList.add(p);
        //display on list view
        ShopCartAdapter adp = new ShopCartAdapter(this, displayList);
        lv.setAdapter(adp);

        tvprice.setText("מחיר סופי: " + MainOrder.myOrder.getSumprice());
    }

    private int sumPrice(ArrayList<Perfume> perfumeList) { //todo: not in use
        int sum = 0;
        for (Perfume p: perfumeList) {
            sum = sum + p.getPrice() * p.getAmount();
        }
        return sum;
    }
}