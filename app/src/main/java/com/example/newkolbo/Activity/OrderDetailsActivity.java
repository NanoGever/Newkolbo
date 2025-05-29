package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView tvOrderNum, tvDate, tvUser, tvTotal;
    private LinearLayout layoutPerfumes;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // מציאת ה-View-ים מהלייאוט
        tvOrderNum = findViewById(R.id.tvOrderNum);
        tvDate = findViewById(R.id.tvDate);
        tvUser = findViewById(R.id.tvUser);
        tvTotal = findViewById(R.id.tvTotal);
        layoutPerfumes = findViewById(R.id.layoutPerfumes);

        // קבלת האובייקט Order מה-Intent
        Order order = (Order) getIntent().getSerializableExtra("order");

        if (order != null) {
            tvOrderNum.setText("מספר הזמנה: " + order.getOrdernum());

            // המרה של תאריך מילישניות למחרוזת קריאה
            String dateStr = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    .format(new Date(order.getDate()));
            tvDate.setText("תאריך: " + dateStr);

            // הצגת שם המשתמש
            tvUser.setText("ללקוח: " + order.getUser().getUsername());

            // ניקוי כל המוצרים לפני הוספה מחדש (מונע כפילויות)
            layoutPerfumes.removeAllViews();

            // הצגת כל מוצר שהוזמן עם הכמות והמחיר
            int total = 0;
            for (Perfume p : order.getPerfumeList()) {
                if (p.getAmount() > 0) {
                    TextView tv = new TextView(this);
                    tv.setText("• " + p.getName() + " - כמות: " + p.getAmount() +
                            " x ₪" + p.getPrice() + " = ₪" + (p.getAmount() * p.getPrice()));
                    layoutPerfumes.addView(tv);

                    total += p.getAmount() * p.getPrice();
                }
            }

            tvTotal.setText("סה\"כ לתשלום: ₪" + total);
        }

    }
}
