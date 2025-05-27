package com.example.newkolbo.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.R;
import com.example.newkolbo.fragment.OrderFragment;
import com.example.newkolbo.fragment.ShopCartFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    public static Order myOrder2;
    public static ArrayList<Perfume> perfumeslist = new ArrayList<>(); //מערך נתונים

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        myOrder2 = new Order();

        // טעינת הפרגמנט הראשוני
        Fragment fragment = new OrderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // הגדרת עיצוב הטאבים
        setupTabLayout(tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                if (tab.getPosition() == 0)
                    fragment = new OrderFragment();
                else
                    fragment = new ShopCartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();

                // עדכון עיצוב הטאב הנבחר
                updateSelectedTabStyle(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // עדכון עיצוב הטאב שלא נבחר
                updateSelectedTabStyle(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //לא חשוב
            }
        });
    }

    private void setupTabLayout(TabLayout tabLayout) {
        // לולאה על כל הטאבים והגדרת עיצוב מותאם
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                // יצירת TextView מותאם אישית
                TextView customTabView = new TextView(this);

                // הגדרת הטקסט
                customTabView.setText(tab.getText());

                // גודל טקסט (18sp)
                customTabView.setTextSize(18);

                // פונט מותאם - החלף ב-R.font.שם_הפונט_שלך
                Typeface typeface = ResourcesCompat.getFont(this, R.font.sec);
                customTabView.setTypeface(typeface);

                // צבע טקסט - לבן
                customTabView.setTextColor(Color.WHITE);

                // מרווחים פנימיים
                int padding = 16; // בפיקסלים
                customTabView.setPadding(padding, padding, padding, padding);

                // יישור טקסט למרכז
                customTabView.setGravity(Gravity.CENTER);

                // הגדרת ה-custom view לטאב
                tab.setCustomView(customTabView);
            }
        }

        // הגדרת הטאב הראשון כנבחר בהתחלה
        if (tabLayout.getTabCount() > 0) {
            updateSelectedTabStyle(tabLayout.getTabAt(0), true);
        }
    }

    private void updateSelectedTabStyle(TabLayout.Tab tab, boolean isSelected) {
        View customView = tab.getCustomView();
        if (customView instanceof TextView) {
            TextView tabTextView = (TextView) customView;
            if (isSelected) {
                // עיצוב טאב נבחר
                tabTextView.setTextColor(Color.WHITE);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD); // טקסט מודגש
                // אפשר להוסיף אנימציה אם רוצים
                tabTextView.animate().scaleX(1.05f).scaleY(1.05f).setDuration(100).start();
            } else {
                // עיצוב טאב לא נבחר
                tabTextView.setTextColor(Color.parseColor("#AAFFFFFF")); // לבן שקוף במקצת
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL); // טקסט רגיל
                tabTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
            }
        }
    }

    public void pay(View view) {
        // העלאת ההזמנה למסד הנתונים
        DatabaseReference myRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("orders");
        myRef.push().setValue(OrderActivity.myOrder2);
    }
}