package com.example.newkolbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PerfumeAdapter extends ArrayAdapter<Perfume> {
    private Context context; //גישה למשאבים
    private ArrayList<Perfume> list; //מערך הנתונים
    private Order order;

    public PerfumeAdapter(@NonNull Context context, ArrayList<Perfume> list, Order order) {
        super(context, R.layout.item_perfume,list); //זימון הפעולה הבונה של מחלקת האב
        this.context = context;
        this.list = list;
        this.order = order;
        TextView tvAmount;
        Button add;
        Button min;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.item_perfume,parent,false); //שורת תצוגה
        TextView tvName = rowView.findViewById(R.id.itemName);
        TextView tvScore = rowView.findViewById(R.id.itemScore);
        TextView tvAmount = rowView.findViewById(R.id.itemScore2);
        ImageView imageView = rowView.findViewById(R.id.itemGender);

        Perfume perfume=list.get(position); // שחקן נוכחי במערך הנתונים
        final int[] amount = {0};

        //קישור לרכיבים גראפיים של שורת התצוגה
        Button add =rowView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              amount[0]++;
              tvAmount.setText("כמות:"+ amount[0]);
              //update this.order
              order.getPerfumelist().add(new Perfume(1,1,"tes",true)) ;
            }
        });
        Button min =rowView.findViewById(R.id.min);
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount[0]--;
                tvAmount.setText("כמות:"+ amount[0]);
            }
        });

        tvName.setText(perfume.getName());
        tvScore.setText("מחיר:"+perfume.getPrice());
        tvAmount.setText("כמות:"+ amount[0]);






        /*(if(player.getGender().equals("male"))
            imageView.setImageResource(R.drawable.male);
        if(player.getGender().equals("female"))
            imageView.setImageResource(R.drawable.female);*/
        return rowView; //פעולה זו מבוצעת על כל שורה ברשימה ומחזירה את שורת התצוגה
    }
}
