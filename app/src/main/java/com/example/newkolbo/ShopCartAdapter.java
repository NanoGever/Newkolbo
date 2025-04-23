package com.example.newkolbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ShopCartAdapter extends ArrayAdapter<Perfume> {
    private Context context; //גישה למשאבים
    private ArrayList<Perfume> list; //מערך הנתונים

    public ShopCartAdapter(@NonNull Context context, ArrayList<Perfume> list) {
        super(context, R.layout.item_perfume_cart,list); //זימון הפעולה הבונה של מחלקת האב
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.item_perfume_cart,parent,false); //שורת תצוגה

        Perfume p = list.get(position); // שחקן נוכחי במערך הנתונים

        //קישור לרכיבים גראפיים של שורת התצוגה
        TextView tvName = rowView.findViewById(R.id.itemName);
        TextView tvAmount = rowView.findViewById(R.id.itemAmount);

        tvName.setText(p.getName());
        tvAmount.setText(""+p.getAmount());

        return rowView; //פעולה זו מבוצעת על כל שורה ברשימה ומחזירה את שורת התצוגה
    }
}
