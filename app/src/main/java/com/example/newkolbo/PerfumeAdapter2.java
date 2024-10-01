package com.example.newkolbo;

import android.content.Context;
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

public class PerfumeAdapter2 extends ArrayAdapter<Perfume> {
    private Context context; //גישה למשאבים
    private ArrayList<Perfume> list; //מערך הנתונים

    public PerfumeAdapter2(@NonNull Context context, ArrayList<Perfume> list) {
        super(context, R.layout.item_perfume,list); //זימון הפעולה הבונה של מחלקת האב
        this.context = context;
        this.list = list;
        TextView tvAmount;
        ImageView itemGender;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.item_perfume2,parent,false); //שורת תצוגה

        //קישור לרכיבים גראפיים של שורת התצוגה
        TextView tvName = rowView.findViewById(R.id.itemName);
        TextView tvScore = rowView.findViewById(R.id.itemScore);
        TextView tvAmount = rowView.findViewById(R.id.itemScore2);
        ImageView itemGender = rowView.findViewById(R.id.itemGender);

        Perfume perfume=list.get(position); // שחקן נוכחי במערך הנתונים

        tvName.setText(perfume.getName());
        tvScore.setText("מחיר:"+perfume.getPrice());

        /*(if(player.getGender().equals("male"))
            imageView.setImageResource(R.drawable.male);
        if(player.getGender().equals("female"))
            imageView.setImageResource(R.drawable.female);*/
        return rowView; //פעולה זו מבוצעת על כל שורה ברשימה ומחזירה את שורת התצוגה
    }
}
