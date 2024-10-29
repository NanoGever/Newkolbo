package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.PerfumeAdapter2;
import com.example.newkolbo.PerfumeLine;
import com.example.newkolbo.R;

import java.util.ArrayList;

public class ShopCart extends AppCompatActivity {
    ListView lv;
    PerfumeAdapter2 adp;
    TextView tvprice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvprice = findViewById(R.id.priceorder);
        lv = findViewById(R.id.lv);

        Toast.makeText(this, MainOrder.order.getPerfumeLineList().size()+"", Toast.LENGTH_SHORT).show();
        adp = new PerfumeAdapter2(this, MainOrder.order.getPerfumeLineList());
        lv.setAdapter(adp);

        int sum = sumPrice(MainOrder.order.getPerfumeLineList());
        tvprice.setText("מחיר סופי: " + sum);
    }

    private int sumPrice(ArrayList<PerfumeLine> perfumeLineList) {
        int sum = 0;
        for (PerfumeLine pl: perfumeLineList) {
            sum = sum + pl.getPrice() * pl.getAmount();
        }
        return sum;
    }
}