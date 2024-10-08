package com.example.newkolbo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.R;

import java.util.ArrayList;

public class MainOrder extends AppCompatActivity {

    private Button continue1;
    ArrayList<Perfume> perfumeslist= new ArrayList<>(); //מערך נתונים
    PerfumeAdapter adp;
    ListView lv;
    static Order order;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.mainorder);
        lv = findViewById(R.id.lvx);


        order = new Order(); //save my order
        initData();

        adp = new PerfumeAdapter(this, perfumeslist, order);
        lv.setAdapter(adp);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        continue1 = findViewById(R.id.continueid1);                               //continue button takes to order page Ordercredit
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calculate sum price of order
                //Intent intent = new Intent(MainOrder.this, OrderCredit.class);
                //startActivity(intent);

                Intent intent = new Intent(MainOrder.this, ShopCart.class);
                startActivity(intent);
                Toast.makeText(MainOrder.this, order.getPerfumelist().size()+"", Toast.LENGTH_SHORT).show();

            }
        });}

    private void initData() {
        perfumeslist.add(new Perfume(1,1,"A",true));
        perfumeslist.add(new Perfume(3,2,"B",true));
        perfumeslist.add(new Perfume(2,3,"C",true));
        perfumeslist.add(new Perfume(2,4,"D",true));
        perfumeslist.add(new Perfume(2,5,"E",true));
        perfumeslist.add(new Perfume(2,6,"F",true));
    }


}