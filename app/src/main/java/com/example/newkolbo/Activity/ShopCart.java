package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.PerfumeAdapter2;
import com.example.newkolbo.R;

import java.util.ArrayList;

public class ShopCart extends AppCompatActivity {
    ListView lv;
    ArrayList<Perfume> perfumeslist = new ArrayList<>(); //מערך נתונים
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

        adp = new PerfumeAdapter2(this, MainOrder.order.getPerfumelist());
        lv.setAdapter(adp);

        int sumPrice = sumPrice(MainOrder.order.getPerfumelist());
        tvprice.setText(sumPrice+"מחיר סופי:");
    }

    private int sumPrice(ArrayList<Perfume> perfumelist) {
        int sum = 0;
        for (int i = 0; i < perfumeslist.size(); i++) {
            sum += perfumeslist.get(i).getPrice();
        }
        return sum;
    }
}