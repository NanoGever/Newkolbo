//נלחץ על כפתור "רכישה".
//
//נחכה 3 שניות כדי לדמות עיבוד.
//
//אם בזמן הזה המשתמש ילחץ על "ביטול", נעצור את העיבוד (לא נציג Toast ולא נאפשר רכישה).

package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newkolbo.R;


public class PaymentActivity extends AppCompatActivity {

    private Button btnPurchase, btnCancel;
    private ProgressBar progressBar;
    private Handler mainHandler;

    private Thread purchaseThread;
    private boolean isCancelled = false; // דגל לביטול

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_credit);

        btnPurchase = findViewById(R.id.btnPurchase);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setEnabled(false);
        btnCancel.setAlpha(0.5f);
        progressBar = findViewById(R.id.progressBar);

        mainHandler = new Handler(Looper.getMainLooper());

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sleep...
                startPurchaseThread();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelPurchase();
            }
        });
    }

    private void startPurchaseThread() {
        progressBar.setVisibility(View.VISIBLE);
        btnPurchase.setEnabled(false);
        btnCancel.setEnabled(true);
        btnCancel.setAlpha(0.5f);
        isCancelled = false;

        purchaseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // סימולציית עיבוד
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isCancelled) {
                    return; // לא עושים כלום אם בוטל
                }

                final boolean success = Math.random() < 0.5;

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        btnPurchase.setEnabled(true);
                        btnCancel.setEnabled(false);
                        btnCancel.setAlpha(0.5f);

                        if (success) {
                            Toast.makeText(PaymentActivity.this, "✅ הרכישה אושרה!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(PaymentActivity.this, "❌ הרכישה נדחתה.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        purchaseThread.start();
    }

    private void cancelPurchase() {
        isCancelled = true;

        if (purchaseThread != null && purchaseThread.isAlive()) {
            purchaseThread.interrupt(); // אפשרי אם אתה רוצה לעצור שינה (לא חובה)
        }

        progressBar.setVisibility(View.GONE);
        btnPurchase.setEnabled(true);
        btnCancel.setEnabled(false);
        btnCancel.setAlpha(0.5f);

        Toast.makeText(this, "❌ הרכישה בוטלה על ידי המשתמש.", Toast.LENGTH_SHORT).show();
    }
}

