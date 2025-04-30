package com.example.newkolbo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.newkolbo.Perfume;
import com.example.newkolbo.R;
import com.example.newkolbo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button order;       // Order button
    public static ArrayList<Perfume> perfumeslist= new ArrayList<>(); //מערך נתונים


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //uploadPerfumes();
        downloadPerfumes();

        order=findViewById(R.id.button_main_order);                               //order button takes to order page 2
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void downloadPerfumes() {
        DatabaseReference myRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/").getReference("perfumes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                perfumeslist.clear();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    Perfume p = userSnapshot.getValue(Perfume.class);
                    perfumeslist.add(p);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "error reading from firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logoff(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(MainActivity.this,LoginRegActivity.class));
        finish();
    }

    public void history(View view) {
        startActivity(new Intent(MainActivity.this,HistoryActivity.class));
    }

    private void uploadPerfumes() {
        DatabaseReference myRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/").getReference("perfumes");
        ArrayList<Perfume> perfumeslist = new ArrayList<>();
        perfumeslist.add(new Perfume(1,1,"A",true,0));
        perfumeslist.add(new Perfume(3,2,"B",true,0));
        perfumeslist.add(new Perfume(2,3,"C",true,0));
        perfumeslist.add(new Perfume(2,4,"D",true,0));
        perfumeslist.add(new Perfume(2,5,"E",true,0));
        perfumeslist.add(new Perfume(2,6,"F",true,0));
        myRef.setValue(perfumeslist);

    }
}
