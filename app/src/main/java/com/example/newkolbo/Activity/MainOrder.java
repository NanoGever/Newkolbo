package com.example.newkolbo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.R;

import java.util.ArrayList;

public class MainOrder extends AppCompatActivity {

    private Button continue1;
    public static ArrayList<Perfume> perfumeslist= new ArrayList<>(); //מערך נתונים
    ListView lv;
    public static Order myOrder;


    //int[] picturesArray = {R.drawable.ic_launcher_background, R.drawable.better_, R.drawable.ic_launcher_foreground, R.drawable.better_, R.drawable.better_, R.drawable.better_, R.drawable.better_};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.mainorder);
        lv = findViewById(R.id.lvx);

        initData(); //todo: read from DB
        myOrder = new Order();

        PerfumeAdapter adp = new PerfumeAdapter(this, myOrder.getPerfumeList());
        lv.setAdapter(adp);

        continue1 = findViewById(R.id.continueid1);                               //continue button takes to order page Ordercredit
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update sumprice of myOrder
                int sumprice = 0;
                for(Perfume p : myOrder.getPerfumeList())
                    sumprice = sumprice + p.getPrice() * p.getAmount();
                myOrder.setSumprice(sumprice);
                //upload order to DB firebase
                //update ordernum of myOrder
                //close activity and jump to reception activity
                Intent intent = new Intent(MainOrder.this, ShopCart.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void initData() {
        perfumeslist.add(new Perfume(1,1,"A",true,0));
        perfumeslist.add(new Perfume(3,2,"B",true,0));
        perfumeslist.add(new Perfume(2,3,"C",true,0));
        perfumeslist.add(new Perfume(2,4,"D",true,0));
        perfumeslist.add(new Perfume(2,5,"E",true,0));
        perfumeslist.add(new Perfume(2,6,"F",true,0));
    }


}