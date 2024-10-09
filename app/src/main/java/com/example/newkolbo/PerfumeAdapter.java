package com.example.newkolbo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PerfumeAdapter extends ArrayAdapter<Perfume> {
    private Context context; //גישה למשאבים
    private ArrayList<Perfume> list; //מערך הנתונים
    private Order order;
    private int[] pictures;


    public PerfumeAdapter(@NonNull Context context, ArrayList<Perfume> list, Order order, int[] pictures) {
        super(context, R.layout.item_perfume, list); //זימון הפעולה הבונה של מחלקת האב
        this.context = context;
        this.list = list;
        this.order = order;
        this.pictures = pictures;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_perfume, parent, false); //שורת תצוגה
        TextView tvName = rowView.findViewById(R.id.itemName);
        TextView tvScore = rowView.findViewById(R.id.itemScore);
        TextView tvAmount = rowView.findViewById(R.id.itemScore2);
        ImageView itemGender = rowView.findViewById(R.id.itemGender);

        Perfume perfume = list.get(position); // שחקן נוכחי במערך הנתונים
        final int[] amount = {0};

        //קישור לרכיבים גראפיים של שורת התצוגה
        Button add = rowView.findViewById(R.id.add);
        Button min = rowView.findViewById(R.id.min);
        add.setTag(position + ""); //...
        min.setTag(position + "");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount[0]++;
                tvAmount.setText("כמות:" + amount[0]);
                //add product to order
                Button b = (Button) view;
                int p = Integer.parseInt(b.getTag() + "");
                order.getPerfumelist().add(list.get(p));
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount[0]>0) {
                    amount[0]--;
                    tvAmount.setText("כמות:" + amount[0]);
                    //move product from order
                    Button b = (Button) view;
                    int p = Integer.parseInt(b.getTag() + "");
                    Perfume perfumeToRemove = list.get(p);
                    Log.d("PerfumeAdapter", perfumeToRemove + "");
                    removePerfume(order.getPerfumelist(), perfumeToRemove);
                }
            }
        });

        tvName.setText(perfume.getName());
        tvScore.setText("מחיר:" + perfume.getPrice());
        tvAmount.setText("כמות:" + amount[0]);

        ImageView pic = rowView.findViewById(R.id.pic);
        pic.setImageResource(pictures[position]);
        return rowView; //פעולה זו מבוצעת על כל שורה ברשימה ומחזירה את שורת התצוגה
    }

    private void removePerfume(ArrayList<Perfume> perfumelist, Perfume perfumeToRemove)
    {
        for (int i=0; i < perfumelist.size(); i++) {
            if (perfumelist.get(i).getName().equals( perfumeToRemove.getName() ))
            {
                Log.d("PerfumeAdapter", i+"");
                perfumelist.remove(i);
                return;
            }
        }
    }
}

